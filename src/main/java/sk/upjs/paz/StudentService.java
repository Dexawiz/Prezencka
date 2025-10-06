package sk.upjs.paz;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class StudentService {

    private final List<Student> students;

    public static List<Student> loadFromResources() throws IOException {
        var csvFilePath = StudentService.class.getResource("students.csv");

        if(csvFilePath == null){
            throw new IllegalStateException("CSV file not found");
        }

        var lines =
                Files.readAllLines(Path.of(csvFilePath.getPath()));

        if (lines.isEmpty()) {
            throw new IllegalStateException("CSV file not found");
        }

        var students = lines.stream()
                .skip(1)
                .map(line ->{
                    var lineParts = line.split(",");
                    var name = lineParts[0];
                    var surname = lineParts[1];
                    var id = Long.parseLong(lineParts[2]);
                    var genderString = lineParts[3];
                    var gender = Student.Gender.Mutant;
                    switch (genderString) {
                        case "M":
                            gender = Student.Gender.Man;
                            break;
                        case "F":
                            gender = Student.Gender.Woman;
                            break;
                    }
                    var birthDate = LocalDate.parse(lineParts[4]);

                    return new Student(id, name, surname, gender, birthDate);
                } ).toList();

        return students;
    }

    public StudentService(List<Student> students) {
        this.students = students;
    }

    public RatioOfGenders ratio() throws IOException {
        int countMan = 0;
        int countWoman = 0;
        int countMutant = 0;
        for (Student s : loadFromResources()){

            switch (s.gender()) {
                case Man ->  countMan++;
                case Woman -> countWoman++;
                case Mutant ->  countMutant++;
                case null, default -> {

                }
            }
        }

        return new RatioOfGenders(countMan, countWoman, countMutant);
    }
}
