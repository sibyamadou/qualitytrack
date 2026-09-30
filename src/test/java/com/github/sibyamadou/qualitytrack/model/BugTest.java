package com.github.sibyamadou.qualitytrack.model;
import org.junit.jupiter.api.Test;
import  static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        assertThrows(
                IllegalStateException.class,
                () -> bug.validate()
        );
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
    @Test
    void openBugShouldNotBeFixedDirectly() {

        Bug bug = new Bug(
                4,
                "Erreur panier",
                "Le panier ne calcule pas correctement le total",
                Priority.HIGH
        );

        assertThrows(
                IllegalStateException.class,
                () -> bug.markAsFixed()
        );
    }

    @Test
    void inProgressBugShouldBeFixed() {

        Bug bug = new Bug(
                5,
                "Erreur de recherche",
                "La recherche ne retourne aucun résultat",
                Priority.MEDIUM
        );

        bug.startProgress();

        assertEquals(BugStatus.IN_PROGRESS, bug.getStatus());

        bug.markAsFixed();

        assertEquals(BugStatus.FIXED, bug.getStatus());
    }
    @Test
    void validatedBugShouldNotBeStartedAgain() {
        Bug bug = new Bug(
                6,
                "Erreur de validation",
                "Le resultat  n'est pas validé",
                Priority.MEDIUM
        );
        bug.startProgress();

        assertEquals(BugStatus.IN_PROGRESS, bug.getStatus());

        bug.markAsFixed();

        assertEquals(BugStatus.FIXED, bug.getStatus());
        bug.validate();
        assertEquals(BugStatus.VALIDATED, bug.getStatus());
        assertThrows(
              IllegalStateException.class,
                () -> bug.startProgress()
        );
    }
}
