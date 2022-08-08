public class Website extends Service {
    private static double price = 99;
    private static double maxHardDriveSize = 32;

    private String domainName;

    public Website(String domainName) {
        super();
        this.domainName = domainName;
    }

    public Website(int id, String ipAddress, boolean isPaid, String domainName) {
        super(id, ipAddress, isPaid);
        this.domainName = domainName;
    }

    public String getDomainName() {
        return domainName;
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
        return "Website: " + domainName + ".pl, " + super.toString();
    }
}
