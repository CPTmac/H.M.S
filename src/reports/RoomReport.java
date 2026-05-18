package reports;

public class RoomReport extends Report {

    private int rooms;

    public RoomReport(int rooms) {
        this.rooms = rooms;
    }

    @Override
    public void generate() {

        System.out.println("===== ROOM REPORT =====");
        System.out.println("Available Rooms: " + rooms);
    }
}
