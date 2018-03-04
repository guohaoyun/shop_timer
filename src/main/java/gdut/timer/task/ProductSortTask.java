package gdut.timer.task;

import org.springframework.beans.factory.annotation.Autowired;

import gdut.timer.service.product.ProductService;

public class ProductSortTask {

	@Autowired
	private ProductService productService;
	
	public void runJob(){
		System.out.println("runJob........................");
		productService.addSalesNumber();
	}
}
