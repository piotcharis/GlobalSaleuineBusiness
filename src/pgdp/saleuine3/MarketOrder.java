package pgdp.saleuine3;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MarketOrder implements Comparable<MarketOrder> {
    private final int id;

    private long ordered;
    private long supply;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public MarketOrder(int id, int order) {
        this.id = id;
        ordered = order;
    }

    public void addMarketOrder(MarketOrder marketOrder) {
        lock.writeLock().lock();
        try {
            if (marketOrder.id != this.id) {
                return;
            }
            ordered += marketOrder.ordered;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public long supplyMarket(int maxSupply) {
        lock.writeLock().lock();
        try {
            long diff = ordered - supply;
            if (diff > maxSupply) {
                supply += maxSupply;
                return maxSupply;
            } else {
                supply = ordered;
                return diff;
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    public boolean isMarketOrderFullySupplied() {
        lock.readLock().lock();
        try {
            return supply == ordered;
        } finally {
            lock.readLock().unlock();
        }
    }

    public long getOrdered() {
        return ordered;
    }

    public long getSupply() {
        return supply;
    }

    @Override
    public int compareTo(MarketOrder o) {
        return Integer.compare(this.id, o.id);
    }

    @Override
    public String toString() {
        lock.readLock().lock();
        try {
            return "Market " + id + " ordered " + ordered + " and got supplied with " + supply;
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof MarketOrder)) {
            return false;
        }
        return this.id == ((MarketOrder) o).id;
    }

    @Override
    public int hashCode() {
        // override hashCode for good practice since equals is overwritten
        return this.id;
    }
}
