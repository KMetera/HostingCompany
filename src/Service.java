public abstract class Service {
    protected int id;
    protected String ipAddress;
    protected boolean isPaid = false;

    public Service() {
        ipAddress = generateIpAddress();
    }

    public Service(int id, String ipAddress, boolean isPaid) {
        this.id = id;
        this.ipAddress = ipAddress;
        this.isPaid = isPaid;
    }

    public abstract double getPrice();

    public abstract double getMaxHardDriveSize();

    public int getId() {
        return this.id;
    }

    protected String generateIpAddress() {
        return (int) (100 + Math.random() * 155) + "." + (int) (100 + Math.random() * 155) + "." + (int) (100 + Math.random() * 155) + "." + (int) (100 + Math.random() * 155);
    }

    protected void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    @Override
    public String toString() {
        return ipAddress;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }
}
