

import java.util.Random;


public class Doctor implements  Runnable 
{
	
	private final int DocID ;
	private final WaitingRoom  waitingRoom ;
	private final TreatmentRoom treatmentRoom;
	private final Random random = new Random();
	
public Doctor(int ID , WaitingRoom  WRoom , TreatmentRoom TRoom )

{
	
	DocID = ID  ;
	waitingRoom = WRoom ;
	treatmentRoom = TRoom  ; 
}


    public int getId() 
    {
        return   DocID  ; 
    }
    

    @Override
     public void run()
     
    {
    	try 
    	{
    		
    		while (!Thread.currentThread().isInterrupted())
    		{
    			Patient patient  = waitingRoom.takePatient();
    			
    			if(patient == null)
    			{
    				break ;
    			}
    			
            System.out.println("Doctor with "+ DocID + " is assigned to "+patient.getName() )  ; 	

            System.out.println("Doctor " + DocID + " is trying to acquire a Treatment Room ");


            boolean acquired = treatmentRoom.acquireRoom(3000);
            
                      if(acquired )
                    	  
                      {
                    	  
                    	  System.out.println("Doctor " + DocID + "  acquired a Treatment Room ");
                    	  System.out.println("Doctor " + DocID + "  begins  Treating "+ patient.getName());
                    	  
                       Thread.sleep(1000+random.nextInt(2000));
                          
                 	  System.out.println("Doctor " + DocID + "  finished   Treating "+ patient.getName());
                 	  
                    	  treatmentRoom.releaseRoom();
                    	  
                    	  System.out.println("Doctor " + DocID + "  released  the Treatment room ");
                    	  
                    	  System.out.println("Doctor " + DocID + " Is now available ");
                    	  
                    	  patient.leaveHospital();
                    	
                    	 
                      }
                      else
                      {
                    	  
                     	  System.out.println("Doctor " + DocID + "  can not acquire a Treatment Room ");
                    	  
                     	 System.out.println("Doctor " + DocID + "  is sending "+ patient.getName() + " back to the waiting Room ");
                     	 
                     	 waitingRoom.returnPatient(patient);
                   	  
                    	  System.out.println("Doctor " + DocID + " Is now available ");
                    	 
                      }
    		}
    		
    		
    	}
  		catch(InterruptedException e )
    			{
    				Thread.currentThread().interrupt() ;
    			}
    			
    			   		
      	}
    	  }
    
 
 
 
    
    
   