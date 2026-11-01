public class TreatmentRoomTest {
    public static void main(String[] args) throws Exception {
        TreatmentRoom room = new TreatmentRoom();

        System.out.println("doctor 1 acquire: " + room.acquireRoom(1000));
        System.out.println("doctor 2 acquire: " + room.acquireRoom(1000));

        long start = System.currentTimeMillis();
        System.out.println("doctor 3 acquire (expect to false after 3 seconds): " + room.acquireRoom(3000));
        System.out.println("time processed of acquiring : " + (System.currentTimeMillis() - start) + " ms");

        room.releaseRoom();
        System.out.println("doctor 3 acquire after release: " + room.acquireRoom(1000));
    }
}
