package com.github.sibyamadou.qualitytrack.service;

public class DuplicateBugException extends IllegalArgumentException {

    public DuplicateBugException(int id) {
        super("Un bug avec l'identifiant " + id + " existe déjà");
    }
}