package Multithreading;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Math.max;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * Created  on 5/16/2016.
 */
public class RateLimiter {

    class DelayedEntry implements Delayed {
        long expireAt;
        TimeUnit unit;
        DelayedEntry(long delay, TimeUnit tu) {
            unit = tu;
            setDelay(delay);
        }
        void setDelay(long delay) {
            this.expireAt = System.nanoTime() + unit.toNanos(delay);
        }
        public int compareTo(Delayed other) {
            throw new IllegalStateException("Expected single element queue");
        }
        public long getDelay(TimeUnit u) {
            return u.convert(expireAt - System.nanoTime(), NANOSECONDS);
        }
    }

    // limit with a knowledge of a client, the number of calls a particular client can make in a certain time.
    DelayQueue<DelayedEntry> queue;
    DelayedEntry token;
    TimeUnit rateUnit;
    AtomicInteger rate;
    RateLimiter(int rateLimit) {
        queue = new DelayQueue<>();
        rateUnit = NANOSECONDS;
        rate = new AtomicInteger(rateLimit);
        token = new DelayedEntry(0, NANOSECONDS);
    }
    boolean acquire(int permits) throws InterruptedException {
        long targetDelay = rateUnit.toNanos(permits) / max(1, rate.get());
        DelayedEntry nextToken = token;
        while (!queue.isEmpty()) {
            nextToken = queue.take();
        }
        assert nextToken != null;
        nextToken.setDelay(targetDelay);
        return queue.offer(token);
    }
}