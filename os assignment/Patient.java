public class Patient implements Runnable {
    private String name;
    private WaitingRoom waitingRoom;

    public Patient(String name, WaitingRoom waitingRoom) {
        this.name = name;
        this.waitingRoom = waitingRoom;
    }

    public String getName() {
        return name;
    }

    public void leaveHospital() {
        System.out.println(name + " left.");
    }

    @Override
    public void run() {
        try {
            waitingRoom.enterWaitingRoom(this);
        } catch (InterruptedException e) {
            System.out.println(name + " check-in interrupted.");
        }
    }
}