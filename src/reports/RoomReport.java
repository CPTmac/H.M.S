package reports;

public class RoomReport extends Report {

    private final int rooms;

    public RoomReport(int rooms) {
        this.rooms = rooms;
    }

    @Override
    public void generate() {
        // Report header for the room summary.
        System.out.println("============== Room Report ==============");
        printLine("Total Rooms Available", rooms);
        System.out.println("==========================================");
    }
}

