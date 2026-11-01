
public class TreatmentRoom
{
    private final Semaphore semaphore;
    
    public TreatmentRoom() 
    {
        this.semaphore = new Semaphore(2);
    }

    public boolean acquireRoom(long timeoutMs) 
    
    	throws InterruptedException 
    { 
        return semaphore.tryAcquire(timeoutMs); 
    }
    
    public void releaseRoom() 
    {
      	semaphore.release();
    }
    
}