package com.github.sibyamadou.qualitytrack.model;

public class Bug {

    private int id;
    private String title;
    private String description;
    private Priority priority;
    private BugStatus status;

    public Bug(int id, String title, String description, Priority priority) {
        if (id <= 0) {
            throw new IllegalArgumentException(
                    "L'identifiant d'un bug doit être strictement positif"
            );
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException(
                    "Le titre d'un bug ne peut pas être vide"
            );
        }
        if (priority == null) {
            throw new IllegalArgumentException(
                    "La priorité d'un bug ne peut pas être nulle"
            );
        }

        if (description == null) {
            throw new IllegalArgumentException(
                    "La description d'un bug ne doit pas être nulle"
            );
        }
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        // Tout nouveau bug commence avec avec un statut open
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
        if(this.status != BugStatus.OPEN){
            throw new IllegalStateException(
                    "Un bug ne peut être démarré que s'il est OPEN"
            );
        }
        this.status = BugStatus.IN_PROGRESS;
    }

    public void markAsFixed() {
        if (this.status != BugStatus.IN_PROGRESS) {
            throw new IllegalStateException(
                    "Un bug ne peut être marqué comme corrigé que s'il est IN_PROGRESS"
            );
        }

        this.status = BugStatus.FIXED;
    }

    public void validate() {
        if(this.status != BugStatus.FIXED){
            throw new IllegalStateException(
                    "Un bug ne peut être validé que s'il est FIXED"
            );
        }
        this.status = BugStatus.VALIDATED;

    }

}


