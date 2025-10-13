package sk.upjs.ics;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class MemoryAttendanceDao implements AttendanceDao {
    private List<Attendance> storage;

    public MemoryAttendanceDao(List<Attendance> storage) {
        this.storage = storage;
    }

    public static List<Attendance> loadFromResources() throws IOException {
        // load students and subjects to map IDs to instances
        var students = MemoryStudentDao.loadFromResources().stream()
                .collect(Collectors.toMap(Student::getId, s -> s));
        var subjects = MemorySubjectDao.loadFromResources().stream()
                .collect(Collectors.toMap(Subject::getId, s -> s));

        var result = new ArrayList<Attendance>();
        try (var input = MemoryAttendanceDao.class.getResourceAsStream("attendances.csv")) {
            if (input == null) {
                throw new IOException("Resource attendances.csv not found");
            }
            var scanner = new Scanner(input);
            if (scanner.hasNextLine()) {
                scanner.nextLine(); // skip header
            }
            while (scanner.hasNextLine()) {
                var line = scanner.nextLine();
                if (line.isBlank()) continue;
                var parts = line.splitWithDelimiters(",", 0);
                var attendance = new Attendance();
                attendance.setId(Long.parseLong(parts[0]));
                attendance.setDateTime(LocalDateTime.parse(parts[2]));
                attendance.setSubject(subjects.get(Long.parseLong(parts[4])));
                var attendees = Arrays.stream(parts[6].split("_"))
                        .filter(s -> !s.isEmpty())
                        .map(Long::parseLong)
                        .map(students::get)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toSet());
                attendance.setAttendees(attendees);
                result.add(attendance);
            }
        }
        return result;
    }

    @Override
    public List<Attendance> findAll() {
        return storage == null ? List.of() : Collections.unmodifiableList(storage);
    }

    @Override
    public Attendance findById(Long id) {
        if (id == null || storage == null) return null;
        for (Attendance a : storage) {
            if (id.equals(a.getId())) return a;
        }
        return null;
    }

    @Override
    public Attendance create(Attendance attendance) {
        if (attendance == null) {
            throw new NullPointerException("Attendance is null");
        };
        if(attendance.getId() != null){
            throw new IllegalArgumentException("Attendance id is null");
        }
        if (storage == null) storage = new ArrayList<>();
        long nextId = storage.stream()
                .map(Attendance::getId)
                .filter(Objects::nonNull)
                .mapToLong(Long::longValue)
                .max()
                .orElse(0L) + 1;
        if (attendance.getId() == null || findById(attendance.getId()) != null) {
            attendance.setId(nextId);
        }
        storage.add(attendance);
        return attendance;
    }
}
