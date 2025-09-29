package sk.upjs.paz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void testRatio() {
        Set<Student> testStudents = Set.of(
                new Student(1L,
                        "Ivan",
                        "Medv",
                        Student.Gender.Man,
                        LocalDate.now()),
                new Student(2L,
                        "dcsdc",
                        "sdasd",
                        Student.Gender.Woman,
                        LocalDate.now()),
                new Student(3L,
                        "Someone",
                        "Someone",
                        Student.Gender.Mutant,
                        LocalDate.now())
        );

        var studentService = new StudentService(testStudents);
        var vysledok = studentService.ratio();

        var spravnyVysledok = new RatioOfGenders(
                1, 1, 1);

        assertEquals(spravnyVysledok, vysledok);

    }
}