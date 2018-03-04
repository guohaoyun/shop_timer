package gdut.timer.template;


public class Entity {

	// 实体所在的包名
		private String javaPackage;	//com.timer.dao.role
		//Dao名
		private String className;	//RoleDao
		//实体名
		private String entityName;	//Role
		
		private String lowerName;	//role
		
		public String getJavaPackage() {
			return javaPackage;
		}
		public void setJavaPackage(String javaPackage) {
			this.javaPackage = javaPackage;
		}
		public String getClassName() {
			return className;
		}
		public void setClassName(String className) {
			this.className = className;
		}
		public String getEntityName() {
			return entityName;
		}
		public void setEntityName(String entityName) {
			this.entityName = entityName;
		}
		public String getLowerName() {
			return lowerName;
		}
		public void setLowerName(String lowerName) {
			this.lowerName = lowerName;
		}
	
		
		
}
