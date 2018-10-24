package clhAndMsc;

import java.util.concurrent.atomic.AtomicReference;

public class CLHLock implements Lock {
    AtomicReference<QNode> tail;
    ThreadLocal<QNode> myprev;
    ThreadLocal<QNode> mynode;

    public CLHLock() {
        tail = new AtomicReference<>(new QNode());
        mynode = new ThreadLocal<QNode>() {
            @Override
            protected QNode initialValue() {
                return new QNode();
            }
        };

        myprev = new ThreadLocal<QNode>() {
            @Override
            protected QNode initialValue() {
                return null;
            }
        };
    }

    @Override
    public void lock() {
        QNode qNode = mynode.get();
        qNode.locked = true;
        QNode pred = tail.getAndSet(qNode);
        myprev.set(pred);
        while (pred.locked) {
        }
    }

    @Override
    public void unlock() {
        QNode qNode = mynode.get();
        qNode.locked = false;
        mynode.set(myprev.get());
    }

    class QNode {
        boolean locked;
    }
}

