package sk.upjs.paz;

import java.util.Set;

public class StudentService {
    private Set<Student> student;

    public RatioOfGenders ratio(){
        int countMan = 0;
        int countWoman = 0;
        int countMutant = 0;
        for (Student s : student){

            switch (s.sex()) {
                case Man ->  countMan++;
                case Woman -> countWoman++;
                case Mutant ->  countMutant++;
                case null, default -> {

                }
            }
        }

        return new RatioOfGenders(countMan, countWoman, countMutant);
    }
}
