package com.example.sarath.cseiimdpjntuk;

public class Alertobj {
    String subject;
    String description;
    String deadline;
    public Alertobj(){

    }
    public Alertobj(String subject,String description,String deadline)
    {
        this.subject = subject;
        this.description = description;
        this.deadline = deadline;
    }

    public String getSubject() {
        return subject;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getDescription() {
        return description;
    }
}
