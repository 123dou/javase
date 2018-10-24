package abstractFactory;

import abstractFactory.dao.IDepartmentDao;
import abstractFactory.dao.IUserDao;
import abstractFactory.entity.Department;
import abstractFactory.entity.User;
import abstractFactory.factory.IFactory;
import abstractFactory.factory.OracleDaoFactory;
import abstractFactory.factory.SqlDaoFacoty;

public class Test {
    public static void main(String[] args) {
		IFactory factory = new SqlDaoFacoty();
		IUserDao userDao = factory.createUserDao();
		User user = new User();
		userDao.insert(user);
		//需要换不同的数据库的时候只需要改这一处
		IFactory factory2 = new OracleDaoFactory();
		Department department = new Department();
		IDepartmentDao deptDao = factory2.createDepartmentDao();
		deptDao.update(department);
		
	}
}
