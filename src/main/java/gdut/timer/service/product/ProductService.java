package gdut.timer.service.product;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gdut.timer.dao.BaseDao;
import gdut.timer.service.BaseService;
import gdut.timer.dao.product.ProductDao;
/**
 * @author ghy
 *
 */
@Service
public class ProductService extends BaseService {

	@Autowired
	private ProductDao productDao;
	
	public void addSalesNumber(){
		productDao.addSalesNumber();
	}
}
