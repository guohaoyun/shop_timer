package gdut.timer.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


/**  
*  
*
* @author ghy  
* @date 2017年5月13日
* 类说明  :
*/
public class BaseDao<T,PK extends java.io.Serializable> {

	// 泛型反射类
			private Class<T> entityClass;
			// 通过反射获取子类确定的泛型类
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public BaseDao() {
				//返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type
				//然后将其转换ParameterizedType
				Type genType = getClass().getGenericSuperclass();
				//返回表示此类型实际类型参数的 Type 对象的数组。[0]就是这个数组中第一个了(T,Pk)
				Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
				entityClass = (Class) params[0];

			}
			
			
			/*
			 * 
			 * 注入sessionFactory
			 */
			@Autowired
			@Qualifier("sessionFactory")
			private SessionFactory sessionFactory;

			public Session openSession() {
				return sessionFactory.openSession();
			}

			/**绑定线程
			 * @return
			 * @author ghy
			 */
			public Session getSession() {
				return sessionFactory.getCurrentSession();
			}
			
			public void closeSession(Session session){
				if(session != null){
					session.close();
				}
			}

		
			public Serializable save(T entity) {

				Session session = openSession();
				Serializable flag = session.save(entity);
				closeSession(session);
				return flag;

			}
			
			public Serializable saveByCurrentSession(T entity) {

				Session session = getSession();
				Serializable flag = session.save(entity);
				return flag;

			}
			
			
		
			public void updateByCurrentSession(T entity) {

				getSession().update(entity);

			}
			
			

			/**根据id删除PO
			 * @param id
			 * @author ghy
			 */
			public void deleteById(Serializable id) {
				Session session = openSession();
				session.delete(this.get(id));
				closeSession(session);

			}
			
			
		
			/** 
			 * 根据id判断PO是否存在
			 */
			public boolean exists(Serializable id) {

				return get(id) != null;

			}

			/*
			 * 
			 * 根据id加载PO
			 */
			
			@SuppressWarnings("unchecked")
			public T load(Serializable id) {

				return (T) getSession().load(this.entityClass, id);

			}

			/*
			 * 
			 * 根据id获取PO
			 */
			@SuppressWarnings("unchecked")
			public T get(Serializable id) {

				return (T) openSession().get(this.entityClass, id);

			}
			
			

			
			/**
			 * 获取总数，使用getCurrentSession()
			 * @return
			 * @author ghy
			 */
			public int countAll() {

				Criteria criteria = createCriteria();

				return Integer.valueOf(criteria.setProjection(Projections.rowCount())

				.uniqueResult().toString());

			}
			
			/**
			 * 获取总数，使用openSession()
			 * @return
			 * @author ghy
			 */
			public int countAll2() {

				Criteria criteria = createCriteria2();

				return Integer.valueOf(criteria.setProjection(Projections.rowCount())

				.uniqueResult().toString());

			}

			/*
			 * 
			 * 根据Criteria查询条件，获取PO总数
			 */
			
			public int countAll(Criteria criteria) {

				return Integer.valueOf(criteria.setProjection(Projections.rowCount())

				.uniqueResult().toString());

			}

			/*
			 * 
			 * 删除所有
			 */
			
			public void deleteAll(Collection<?> entities) {

				if (entities == null)

					return;

				for (Object entity : entities) {

					getSession().delete(entity);

				}

			}

		
			@SuppressWarnings("unchecked")
			public List<T> list() {

				return createCriteria().list();

			}

			/*
			 * 
			 * 获取对象列表根据Criteria
			 */
			@SuppressWarnings("unchecked")
			public List<T> list(Criteria criteria) {

				return criteria.list();

			}

			/*
			 * 
			 * 离线查询
			 */
			
			@SuppressWarnings({ "unchecked", "hiding" })
			public <T> List<T> list(DetachedCriteria criteria) {

				return (List<T>) list(criteria.getExecutableCriteria(getSession()));

			}

			/*
			 * 
			 * 获取全部对象，支持排序
			 * 
			 * @param orderBy
			 * 
			 * @param isAsc
			 * 
			 * @return
			 */

			
			@SuppressWarnings("unchecked")
			public List<T> list(String orderBy, boolean isAsc) {

				Criteria criteria = createCriteria();

				if (isAsc) {

					criteria.addOrder(Order.asc(orderBy));

				} else {

					criteria.addOrder(Order.desc(orderBy));

				}

				return criteria.list();

			}

