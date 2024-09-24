package sk.upjs.ics;

import java.util.List;

public class AttendanceService {

    private List<Attendance> attendances;

    public AttendanceService(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    public int averageNumberOfStudents(Subject subject) {
        int sum = 0;
        int count = 0;
        for (Attendance attendance : attendances) {
            if (attendance.getSubject().equals(subject)) {
                sum += attendance.getAttendees().size();
                count++;
            }
        }
        return sum / count;
    }
}
