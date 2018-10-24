package abstractFactory.dao;

import abstractFactory.entity.Department;

public class OracleDeptDao implements IDepartmentDao {

	@Override
	public void insert(Department department) {
        System.out.println("向dept表插入一条数据");
	}

	@Override
	public void update(Department department) {
        System.out.println("在dept表更新一条数据");
	}

}
