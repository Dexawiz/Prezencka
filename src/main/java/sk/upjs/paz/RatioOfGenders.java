package sk.upjs.paz;


public record RatioOfGenders(int man,
                             int woman,
                             int mutant) {

    public double ratioToMenFromWomen (int man, int woman, int mutant) {
        var allStudents = man + woman + mutant;

        return (double) man / (double) allStudents;
    }

    public double ratioToWomenToMen (int man, int woman, int mutant) {
        var allStudents = man + woman + mutant;

        return (double) woman / (double) allStudents;
    }

}
