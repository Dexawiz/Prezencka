package sk.upjs.paz;

import lombok.With;

import java.time.LocalDate;

@With
public record Student(Long id,
                      String name,
                      String surname,
                      Gender sex,
                      LocalDate dob)
{


    public enum Gender{
        Man, Woman, Mutant;
    }

}



