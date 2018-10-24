package abstractFactory.factory;

import abstractFactory.dao.IDepartmentDao;
import abstractFactory.dao.IUserDao;

/**
 * 抽像出需要产生的接口方法:其中产生接口的方法可以有不同的实现
 * 无论哪一种实现都要能实现两个方法
 * @author dou
 *
 */
public interface IFactory {
    IUserDao createUserDao();
    IDepartmentDao createDepartmentDao();
}
