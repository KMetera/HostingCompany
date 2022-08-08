public class Server {
    private int id;
    private String name;
    private String location;
    private double maxRamSize;
    private double maxHardDriveSize;

    public Server(int id, String name, String location, double maxRamSize, double maxHardDriveSize) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.maxRamSize = maxRamSize;
        this.maxHardDriveSize = maxHardDriveSize;
    }

    public String getServerInformation() {
        return "Id: " + id + "\nName: " + name + "\nLocation: " + location + "\nMax ram size: " + maxRamSize + " GB\nMax hard drive size: " + maxHardDriveSize + " GB";
    }

    private void turnOff(){}
    private void turnOn(){}
    private void createBackup(){}


}
