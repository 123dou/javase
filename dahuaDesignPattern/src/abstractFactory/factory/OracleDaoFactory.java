package abstractFactory.factory;

import abstractFactory.dao.IDepartmentDao;
import abstractFactory.dao.IUserDao;
import abstractFactory.dao.OracleDeptDao;
import abstractFactory.dao.OracleUserDao;
/**
 * Oracle数据库对dao层的实现也就是具体的工厂
 * @author dou
 *
 */
public class OracleDaoFactory implements IFactory {

	@Override
	public IUserDao createUserDao() {
		return new OracleUserDao();
	}

	@Override
	public IDepartmentDao createDepartmentDao() {
		return new OracleDeptDao();
	}

}
