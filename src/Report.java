
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;


public class Report implements Serializable {
    private enum Status {
        PENDING, ONGOING, COMPLETED;
    }

    private LocalDateTime dateAndTimeOfReceiptOfReport;
    private LocalDateTime dateAndTimeOfEndOfReport;
    private Duration reportLifespan;
    private StringBuilder reportContent;
    private String reportStatus;


    private SubscriberPremium subscriberPremium;
    private TechSupport techSupport;


    public Report(SubscriberPremium subscriberPremium, TechSupport techSupport, StringBuilder reportContent) {
        this.dateAndTimeOfReceiptOfReport = LocalDateTime.now();
        this.reportContent = reportContent;
        this.reportStatus = Status.PENDING.name();
    }




    void closeReport() {
        this.dateAndTimeOfEndOfReport = LocalDateTime.now();
        this.reportLifespan = Duration.between(dateAndTimeOfReceiptOfReport, dateAndTimeOfEndOfReport);
        this.reportStatus = Status.COMPLETED.name();
    }


    @Override
    public String toString() {
        return "dateAndTimeOfReceiptOfReport: " + dateAndTimeOfReceiptOfReport + ", dateAndTimeOfEndOfReport: " + dateAndTimeOfEndOfReport + ", reportLifespan: " + reportLifespan + ", reportContent: '" + reportContent + "', reportStatus: " + reportStatus;
    }
}

