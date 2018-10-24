package producerAndcomsumer.pc5;


public class Consumer implements Runnable {
    private Resource resource;

    private volatile boolean iscontinue = true;

    public Consumer(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        try {
            while (iscontinue && !Thread.interrupted()) {
                Thread.sleep((long) (Math.random() * 500));
                resource.remove();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void stop() {
        iscontinue = false;
        resource.stopRemove();
    }

}
