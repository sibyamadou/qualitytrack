package com.github.sibyamadou.qualitytrack.service;

public class BugNotFoundException extends IllegalArgumentException {

    public BugNotFoundException(int id) {
        super("Aucun bug avec l'identifiant " + id);
    }
}