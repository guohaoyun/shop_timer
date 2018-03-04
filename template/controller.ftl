package ${entity.javaPackage};

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import gdut.timer.controller.BaseController;
import gdut.timer.service.${entity.lowerName}.${entity.entityName}Service;
/**
 * @author ghy
 *
 */
@Controller
@RequestMapping(value = "${entity.lowerName}")
public class ${entity.className} extends BaseController {

	Logger logger = Logger.getLogger(${entity.className}.class);
	
	@Autowired
	private ${entity.entityName}Service ${entity.lowerName}Service;
	
	@RequestMapping(value = "/")
	public void test(HttpServletRequest request, HttpServletResponse response){
		
		String jsonResponse = null;
		
		if(logger.isDebugEnabled()){
			logger.debug("jsonResponse:"+jsonResponse);
		}
		
		renderJson(response, jsonResponse);
	}
}
