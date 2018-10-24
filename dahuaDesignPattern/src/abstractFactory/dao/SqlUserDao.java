package abstractFactory.dao;

import abstractFactory.entity.User;

public class SqlUserDao implements IUserDao {

	@Override
	public void insert(User user) {
        System.out.println("向user表插入一条数据");
	}

	@Override
	public void update(User user) {
        System.out.println("在user表更新一条数据");
	}

}
