package tn.esprit.spring.services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Color;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.repositories.IPisteRepository;
import tn.esprit.spring.services.PisteServicesImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class PisteServicesTest {

    @InjectMocks
    private PisteServicesImpl pisteServices; // Concrete implementation of IPisteServices

    @Mock
    private IPisteRepository pisteRepository; // Mock repository

    @BeforeEach
    public void setUp() {
        // No additional setup is needed for this example
    }

    @Test
    public void testAddPiste() {
        Piste piste = new Piste();
        piste.setNamePiste("Test Piste");

        Mockito.when(pisteRepository.save(Mockito.any(Piste.class))).thenReturn(piste);

        Piste savedPiste = pisteServices.addPiste(piste);

        assertNotNull(savedPiste);
        assertEquals("Test Piste", savedPiste.getNamePiste());
    }

    @Test
    public void testRemovePiste() {
        Long pisteId = 1L;

        pisteServices.removePiste(pisteId);

        Mockito.verify(pisteRepository).deleteById(pisteId);
    }

    @Test
    public void testRetrievePiste() {
        Long pisteId = 1L;
        Piste piste = new Piste();
        piste.setNamePiste("Test Piste");

        Mockito.when(pisteRepository.findById(pisteId)).thenReturn(Optional.of(piste));

        Piste retrievedPiste = pisteServices.retrievePiste(pisteId);

        assertNotNull(retrievedPiste);
        assertEquals("Test Piste", retrievedPiste.getNamePiste());
    }
    @Test
    public void testPisteEntity() {
        // Create a Piste object
        Piste piste = new Piste();
        piste.setNumPiste(1L);
        piste.setNamePiste("Test Piste");
        piste.setColor(Color.GREEN);
        piste.setLength(500);
        piste.setSlope(30);

        // Create a set of Skier objects
        Skier skier1 = new Skier();
        skier1.setNumSkier(1L);
        Skier skier2 = new Skier();
        skier2.setNumSkier(2L);

        Set<Skier> skiers = new HashSet<>();
        skiers.add(skier1);
        skiers.add(skier2);

        // Set the skiers for the piste
        piste.setSkiers(skiers);

        // Perform assertions to test the entity fields
        assertEquals(1L, piste.getNumPiste());
        assertEquals("Test Piste", piste.getNamePiste());
        assertEquals(Color.GREEN, piste.getColor());
        assertEquals(500, piste.getLength());
        assertEquals(30, piste.getSlope());

        // Test the skiers association
        assertNotNull(piste.getSkiers());
        assertEquals(2, piste.getSkiers().size());
    }
}
