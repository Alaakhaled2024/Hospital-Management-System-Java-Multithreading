import java.util.LinkedList;
import java.util.Queue;

public class WaitingRoom {
    private final Queue<Patient> buffer;
    private final int capacity;

    private final Semaphore mutex;
    private final Semaphore emptySeats;
    private final Semaphore patients;

    public WaitingRoom(int capacity) {
        this.capacity = capacity;
        this.buffer = new LinkedList<>();
        this.mutex = new Semaphore(1);
        this.emptySeats = new Semaphore(capacity);
        this.patients = new Semaphore(0);
    }

    // اول لما المريض يدخل

    public void enterWaitingRoom(Patient patient) throws InterruptedException {
        System.out.println(patient.getName() + " arrived.");

        // شوف في مكان ولا لا
        synchronized (this) {
            if (buffer.size() == capacity) {
                System.out.println("The waiting room is full.");
                System.out.println(patient.getName() + " is waiting outside for an available seat.");
            }
        }
        emptySeats.acquire();
        mutex.acquire();
        buffer.add(patient);
        System.out.println(patient.getName() + " entered the waiting room.");
        mutex.release();
        patients.release();
    }

    // الدكتور يرجع لو مفيش اوضة فاضية
    public void returnPatient(Patient patient) throws InterruptedException {
        synchronized (this) {
            if (buffer.size() == capacity) {
                System.out.println("Waiting room full.");
                System.out.println(patient.getName() + " is waiting outside for a seat.");
            }
        }

        emptySeats.acquire();
        mutex.acquire();
        buffer.add(patient);
        System.out.println(patient.getName() + " entered the waiting room.");
        mutex.release();
        patients.release();
    }

    // ياخد عيان جديد و يوقف لو وصل صفر
    public Patient takePatient() throws InterruptedException {
        patients.acquire();
        mutex.acquire();
        Patient patient = buffer.poll();
        mutex.release();
        emptySeats.release();
        return patient;
    }

    public synchronized int getWaitingCount() {
        return buffer.size();
    }
    public void shutdownDoctors(int numberOfDoctors)
            throws InterruptedException {

        mutex.acquire();

        for (int i = 0; i < numberOfDoctors; i++) {
            buffer.add(null);
            patients.release();
        }

        mutex.release();
    }
}