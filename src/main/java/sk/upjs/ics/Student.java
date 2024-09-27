package sk.upjs.ics;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Student {
    private Long id;
    private String name;
    private String surname;
    private Gender sex;
    private LocalDate birthDate;
}
