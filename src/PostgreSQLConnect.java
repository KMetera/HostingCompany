import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostgreSQLConnect {
    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String user = "postgres";
    private static final String password = "masy123";

    public static List<Service> getBookedServices(int subscriberId) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            if (connection != null) {

                String query = "SELECT * FROM service JOIN website w ON service.id = w.service_id JOIN subscriber s ON service.subscriber_id = s.id WHERE subscriber_id=? AND ispaid=false";

                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, subscriberId);

                ResultSet result = statement.executeQuery();

                List<Service> bookedServices = new ArrayList<>();
                while (result.next()) {
                    bookedServices.add(new Website(result.getInt("id"), result.getString("ipaddress"), result.getBoolean("ispaid"), result.getString("domainname")));
                }

                query = "SELECT * FROM service JOIN serverftp s on service.id = s.service_id JOIN subscriber s2 on s2.id = service.subscriber_id WHERE s2.id=? AND ispaid=false";

                statement = connection.prepareStatement(query);
                statement.setInt(1, subscriberId);

                result = statement.executeQuery();

                while (result.next()) {
                    bookedServices.add(new ServerFtp(result.getInt("id"), result.getString("ipaddress"), result.getBoolean("ispaid")));
                }

                return bookedServices;


            } else {
                System.out.println("Failed to connect to the database");
                return new ArrayList<>();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static Subscriber getSubscriber(int subscriberId) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            if (connection != null) {

                //Get if subscriber standard
                String query = "SELECT id, username, funds FROM subscriber JOIN subscriberstandard s on subscriber.id = s.subscriber_id WHERE id = ?";

                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, subscriberId);

                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    if ((result.getString("username")) != null) {
                        return new SubscriberStandard(result.getInt("id"), result.getString("username"), result.getDouble("funds"));
                    }
                }

                //Get if subscriber premium
                query = "SELECT id, username, funds FROM subscriber JOIN subscriberpremium s on subscriber.id = s.subscriber_id WHERE id = ?";

                statement = connection.prepareStatement(query);
                statement.setInt(1, subscriberId);

                result = statement.executeQuery();
                while (result.next()) {
                    if ((result.getString("username")) != null) {
                        return new SubscriberPremium(result.getInt("id"), result.getString("username"), result.getDouble("funds"));
                    }
                }

            } else {
                System.out.println("Failed to connect to the database");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public static double getSubscriberFunds(int subscriberId) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            if (connection != null) {

                //Get funds
                String query = "SELECT funds FROM subscriber WHERE id = ?";

                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, subscriberId);

                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    return result.getDouble("funds");
                }

            } else {
                System.out.println("Failed to connect to the database");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public static void addNewService(Subscriber subscriber, Website website) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            if (connection != null) {

                //Get max id+1 from service
                String query = "SELECT (MAX(id)+1) AS max FROM service";
                Statement statementMaxId = connection.createStatement();
                ResultSet result = statementMaxId.executeQuery(query);

                int maxValue = 1;
                while (result.next()) {
                    if ((result.getString("max")) != null) {
                        maxValue = result.getInt("max");
                    }
                }


                String insert1 = "INSERT INTO service (id, ipaddress, ispaid, subscriber_id, server_id) VALUES (?, ?, false, ?, 1)";
                PreparedStatement statement = connection.prepareStatement(insert1);
                statement.setInt(1, maxValue);
                statement.setString(2, website.getIpAddress());
                statement.setInt(3, subscriber.getId());
                statement.executeUpdate();

                String insert2 = "INSERT INTO website (service_id, domainname, price, maxharddrivesize) VALUES (?, ?, ?, ?)";
                statement = connection.prepareStatement(insert2);
                statement.setInt(1, maxValue);
                statement.setString(2, website.getDomainName());
                statement.setDouble(3, website.getPrice());
                statement.setDouble(4, website.getMaxHardDriveSize());
                statement.executeUpdate();
            } else {
                System.out.println("Failed to connect to the database");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void addNewService(Subscriber subscriber, ServerFtp serverFtp) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            if (connection != null) {
                String query = "SELECT (MAX(id)+1) as max FROM service";
                Statement statementMaxId = connection.createStatement();
                ResultSet result = statementMaxId.executeQuery(query);

                int maxValue = 1;
                while (result.next()) {
                    if ((result.getString("max")) != null) {
                        maxValue = result.getInt("max");
                    }
                }


                String insert1 = "INSERT INTO service (id, ipaddress, ispaid, subscriber_id, server_id) VALUES (?, ?, false, ?, 1)";
                PreparedStatement statement = connection.prepareStatement(insert1);
                statement.setInt(1, maxValue);
                statement.setString(2, serverFtp.getIpAddress());
                statement.setInt(3, subscriber.getId());
                statement.executeUpdate();

                String insert2 = "INSERT INTO serverftp (service_id, price, maxharddrivesize) VALUES (?, ?, ?)";
                statement = connection.prepareStatement(insert2);
                statement.setInt(1, maxValue);
                statement.setDouble(2, serverFtp.getPrice());
                statement.setDouble(3, serverFtp.getMaxHardDriveSize());
                statement.executeUpdate();
            } else {
                System.out.println("Failed to connect to the database");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void addNewPaidService(Subscriber subscriber, Service service) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            if (connection != null) {
                LocalDateTime now = LocalDateTime.now();
                Timestamp timestamp = Timestamp.valueOf(now);

                String insert = "INSERT INTO payment (service_id, subscriber_id, dateandtime, currentpayment) VALUES (?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(insert);
                statement.setInt(1, service.getId());
                statement.setInt(2, subscriber.getId());
                statement.setTimestamp(3, timestamp);
                statement.setDouble(4, service.getPrice());
                statement.executeUpdate();

                String update = "UPDATE service SET ispaid = true WHERE id=?";
                statement = connection.prepareStatement(update);
                statement.setInt(1, service.getId());
                statement.executeUpdate();

                String update2 = "UPDATE subscriber SET funds=funds-? WHERE id = ?";
                statement = connection.prepareStatement(update2);
                statement.setDouble(1, service.getPrice());
                statement.setInt(2, subscriber.getId());
                statement.executeUpdate();

            } else {
                System.out.println("Failed to connect to the database");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static boolean checkIfWebsiteExists(String domain) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            if (connection != null) {

                String query = "SELECT * FROM website JOIN service s on s.id = website.service_id WHERE domainname = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, domain);
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    return true;
                }
                return false;
            } else {
                System.out.println("Failed to connect to the database");
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void giveMoneyToSubscriber(Subscriber subscriber, Double moneyValue) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            if (connection != null) {

                String query = "UPDATE subscriber SET funds=funds+? WHERE id = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setDouble(1, moneyValue);
                statement.setInt(2, subscriber.getId());
                statement.executeUpdate();
            } else {
                System.out.println("Failed to connect to the database");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


