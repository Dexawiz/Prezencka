package sk.upjs.ics;

import java.util.List;

public interface SubjectDao {
    List<Subject> findAll();

    List<Subject> findById(Long id);
}
