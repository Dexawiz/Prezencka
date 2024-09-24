package sk.upjs.ics;

import java.util.List;

public class AttendanceService {

    private List<Attendance> attendances;

    public AttendanceService(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    public int averageNumberOfStudents(Subject subject) {
        if (attendances == null) {
            return 0;
        }

        if (attendances.isEmpty()) {
            return 0;
        }

        int sum = 0;
        int count = 0;

        for (Attendance attendance : attendances) {
            if (attendance.getSubject() == null) {
                continue;
            }

            if (attendance.getSubject().equals(subject)) {
                sum += attendance.getAttendees().size();
                count++;
            }
        }

        if (count == 0) {
            return 0;
        }

        return Math.round((float) sum / count);
    }
}
