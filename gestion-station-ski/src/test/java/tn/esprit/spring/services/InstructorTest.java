
package tn.esprit.spring.services;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IInstructorRepository;
import tn.esprit.spring.services.InstructorServicesImpl;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InstructorTest {

    @Mock
    private IInstructorRepository instructorRepository;

    @Mock
    private ICourseRepository courseRepository;

    @InjectMocks
    private InstructorServicesImpl instructorService;

    //aaaaaaaaaaa

    @Test
    public void testAddInstructor() {
        // Créer un instructeur fictif pour le test
        Instructor instructor = new Instructor(null, "John", "Doe", LocalDate.now(), null);

        // Définir le comportement attendu lorsque save est appelé sur le repository
        when(instructorRepository.save(any(Instructor.class))).thenReturn(instructor);

        // Appeler la méthode du service pour ajouter un instructeur
        Instructor result = instructorService.addInstructor(instructor);

        // Vérifier que la méthode save du repository a été appelée une fois
        verify(instructorRepository, Mockito.times(1)).save(any(Instructor.class));

        // Vérifier que l'instructeur renvoyé par le service est le même que celui renvoyé par le repository
        assertEquals(instructor, result);
    }


    @Test
    public void testRetrieveAllInstructors() {
        // Créer une liste fictive d'instructeurs pour le test
        List<Instructor> instructors = new ArrayList<>();
        instructors.add(new Instructor(1L, "John", "Doe", LocalDate.now(), null));
        instructors.add(new Instructor(2L, "Jane", "Doe", LocalDate.now(), null));

        // Définir le comportement attendu lorsque findAll est appelé sur le repository
        when(instructorRepository.findAll()).thenReturn(instructors);

        // Appeler la méthode du service pour récupérer tous les instructeurs
        List<Instructor> result = instructorService.retrieveAllInstructors();

        // Vérifier que la méthode findAll du repository a été appelée une fois
        verify(instructorRepository, Mockito.times(1)).findAll();

        // Vérifier que la liste d'instructeurs renvoyée par le service est la même que celle renvoyée par le repository
        assertEquals(instructors, result);
    }

    // Ajoutez d'autres méthodes de test pour les autres fonctionnalités, par exemple testRemoveInstructor, testUpdateInstructor






}
