package com.github.sibyamadou.qualitytrack.controller;

import com.github.sibyamadou.qualitytrack.model.Bug;
import com.github.sibyamadou.qualitytrack.service.BugService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@RestController
@RequestMapping("/bugs")
public class BugController {
    private final BugService bugService;

    public BugController(BugService bugService) {
        this.bugService = bugService;
    }

    @GetMapping
    public List<Bug> getAllBugs() {
        return bugService.getAllBugs();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Bug createBug(@RequestBody CreateBugRequest request) {
        Bug bug = new Bug(
                request.id(),
                request.title(),
                request.description(),
                request.priority()
        );
        bugService.addBug(bug);
        return bug;
    }

    @GetMapping("/{id}")
    public Bug getBug(@PathVariable int id) {
        return bugService.getBugById(id);
    }
}
