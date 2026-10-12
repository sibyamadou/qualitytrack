package com.github.sibyamadou.qualitytrack.controller;
import com.github.sibyamadou.qualitytrack.model.Priority;

public record CreateBugRequest(int id, String title, String description, Priority priority) {
}
