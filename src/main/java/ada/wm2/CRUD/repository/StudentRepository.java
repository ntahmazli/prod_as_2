package ada.wm2.CRUD.repository;

import ada.wm2.CRUD.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, Integer> {

    @Query(value = "select * from STUDENTS where FIRST_NAME like %:first", nativeQuery=true)
    List<Student>getFirstName(String first);

    @Query(value = "select * from STUDENTS where LAST_NAME like %:last", nativeQuery=true)
    List<Student>getSurname(String last);

    @Query(value = "select * from STUDENTS where MAJOR like %:major", nativeQuery = true)
    List<Student> getMajor(String major);

    @Query(value = "Select * from STUDENTS", nativeQuery = true)
    Iterable<Student> getAllStudentInfo();

    @Query(value = "Select * from STUDENTS where ST_ID like :id", nativeQuery = true)
    Optional<Student> getStudentId(Integer id);


}


