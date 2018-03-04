package ${entity.javaPackage};


import org.springframework.stereotype.Repository;

import gdut.timer.dao.BaseDao;
import gdut.timer.model.${entity.entityName};
/**
 * @author ghy
 *
 */
@Repository
public class ${entity.className} extends BaseDao<${entity.entityName}, Integer> {

}
