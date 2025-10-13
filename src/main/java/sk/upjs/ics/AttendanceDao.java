package sk.upjs.ics;

import java.util.List;;

public interface AttendanceDao {
    List<Attendance> findAll();

    Attendance findById(Long id);

    //impliement "auto-increment" of id manually
    //by checking max esxistinf id

    Attendance create(Attendance attendance);


}
