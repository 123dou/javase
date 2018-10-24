package dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyInvocationHandler implements InvocationHandler {
    //目标对像
	private Object target;
	/**
	 * 构造函数
	 * @param target
	 */
	public MyInvocationHandler(Object target) {
		this.target = target;
	}
	/**
	 * 执行目标对像的方法
	 * @param proxy
	 * @param method
	 * @param args
	 * @return
	 * @throws Throwable
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("执行目标方法之前.......");
        Object result = method.invoke(target, args);
        System.out.println("执行目标方法之后.........");
		return result;
	}
	/**
	 * 获取目标对像的代理对像
	 * @return
	 */
	public Object getProxy() {
		return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), 
				target.getClass().getInterfaces(), this);
	}

}













