package sk.upjs.ics;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class MemorySubjectDao implements SubjectDao {
    private final List<Subject> subjects;

    public MemorySubjectDao(List<Subject> subjects) {
        this.subjects = subjects == null ? List.of() : List.copyOf(subjects);
    }

    public static List<Subject> loadFromResources() throws IOException {
        // load students first to be able to map IDs to Student objects
        var students = MemoryStudentDao.loadFromResources().stream()
                .collect(Collectors.toMap(Student::getId, s -> s));

        var result = new ArrayList<Subject>();
        try (var input = MemorySubjectDao.class.getResourceAsStream("subjects.csv")) {
            if (input == null) {
                throw new IOException("Resource subjects.csv not found");
            }
            var scanner = new Scanner(input);
            if (scanner.hasNextLine()) {
                scanner.nextLine(); // skip header
            }
            while (scanner.hasNextLine()) {
                var line = scanner.nextLine();
                if (line.isBlank()) continue;
                var parts = line.splitWithDelimiters(",", 0);
                var subject = new Subject();
                subject.setId(Long.parseLong(parts[0]));
                subject.setName(parts[2]);
                subject.setYearOfStudy(Integer.parseInt(parts[4]));
                var registeredStudents = Arrays.stream(parts[6].split("_"))
                        .filter(s -> !s.isEmpty())
                        .map(Long::parseLong)
                        .map(students::get)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toSet());
                subject.setStudents(registeredStudents);
                result.add(subject);
            }
        }
        return result;
    }

    @Override
    public List<Subject> findAll() {
        return subjects;
    }

    @Override
    public List<Subject> findById(Long id) {
        if (id == null) return List.of();
        return subjects.stream().filter(s -> id.equals(s.getId())).collect(Collectors.toList());
    }
}
