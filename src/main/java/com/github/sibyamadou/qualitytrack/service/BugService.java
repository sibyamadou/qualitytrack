package com.github.sibyamadou.qualitytrack.service;
import com.github.sibyamadou.qualitytrack.model.Bug;
import  java.util.ArrayList;
import  java.util.List;
public class BugService {
    private final List<Bug> bugs = new ArrayList<>();

    public void addBug(Bug bug) {
        for (Bug existing : bugs) {
            if (existing.getId() == bug.getId()) {
                throw new IllegalArgumentException(
                        "Un bug avec l'identifiant " + bug.getId() + " existe déjà"
                );
            }
        }
        bugs.add(bug);
    }

    public List<Bug> getAllBugs() {
        return List.copyOf(bugs);
    }

    public Bug getBugById(int id) {
        for (Bug bug : bugs) {
            if (bug.getId() == id) {
                return bug;
            }
        }
        throw new IllegalArgumentException("Aucun bug avec l'identifiant " + id);
    }

}
