package com.github.sibyamadou.qualitytrack.model;
import org.junit.jupiter.api.Test;
import  static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
public class BugTest {
    private Bug bug;
    @BeforeEach
    void setUp() {
        bug = new Bug(
                1,
                "Erreur de connexion",
                "L'application plante avec un mauvais mot de passe",
                Priority.HIGH
        );
    }

    @Test
    void  newBugShouldBeOpen() {
        assertEquals(BugStatus.OPEN, bug.getStatus());
    }
    @Test
    void openBugShouldNotBeValidatedDirectly() {
        assertThrows(
                IllegalStateException.class,
                () -> bug.validate()
        );
    }
    @Test
    void fixedBugShouldBeValidated() {
        bug.startProgress();
        bug.markAsFixed();
        bug.validate();

        assertEquals(BugStatus.VALIDATED, bug.getStatus());
    }
    @Test
    void openBugShouldNotBeFixedDirectly() {
        assertThrows(
                IllegalStateException.class,
                () -> bug.markAsFixed()
        );
    }

    @Test
    void inProgressBugShouldBeFixed() {

        bug.startProgress();

        assertEquals(BugStatus.IN_PROGRESS, bug.getStatus());

        bug.markAsFixed();

        assertEquals(BugStatus.FIXED, bug.getStatus());
    }
    @Test
    void validatedBugShouldNotBeStartedAgain() {

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
    @Test
    void bugShouldNotBeCreatedWithEmptyTitle() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Bug(
                        7,
                        "",
                        "Erreur dans l'application",
                        Priority.HIGH
                )
        );
    }
    @Test
    void bugShouldNotBeCreatedWithNullTitle() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Bug(
                        8,
                        null,
                        "Erreur dans l'application",
                        Priority.HIGH
                )
        );
    }
    @Test
    void bugShouldNotBeCreatedWithBlankTitle() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Bug(
                        9,
                        "     ",
                        "Erreur dans l'application",
                        Priority.HIGH
                )
        );
    }

    @Test
    void bugShouldNotBeCreatedWithNullPriority() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Bug(
                        10,
                        "Erreur de connexion",
                        "Impossible de se connecter",
                        null
                )
        );
    }
    @Test
    void bugShouldNotBeCreatedWithInvalidId() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Bug(
                        0,
                        "Erreur de connexion",
                        "Impossible de se connecter",
                        Priority.HIGH
                )
        );
    }

    @Test
    void bugShouldNotBeCreatedWithNullDescription() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Bug(
                        11,
                        "Erreur de connexion",
                        null,
                        Priority.HIGH
                )
        );
    }
}
