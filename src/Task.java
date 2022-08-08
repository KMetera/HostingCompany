import java.time.LocalDateTime;

public class Task {
    private int id;
    private String content;
    private LocalDateTime creationDateAndTime;
    private LocalDateTime deadline;

    public Task(int id, String content, LocalDateTime creationDateAndTime, LocalDateTime deadline) {
        this.id = id;
        this.content = content;
        this.creationDateAndTime = creationDateAndTime;
        this.deadline = deadline;
    }
}
