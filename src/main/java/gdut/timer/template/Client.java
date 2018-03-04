package gdut.timer.template;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class Client {

	private static File javaFile = null;

	public static void main(String[] args) {
		
		String modelName = "Product";	//填写
		String lowerName = modelName.toLowerCase();	
		create("gdut.timer.dao."+lowerName,modelName+"Dao",modelName,"dao.ftl",lowerName);
		create("gdut.timer.service."+lowerName,modelName+"Service",modelName,"service.ftl",lowerName);
		//create("com.timer.controller."+lowerName,modelName+"Controller",modelName,"controller.ftl",lowerName);
	}
	
	private static void create(String javaPackage, String className, String entityName, String ftlFile,String lowerName){
		Configuration cfg = new Configuration();	
		try { 
			// 步骤一：指定 模板文件从何处加载的数据源，这里设置一个文件目录
			cfg.setDirectoryForTemplateLoading(new File("./template"));
			cfg.setObjectWrapper(new DefaultObjectWrapper());
			
			// 步骤二：获取 模板文件
			Template template = cfg.getTemplate(ftlFile);
			
			// 步骤三：创建 数据模型
			Map<String, Object> root = createModel(javaPackage,className,entityName,lowerName);
			
			// 步骤四：合并 模板 和 数据模型
			// 创建.java类文件
			if(!javaFile.exists()){
				Writer javaWriter = new FileWriter(javaFile);
				template.process(root, javaWriter);
				javaWriter.flush();
				System.out.println("文件生成路径：" + javaFile.getCanonicalPath());
				
				javaWriter.close();
			}
			// 输出到Console控制台
			Writer out = new OutputStreamWriter(System.out);
			template.process(root, out);
			out.flush();
			out.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 创建数据模型
	 * @return
	 */
	private static Map<String, Object> createModel(String javaPackage, String className, String entityName,String lowerName) {
		Map<String, Object> root = new HashMap<String, Object>();
		Entity entity = new Entity();
		entity.setJavaPackage(javaPackage); // 创建包名
		entity.setClassName(className);  // 创建类名
		entity.setEntityName(entityName);
		entity.setLowerName(lowerName);
	
		
		// 创建.java类文件
		File outDirFile = new File("./src/main/java");
		if(!outDirFile.exists()){
			outDirFile.mkdir();
		}
		
		javaFile = toJavaFilename(outDirFile, entity.getJavaPackage(), entity.getClassName());
		
		root.put("entity", entity);
		return root;
	}
	
	
	/**
	 * 创建.java文件所在路径 和 返回.java文件File对象
	 * @param outDirFile 生成文件路径
	 * @param javaPackage java包名
	 * @param javaClassName java类名
	 * @return
	 */
	private static File toJavaFilename(File outDirFile, String javaPackage, String javaClassName) {
        String packageSubPath = javaPackage.replace('.', '/');
        File packagePath = new File(outDirFile, packageSubPath);
        File file = new File(packagePath, javaClassName + ".java");
        if(!packagePath.exists()){
        	packagePath.mkdirs();
        }
        return file;
    }
}
