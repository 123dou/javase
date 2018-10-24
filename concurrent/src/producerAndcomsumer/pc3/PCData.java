package producerAndcomsumer.pc3;


public final class PCData {
    private final int DATA;
    public PCData(int data) {
        this.DATA = data;
    }

    public int getDATA() {
        return DATA;
    }

    public String toString() {
        return "data: " + DATA;
    }
}
