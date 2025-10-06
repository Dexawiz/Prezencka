package sk.upjs.paz;

import lombok.With;

import java.time.LocalDate;

@With
public record Student(Long id,
                      String name,
                      String surname,
                      Gender gender,
                      LocalDate birthdate)
{


    public enum Gender{
        Man, Woman, Mutant;
    }

}



