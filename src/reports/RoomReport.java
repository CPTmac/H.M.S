package reports;

public class RoomReport extends Report {

    private final int rooms;

    public RoomReport(int rooms) {
        this.rooms = rooms;
    }

    @Override
    public String generate() {
        StringBuilder output = new StringBuilder();

        output.append("============== Room Report ==============\n");
        output.append(formatLine("Total Rooms Available", rooms));
        output.append("==========================================\n");

        return output.toString();
    }
}

