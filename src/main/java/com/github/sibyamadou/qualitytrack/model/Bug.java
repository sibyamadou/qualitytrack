package com.github.sibyamadou.qualitytrack.model;

public class Bug {

    private int id;
    private String title;
    private String description;
    private Priority priority;
    private BugStatus status;

    public Bug(int id, String title, String description, Priority priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = BugStatus.OPEN;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Priority getPriority() {
        return priority;
    }

    public BugStatus getStatus() {
        return status;
    }

    public void startProgress() {
        this.status = BugStatus.IN_PROGRESS;
    }

    public void markAsFixed() {
        this.status = BugStatus.FIXED;
    }

    public void validate() {
        this.status = BugStatus.VALIDATED;
    }

}


