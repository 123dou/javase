package clhAndMsc;

import java.util.concurrent.atomic.AtomicReference;

public class MSCLock {
    public class MCSLock implements Lock {
        AtomicReference<QNode> tail;
        ThreadLocal<QNode> myNode;

        @Override
        public void lock() {
            tail = new AtomicReference<>(new QNode());
            QNode qnode = myNode.get();
            QNode pred = tail.getAndSet(qnode);
            if (pred != null) {
                qnode.locked = true;
                pred.next = qnode;

                // wait until predecessor gives up the lock
                while (qnode.locked) {
                }
            }
        }

        @Override
        public void unlock() {
            QNode qnode = myNode.get();
            if (qnode.next == null) {
                if (tail.compareAndSet(qnode, null))
                    return;

                // wait until predecessor fills in its next field
                while (qnode.next == null) {
                }
            }
            qnode.next.locked = false;
            qnode.next = null;
        }

        class QNode {
            boolean locked = false;
            QNode next = null;
        }
    }
}
