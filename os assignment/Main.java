import java.util.*;
public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter waiting room capacity : ");
        int waitingRoomCapacity = input.nextInt();

        System.out.print("Enter number of doctors: ");
        int numberOfDoctors = input.nextInt();

        System.out.print("Enter number of patients: ");
        int numberOfPatients = input.nextInt();

        Hospital hospital = new Hospital(
                waitingRoomCapacity,
                numberOfPatients,
                numberOfDoctors
        );

        hospital.runSimulation();
    }

}
