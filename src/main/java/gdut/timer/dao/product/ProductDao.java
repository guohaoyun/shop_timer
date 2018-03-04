package gdut.timer.dao.product;


import org.springframework.stereotype.Repository;

import gdut.timer.dao.BaseDao;
import gdut.timer.model.Product;
/**
 * @author ghy
 *
 */
@Repository
public class ProductDao extends BaseDao<Product, Integer> {

	
	public void addSalesNumber(){
		String sql = "UPDATE t_product SET sales_number = sales_number + 1";
		super.executeSQL(sql, null);
	}
}
