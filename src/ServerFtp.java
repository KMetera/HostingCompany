public class ServerFtp extends Service {
    private static double price = 119;
    private static double maxHardDriveSize = 512;

    public ServerFtp(){
        super();
    }

    public ServerFtp(int id, String ipAddress, boolean isPaid) {
        super(id, ipAddress, isPaid);
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public double getMaxHardDriveSize() {
        return maxHardDriveSize;
    }

    @Override
    public String toString() {
        return "Server FTP: " + super.toString();
    }
}
