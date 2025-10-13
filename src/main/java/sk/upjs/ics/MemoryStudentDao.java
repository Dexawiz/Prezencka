package sk.upjs.ics;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MemoryStudentDao implements StudentDao {
    private final List<Student> students;

    public MemoryStudentDao(List<Student> students) {
        this.students = students == null ? List.of() : List.copyOf(students);
    }

    public static List<Student> loadFromResources() throws IOException {
        var result = new ArrayList<Student>();
        try (var input = MemoryStudentDao.class.getResourceAsStream("students.csv")) {
            if (input == null) {
                throw new IOException("Resource students.csv not found");
            }
            var scanner = new Scanner(input);
            if (scanner.hasNextLine()) {
                scanner.nextLine(); // skip header
            }
            while (scanner.hasNextLine()) {
                var line = scanner.nextLine();
                if (line.isBlank()) continue;
                var parts = line.splitWithDelimiters(",", 0);
                var student = new Student();
                student.setName(parts[0]);
                student.setSurname(parts[2]);
                student.setId(Long.parseLong(parts[4]));
                var sexString = parts[6];
                if (Objects.equals(sexString, "M")) {
                    student.setSex(Gender.MALE);
                } else if (Objects.equals(sexString, "F")) {
                    student.setSex(Gender.FEMALE);
                } else {
                    student.setSex(Gender.UNKNOWN);
                }
                student.setBirthDate(LocalDate.parse(parts[8]));
                result.add(student);
            }
        }
        return result;
    }

    @Override
    public List<Student> findAll() {
        return students;
    }

    @Override
    public List<Student> findBySubject(Subject subject) {
        if (subject == null || subject.getStudents() == null || subject.getStudents().isEmpty()) {
            return List.of();
        }
        var set = new HashSet<>(subject.getStudents());
        return students.stream()
                .filter(set::contains)
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> findById(Long id) {
        return students.stream().filter(student -> student.getId().equals(id)).collect(Collectors.toList());
    }
} 

