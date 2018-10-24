package abstractFactory.dao;

import abstractFactory.entity.Department;
/**
 * 有可能有不止一种的实现,所以可以考虑有抽像工厂的设计模式
 */
public interface IDepartmentDao {
    void insert(Department department);
    void update(Department department);
}
