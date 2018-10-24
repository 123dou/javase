package abstractFactory.abstractFactory_reflect;

import abstractFactory.dao.IDepartmentDao;
import abstractFactory.dao.IUserDao;
import abstractFactory.dao.OracleDeptDao;
import abstractFactory.dao.OracleUserDao;
import abstractFactory.dao.SqlDeptDao;
import abstractFactory.dao.SqlUserDao;

public class DaoAccess {
    //利用反射来产生对应的工厂
    //获取UserDao
    public static IUserDao createUserDao(String db) 
    		throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    	IUserDao userDao = (IUserDao) Class.forName(db).newInstance();
    	return userDao;
    }
    
    //获取DeptDao
    public static IDepartmentDao createDeptDao(String db) 
    		throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    	IDepartmentDao deptDao = (IDepartmentDao) Class.forName(db).newInstance();
    	return deptDao;
    }
}
