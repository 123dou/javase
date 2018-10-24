package abstractFactory.factory;

import abstractFactory.dao.IDepartmentDao;
import abstractFactory.dao.IUserDao;
import abstractFactory.dao.SqlDeptDao;
import abstractFactory.dao.SqlUserDao;
/**
 * mysql数据库对dao层的实现就是具体的工厂
 * @author dou
 *
 */
public class SqlDaoFacoty implements IFactory{

	@Override
	public IUserDao createUserDao() {
		return new SqlUserDao();
	}

	@Override
	public IDepartmentDao createDepartmentDao() {
		return new SqlDeptDao();
	}

}
