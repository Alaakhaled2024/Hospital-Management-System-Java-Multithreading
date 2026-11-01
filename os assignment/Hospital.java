import java.util.ArrayList;
import java.util.List;

public class Hospital {

  private int  waitingRoomCapacity;
  private int numberOfDoctors;
  private int numberOfPatients ;

  private  WaitingRoom WR;
  private TreatmentRoom TR;
  List<Doctor> doctors=new ArrayList<>();
  List<Patient>patients=new ArrayList<>();

    public Hospital(int waitingRoomCapacity, int numberOfPatients, int numberOfDoctors) {
        this.waitingRoomCapacity = waitingRoomCapacity;
        this.numberOfPatients = numberOfPatients;
        this.numberOfDoctors = numberOfDoctors;
        WR=new WaitingRoom(waitingRoomCapacity);
        TR=new TreatmentRoom();
        for (int i=0; i<numberOfDoctors; i++) {
            doctors.add(new Doctor(i+1,WR,TR));
        }
        for (int i=0; i<numberOfPatients; i++) {

            patients.add(new Patient(" Patient "+ (i+1)));
        }
    }



}
