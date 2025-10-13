package sk.upjs.ics;


import java.util.List;


public interface StudentDao {
    List<Student> findAll();

    List<Student> findBySubject(Subject subject);

    List<Student> findById(Long id);
}
