package sk.upjs.paz;

import lombok.With;

import java.util.Set;

@With
public record Subject(Long id,
                      String name,
                      int yeaOfStudies,
                      Set<Student> students) {
}
