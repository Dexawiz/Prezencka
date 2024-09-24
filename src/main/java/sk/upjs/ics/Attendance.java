package sk.upjs.ics;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class Attendance {
    private Long id;
    private LocalDateTime dateTime;
    private Subject subject;
    private Set<Student> attendees;
}
