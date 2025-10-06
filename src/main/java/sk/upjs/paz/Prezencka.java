package sk.upjs.paz;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public record Prezencka(Long id,
                        LocalDateTime date,
                        Subject subject,
                        Set<Student> attendees) {

}
