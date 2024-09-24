package sk.upjs.ics;

import lombok.Data;

import java.util.Set;

@Data
public class Subject {
    private Long id;
    private String name;
    private int yearOfStudy;
    private Set<Student> students;
}
