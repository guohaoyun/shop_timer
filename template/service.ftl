package ${entity.javaPackage};


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gdut.timer.dao.BaseDao;
import gdut.timer.service.BaseService;
import gdut.timer.dao.${entity.lowerName}.${entity.entityName}Dao;
/**
 * @author ghy
 *
 */
@Service
public class ${entity.className} extends BaseService {

	@Autowired
	private ${entity.entityName}Dao ${entity.lowerName}Dao;
}
