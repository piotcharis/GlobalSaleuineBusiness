package pgdp.pingulib.datastructures.trees;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.*;

public class BST<T extends Comparable<T>> {

    private BSTNode<T> root;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public BST() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        lock.readLock().lock();
        try {
            if (root == null) {
                return 0;
            }
            return root.size();
        } finally {
            lock.readLock().unlock();
        }
    }

    public boolean contains(T value) {
        lock.readLock().lock();
        try {
            if (isEmpty()) {
                return false;
            }
            return root.contains(value);
        } finally {
            lock.readLock().unlock();
        }
    }

    public T get(T value) {
        lock.readLock().lock();
        try {
            if (isEmpty()) {
                return null;
            }
            return root.get(value);
        } finally {
            lock.readLock().unlock();
        }
    }

    public boolean insert(T value) {
        lock.writeLock().lock();
        try {
            if (value == null) {
                return false;
            }
            if (isEmpty()) {
                root = new BSTNode<T>(value);
                return true;
            } else {
                return root.insert(value);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    public List<T> toList() {
        lock.readLock().lock();
        try {
            if (isEmpty()) {
                return new LinkedList<>();
            }
            return root.toList(Order.IN);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public String toString() {
        lock.readLock().lock();
        try {
            if (isEmpty()) {
                return "[]";
            } else {
                return root.toString();
            }
        } finally {
            lock.readLock().unlock();
        }
    }
}