			/*
			 * 
			 * 按属性查找对象列表，匹配方式为相等
			 * 
			 * @param propertyName
			 * 
			 * @param value
			 * 
			 * @return
			 */
			
			public List<T> list(String propertyName, Object value) {

				Criterion criterion = Restrictions

				.like(propertyName, "%" + value + "%");

				return list(criterion);

			}

			/*
			 * 
			 * 根据查询条件获取数据列表
			 */
			@SuppressWarnings("unchecked")
			private List<T> list(Criterion criterion) {

				Criteria criteria = createCriteria();

				criteria.add(criterion);

				return criteria.list();

			}

			/*
			 * 
			 * 按Criteria查询对象列表
			 * 
			 * @param criterions数量可变的Criterion
			 * 
			 * @param criterions
			 * 
			 * @return
			 */
			
			@SuppressWarnings("unchecked")
			public List<T> list(Criterion... criterions) {

				return createCriteria(criterions).list();

			}

			/*
			 * 
			 * 按属性查找唯一对象，匹配方式为相等
			 * 
			 * @param propertyName
			 * 
			 * @param value
			 * 
			 * @return
			 */
			@SuppressWarnings("unchecked")
			public T uniqueResult(String propertyName, Object value) {

				Criterion criterion = Restrictions.eq(propertyName, value);

				return (T) createCriteria(criterion).uniqueResult();

			}
			
		

			/*
			 * 
			 * 按Criteria查询唯一对象
			 * @param criterions数量可变的Criterion
			 * @param criterions
			 * @return
			 */
			public T uniqueResult(Criterion... criterions) {

				Criteria criteria = createCriteria(criterions);

				return uniqueResult(criteria);

			}

			/*
			 * 
			 * 按Criteria查询唯一对象
			 * 
			 * @param criterions
			 * 
			 * @return
			 */
			
			@SuppressWarnings("unchecked")
			public T uniqueResult(Criteria criteria) {

				return (T) criteria.uniqueResult();

			}

			/*
			 * 
			 * 为Criteria添加distinct transformer
			 * 
			 * @param criteria
			 * 
			 * @return
			 */

			// 认为没用
			
			public Criteria distinct(Criteria criteria) {

				// 将结果集进行一次封装，封装成DISTINCT_ROOT_ENTITY对象，方便service层代码使用

				criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

				return criteria;

			}

			

			
			/**
			 * getCurrentSession
			 * @return
			 * @author ghy
			 */
			public Criteria createCriteria() {

				return getSession().createCriteria(entityClass);

			}
			
			/**
			 * 使用openSession()
			 * @return
			 * @author ghy
			 */
			public Criteria createCriteria2() {

				return openSession().createCriteria(entityClass);

			}

			/*
			 * 
			 * 根据Criterion条件创建Criteria
			 * 
			 * @param criterions数量可变的Criterion
			 */
			
			public Criteria createCriteria(Criterion... criterions) {

				Criteria criteria = createCriteria();

				for (Criterion c : criterions) {

					criteria.add(c);

				}

				return criteria;

			}

			
			
			

			
			
			@SuppressWarnings("unchecked")
			public List<T> getList(int pageSize, int pageIndex, String hql) {
				List<T> list = new ArrayList<T>();
				Query query = getSession().createQuery(hql);
				query.setFirstResult(pageIndex);
				query.setMaxResults(pageSize);
				list = query.list();
				return list;
			}
			
			/**
			 * 
			 * 分页 封装到 List 对象 中 By Criteria
			 * @param
			 * @return
			 */
			public List<T> findListByCriteria(Criteria criteria, int pageSize,int pageIndex) {

				// 设置起始结果数
				criteria.setFirstResult(pageIndex);

				// 返回的最大结果集
				criteria.setMaxResults(pageSize);

				return list(criteria);

			}
			
			@SuppressWarnings("unchecked")
			public  List<T>  findByHql(String hql) {
				
				List<T> list = new ArrayList<T>();
				Query query = getSession().createQuery(hql);
				list = query.list();
				return list;
				
			}
			
			
			
