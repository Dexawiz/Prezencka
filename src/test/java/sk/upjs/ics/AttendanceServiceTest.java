package sk.upjs.ics;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
class AttendanceServiceTest {

    @org.junit.jupiter.api.Test
    void averageNumberOfStudents_null() {
        var service = new AttendanceService(null);
        var got = service.averageNumberOfStudents(null);
        assertEquals(0, got);
    }

    @org.junit.jupiter.api.Test
    void averageNumberOfStudents_nullSubject() {
        var service = new AttendanceService(List.of(new Attendance()));
        var got = service.averageNumberOfStudents(null);
        assertEquals(0, got);
    }

    @org.junit.jupiter.api.Test
    void averageNumberOfStudents_empty() {
        var service = new AttendanceService(List.of(new Attendance()));
        var got = service.averageNumberOfStudents(null);
        assertEquals(0, got);
    }

    @org.junit.jupiter.api.Test
    void averageNumberOfStudents_happyPath() {
        var janka = new Student();
        janka.setName("Janka");
        var danka = new Student();
        danka.setName("Danka");
        var ferko = new Student();
        ferko.setName("Ferko");

        var paz1c = new Subject();
        paz1c.setName("PAZ1C");

        var dbs1a = new Subject();
        dbs1a.setName("DBS1A");

        var attendance1 = new Attendance();
        attendance1.setSubject(paz1c);
        attendance1.setAttendees(Set.of(janka, danka));

        var attendance2 = new Attendance();
        attendance2.setSubject(paz1c);
        attendance2.setAttendees(Set.of(janka));

        var attendance3 = new Attendance();
        attendance3.setSubject(dbs1a);
        attendance3.setAttendees(Set.of(janka, danka, ferko));

        var service = new AttendanceService(List.of(attendance1, attendance2, attendance3));
        var got = service.averageNumberOfStudents(paz1c);

        assertEquals(2, got);
    }
}