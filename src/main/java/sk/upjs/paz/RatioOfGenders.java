package sk.upjs.paz;


public record RatioOfGenders(int man,
                             int woman,
                             int mutant) {

    public double ratioToMen (int man, int woman, int mutant) {
        var allStudents = man + woman + mutant;

        return (double) man / (double) allStudents;
    }

    public double ratioToWomen(int man, int woman, int mutant) {
        var allStudents = man + woman + mutant;

        return (double) woman / (double) allStudents;
    }

    public double ratioToMutants(int man, int woman, int mutant) {
        var allStudents = man + woman + mutant;
        return (double) mutant / (double) allStudents;
    }

}
