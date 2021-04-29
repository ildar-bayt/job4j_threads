package ru.job4j.pool;

public class Notification {
    private final String subjectTemplate = "Notification %s to email %s";
    private final String bodyTemplate = "Add a new event to %s";
    private final String subject;
    private final String body;
    private final String email;

    public Notification(User user) {
        this.subject = String.format(subjectTemplate, user.getUsername(), user.getEmail());
        this.body = String.format(bodyTemplate, user.getEmail());
        this.email = user.getEmail();
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public String getEmail() {
        return email;
    }
}