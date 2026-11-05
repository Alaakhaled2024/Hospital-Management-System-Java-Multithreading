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
  List<Thread> doctorThreads = new ArrayList<>();


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

            patients.add(new Patient(" Patient "+ (i+1), WR));
        }
    }
    public void startDoctors() {
        for (Doctor doctor : doctors) {
            Thread t = new Thread(doctor);
            doctorThreads.add(t);
            t.start();
        }
    }
    List<Thread> patientThreads = new ArrayList<>();

    public void startPatients() {
        for (Patient patient : patients) {
            Thread t = new Thread(patient);
            patientThreads.add(t);
            t.start();
        }
    }
    public void runSimulation() {

        System.out.println("Hospital Simulation Started");
        startDoctors();

        startPatients();

        try {

            for (Thread t : patientThreads) {
                t.join();
            }

            for (Thread t : doctorThreads) {
                t.join();
            }

        } catch (InterruptedException E) {

                 Thread.currentThread().interrupt();
  System.out.println("Hospital simulation was interrupted ");}

          System.out.println("Hospital simulation finished ");
    }


}
