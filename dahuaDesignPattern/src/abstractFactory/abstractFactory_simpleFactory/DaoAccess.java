package abstractFactory.abstractFactory_simpleFactory;

import abstractFactory.dao.IDepartmentDao;
import abstractFactory.dao.IUserDao;
import abstractFactory.dao.OracleDeptDao;
import abstractFactory.dao.OracleUserDao;
import abstractFactory.dao.SqlDeptDao;
import abstractFactory.dao.SqlUserDao;

public class DaoAccess {
    private static final String db = "ocrale"; //根据需要来修改db以产上相应该工产类
    //private static final String db = "mysql";
    
    //获取UserDao
    public IUserDao createUserDao(String db) {
    	IUserDao userDao = null;
    	switch(db) {
    	case "mysql": 
    		userDao = new SqlUserDao();
    		break;
    	case "ocracle": 
    		userDao = new OracleUserDao();
    		break;
    	}
    	return userDao;
    }
    
    //获取DeptDao
    public IDepartmentDao createDeptDao(String db) {
    	IDepartmentDao deptDao = null;
    	switch(db) {
    	case "mysql": 
    		deptDao = new SqlDeptDao();
    		break;
    	case "ocracle": 
    		deptDao = new OracleDeptDao();
    		break;
    	}
    	return deptDao;
    }
}
