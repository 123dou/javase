package dynamicProxy;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

public class Test {
    public static void main(String[] args) {
        IUserService userService = new UserServiceImpl();
        MyInvocationHandler ih = new MyInvocationHandler(userService);
        IUserService proxy = (IUserService) ih.getProxy();
        byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy0", UserServiceImpl.class.getInterfaces());
        String path = "D:/ideaWorkspace/java/dahuaDesignPattern/src/dynamicProxy/UserServiceImplProxy.class";
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(classFile);
            fos.flush();
            System.out.println("写入类文件成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        proxy.update();
        System.out.println("-----------------------");
        proxy.insert();
    }
}