			/**
			 * @param query
			 * @param params
			 * @author ghy
			 */
			private void setQuery(Query query,List<Object> params){
				if(params.size() > 0){
					int indexFlag = 0;
					for(Object obj : params){
						if(obj instanceof String){
							  query.setString(indexFlag++, (String)obj);
						  } else if(obj instanceof Integer){
							  query.setInteger(indexFlag++, (Integer)obj);
						  } else if(obj instanceof BigInteger){
							  query.setBigInteger(indexFlag++, (BigInteger)obj);
						  } else if(obj instanceof Timestamp){
							  query.setTimestamp(indexFlag++, (Timestamp)obj);
						  } else if(obj instanceof Float){
							  query.setFloat(indexFlag++, (Float)obj);
						  } else if(obj instanceof Double){
							  query.setDouble(indexFlag++, (Double)obj);
						  } else if(obj instanceof Long){
							  query.setLong(indexFlag++, (Long)obj);
						  } else{
							  
						  }
					}
				}
			}
			
			
			/**
			 * @param sql
			 * @param params
			 * @return
			 * @author ghy
			 */
			public int getCountSQLRsult(String sql,List<Object> params)
			{

				Session session = openSession();
				try {
					Query query = session.createSQLQuery(sql);
					if(params != null){
						setQuery(query,params);
					}
					return ((BigInteger)query.uniqueResult()).intValue();
				} catch (Exception e) {
					e.printStackTrace();
					return 0;
				} finally{
					closeSession(session);
				}
				
			}
			
			
			
			/**
			 * @param sql
			 * @param clazz
			 * @param params
			 * @return
			 * @author ghy
			 */
			@SuppressWarnings("unchecked")
			public <E> List<E> getListSQLRsult(String sql ,Class<E> clazz,List<Object> params){
			
				Session session = openSession();
				try {
					 SQLQuery query = session.createSQLQuery(sql);
					 
					 if(null != clazz) {
						 //query.setResultTransformer(new AliasToBeanResultTransformer(clazz));
						 query.addEntity(clazz);
					 }
					 if(params != null){
					 
						 setQuery(query,params);
					 }
					 
					 return query.list();
					
				} catch (RuntimeException re) {
					 re.printStackTrace();
					 return null;
				} finally {
					closeSession(session);
				}
				
				
				
			}
			
			public int executeSQL(String sql ,List<Object> params){
				
				Session session = openSession();
				try {
					 SQLQuery query = session.createSQLQuery(sql);
					
					 if(params != null){
						 setQuery(query,params);
					 }
					 return query.executeUpdate();
					
				} catch (RuntimeException re) {
					re.printStackTrace();
					return 0;
				} finally {
					closeSession(session);
				}
				
				
			}
			
			public int executeSQL(String sql,List<Object> params,Session session){
				
				try {
					 SQLQuery query = session.createSQLQuery(sql);
					
					 if(params != null){
						 setQuery(query,params);
					 }
					 
					 return query.executeUpdate();
					
				} catch (RuntimeException re) {
					re.printStackTrace();
					return 0;
				} 
				
				
			}
			
			public int executeSQLByCurrentSession(String sql ,List<Object> params){
				
				Session session = getSession();
				try {
					 SQLQuery query = session.createSQLQuery(sql);
					
					 if(params != null){
						 setQuery(query,params);
					 }
					 
					 return query.executeUpdate();
					
				} catch (RuntimeException re) {
					re.printStackTrace();
					return 0;
				} 
			}
			
			@SuppressWarnings("unchecked")
			public T getUniqueSQLRsult(String sql ,Class<T> clazz,List<Object> params){
				
				Session session = openSession();
				try {
					 SQLQuery query = session.createSQLQuery(sql);
					 
					 if(null != clazz) {
						 //query.setResultTransformer(new AliasToBeanResultTransformer(clazz));
						 query.addEntity(clazz);
					 }
					 if(params != null){
					 
						 setQuery(query,params);
					 }
					 
					 return (T) query.uniqueResult();
					
				} catch (RuntimeException re) {
					re.printStackTrace();
					 return null;
				} finally {
					closeSession(session);
				}
				
				
			}
}

