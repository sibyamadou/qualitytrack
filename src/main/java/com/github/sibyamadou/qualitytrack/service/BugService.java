package com.github.sibyamadou.qualitytrack.service;
import com.github.sibyamadou.qualitytrack.model.Bug;
import  java.util.ArrayList;
import  java.util.List;
import org.springframework.stereotype.Service;
@Service
public class BugService {
    private final List<Bug> bugs = new ArrayList<>();

    public void addBug(Bug bug) {
        for (Bug existing : bugs) {
            if (existing.getId() == bug.getId()) {
                throw new DuplicateBugException(bug.getId());
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
        throw new BugNotFoundException(id);    }


}
