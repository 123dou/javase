package dynamicProxy;
/**
 * 目标对像
 * @author dou
 *
 */
public class UserServiceImpl implements IUserService {

	@Override
	public void update() {
        System.out.println("更新用户信息");
	}

	@Override
	public void insert() {
		System.out.println("插入一条用户数据");
	}

}
