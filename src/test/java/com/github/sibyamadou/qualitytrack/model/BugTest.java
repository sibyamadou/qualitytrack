package com.github.sibyamadou.qualitytrack.model;
import org.junit.jupiter.api.Test;
import  static org.junit.jupiter.api.Assertions.assertEquals;
public class BugTest {
    @Test
    void  newBugShouldBeOpen() {
        Bug bug = new Bug(
                1,
                "Erreur de connexion",
                "L'application plante avec un mauvais mot de passe",
                Priority.HIGH
        );

        assertEquals(BugStatus.OPEN, bug.getStatus());
    }
    @Test
    void openBugShouldNotBeValidatedDirectly() {

        Bug bug = new Bug(
                2,
                "Erreur paiement",
                "Le paiement échoue",
                Priority.CRITICAL
        );

        bug.validate();

        assertEquals(BugStatus.OPEN, bug.getStatus());
    }
    @Test
    void fixedBugShouldBeValidated() {

        Bug bug = new Bug(
                3,
                "Erreur de profil",
                "Impossible de modifier le profil",
                Priority.MEDIUM
        );

        bug.startProgress();
        bug.markAsFixed();
        bug.validate();

        assertEquals(BugStatus.VALIDATED, bug.getStatus());
    }
}
