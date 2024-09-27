package sk.upjs.ics;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class AttendanceService {

    private final List<Attendance> attendances;

    public AttendanceService(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    public static AttendanceService fromResources() throws IOException {
        var students = new HashMap<Long, Student>();
        try (var studentsInputStream = AttendanceService.class.getResourceAsStream("students.csv")) {
            var scanner = new Scanner(studentsInputStream);
            scanner.nextLine(); // skip header
            while (scanner.hasNextLine()) {
                var line = scanner.nextLine();
                if (line.isBlank()) {
                    continue;
                }

                var parts = line.splitWithDelimiters(",", 0);
                var student = new Student();
                student.setName(parts[0]);
                student.setSurname(parts[2]);
                student.setId(Long.parseLong(parts[4]));
                var sexString = parts[6];
                switch (sexString) {
                    case "M":
                        student.setSex(Gender.MALE);
                    case "F":
                        student.setSex(Gender.FEMALE);
                    default:
                        student.setSex(Gender.UNKNOWN);
                }
                student.setBirthDate(LocalDate.parse(parts[8]));
                students.put(student.getId(), student);
            }
        }

        var subjects = new HashMap<Long, Subject>();
        try (var subjectsInputStream = AttendanceService.class.getResourceAsStream("subjects.csv")) {
            var scanner = new Scanner(subjectsInputStream);
            scanner.nextLine(); // skip header
            while (scanner.hasNextLine()) {
                var line = scanner.nextLine();
                if (line.isBlank()) {
                    continue;
                }

                var parts = line.splitWithDelimiters(",", 0);
                var subject = new Subject();
                subject.setId(Long.parseLong(parts[0]));
                subject.setName(parts[2]);
                subject.setYearOfStudy(Integer.parseInt(parts[4]));
                var registeredStudents = Arrays.stream(parts[6].split("_"))
                        .filter(s -> !s.isEmpty())
                        .map(Long::parseLong)
                        .map(students::get)
                        .collect(Collectors.toSet());

                subject.setStudents(registeredStudents);
                subjects.put(subject.getId(), subject);
            }
        }

        var attendances = new ArrayList<Attendance>();
        try (var attendancesInputStream = AttendanceService.class.getResourceAsStream("attendances.csv")) {
            var scanner = new Scanner(attendancesInputStream);
            scanner.nextLine(); // skip header
            while (scanner.hasNextLine()) {
                var line = scanner.nextLine();
                if (line.isBlank()) {
                    continue;
                }

                var parts = line.splitWithDelimiters(",",0);
                var attendance = new Attendance();
                attendance.setId(Long.parseLong(parts[0]));
                attendance.setDateTime(LocalDateTime.parse(parts[2]));
                attendance.setSubject(subjects.get(Long.parseLong(parts[4])));
                var attendees = Arrays.stream(parts[6].split("_"))
                        .filter(s -> !s.isEmpty())
                        .map(Long::parseLong)
                        .map(students::get)
                        .collect(Collectors.toSet());

                attendance.setAttendees(attendees);
                attendances.add(attendance);
            }
        }

        return new AttendanceService(attendances);
    }

    public int averageNumberOfStudents(String subjectName) {
        if (attendances == null) {
            return 0;
        }

        if (attendances.isEmpty()) {
            return 0;
        }

        int sum = 0;
        int count = 0;

        for (Attendance attendance : attendances) {
            if (attendance.getSubject() == null) {
                continue;
            }

            if (attendance.getSubject().getName().equals(subjectName)) {
                sum += attendance.getAttendees().size();
                count++;
            }
        }

        if (count == 0) {
            return 0;
        }

        return Math.round((float) sum / count);
    }
}
