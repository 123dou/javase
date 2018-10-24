package producerAndcomsumer.pc5;


public class Producer implements Runnable {
    private Resource resource;

    private volatile boolean iscontinue = true;

    public Producer(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        try {
            while (iscontinue && !Thread.interrupted()) {
                Thread.sleep((long) (Math.random() * 1000));
                resource.add();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        iscontinue = false;
    }
}
