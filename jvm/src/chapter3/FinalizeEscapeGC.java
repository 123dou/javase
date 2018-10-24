package chapter3;

import java.lang.ref.PhantomReference;
import java.lang.ref.WeakReference;

public class FinalizeEscapeGC {
    public static FinalizeEscapeGC SAVE_HOOK = null;
    public void isAlive() {
        System.out.println("yes, i am still alive :)");
    }

    /**
     * @throws Throwable the {@code Exception} raised by this method
     * @jls 12.6 Finalization of Class Instances
     * @see WeakReference
     * @see PhantomReference
     */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finallize method executed");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGC();
        SAVE_HOOK = null;
        System.gc();
        //因为finalize方法优先级很低,所以暂停0.5秒以等待它
        Thread.sleep(100);
        if (SAVE_HOOK != null)
            SAVE_HOOK.isAlive();
        else
            System.out.println("no,i am dead :(");
        SAVE_HOOK = null;

        //因为finalize方法优先级很低,所以暂停0.5秒以等待它
        Thread.sleep(100);
        if (SAVE_HOOK != null)
            SAVE_HOOK.isAlive();
        else
            System.out.println("no,i am dead :(");

    }
}
