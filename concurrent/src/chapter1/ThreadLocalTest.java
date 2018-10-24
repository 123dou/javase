package chapter1;

public class ThreadLocalTest {
    static class ResourceClass {
        public final static ThreadLocal<String> RESOURCE_1 = new ThreadLocal<>();
        public final static ThreadLocal<String> RESOURCE_2 = new ThreadLocal<>();
        public static String resource1;
        public static String resource2;
    }

    static class A {
        public void setOne(String value) {
            ResourceClass.RESOURCE_1.set(value);
            ResourceClass.resource1 = value;
        }

        public void setTwo(String value) {
            ResourceClass.RESOURCE_2.set(value);
            ResourceClass.resource2 = value;
        }
    }

    static class B {
        public void display() {
            System.out.println(ResourceClass.RESOURCE_1.get() + ":" + ResourceClass.RESOURCE_2.get());
            //System.out.println(ResourceClass.resource1 + ":" + ResourceClass.resource2);
        }
    }

    public static void main(String[] args) {
        final A a = new A();
        final B b = new B();
        for (int i = 0; i < 15; i++) {
            final String resource1 = "线程-" + i;
            final String resource2 = "value = (" + i + ")";
            new Thread(){
                public void run () {
                    try {
                        a.setOne(resource1);
                        Thread.sleep(100);
                        a.setTwo(resource2);
                        b.display();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        ResourceClass.RESOURCE_1.remove();
                        ResourceClass.RESOURCE_2.remove();
                    }
                }
            }.start();
        }
    }
}
