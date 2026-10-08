package com.github.sibyamadou.qualitytrack.service;

import com.github.sibyamadou.qualitytrack.model.Bug;
import com.github.sibyamadou.qualitytrack.model.Priority;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertSame;

public class BugServiceTest {

    private BugService service;

    @BeforeEach
    void setUp() {
        service = new BugService();
    }

    @Test
    void newServiceShouldHaveNoBugs() {
        assertTrue(service.getAllBugs().isEmpty());
    }

    @Test
    void addedBugShouldAppearInTheList() {
        Bug bug = new Bug(1, "Erreur de connexion", "L'application plante", Priority.HIGH);

        service.addBug(bug);

        assertEquals(1, service.getAllBugs().size());
        assertEquals(bug, service.getAllBugs().get(0));
    }

    @Test
    void existingBugShouldBeFoundById() {
        Bug first = new Bug(1, "Erreur de connexion", "L'application plante", Priority.HIGH);
        Bug second = new Bug(2, "Erreur paiement", "Le paiement échoue", Priority.CRITICAL);
        service.addBug(first);
        service.addBug(second);

        Bug found = service.getBugById(2);

        assertSame(second, found);
    }

    @Test
    void unknownBugIdShouldBeRefused() {
        Bug bug = new Bug(1, "Erreur de connexion", "L'application plante", Priority.HIGH);
        service.addBug(bug);

        assertThrows(
                IllegalArgumentException.class,
                () -> service.getBugById(9)
        );
    }
    @Test
    void bugWithSameIdShouldBeRefused() {
        Bug first = new Bug(1, "Erreur de connexion", "L'application plante", Priority.HIGH);
        Bug duplicate = new Bug(1, "Erreur paiement", "Le paiement échoue", Priority.CRITICAL);
        service.addBug(first);

        assertThrows(
                IllegalArgumentException.class,
                () -> service.addBug(duplicate)
        );
    }
}