public class Semaphore {
    private int permits;

    public Semaphore (int permits){
        this.permits=permits;

    }

    public synchronized void acquire() throws InterruptedException{
        while (permits<=0){
            wait();
        }
        permits--;
    }

    public synchronized void release() {
        permits++;
        notifyAll();
    }

    public synchronized boolean tryAcquire(long time_out_millis) throws InterruptedException{

        long deadline =System.nanoTime()+(time_out_millis * 1000000L);
        while (permits <= 0){
            long remaining_nanotime = deadline - System.nanoTime();
            if(remaining_nanotime <=0 )
                return false;
            long remaining_millistime = (remaining_nanotime+ 999999L) / 1000000L;
            wait(remaining_millistime);
        }
        permits--;
        return true;
    }

}