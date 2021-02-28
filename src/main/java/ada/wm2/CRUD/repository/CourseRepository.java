package ada.wm2.CRUD.repository;

import ada.wm2.CRUD.entity.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CourseRepository extends CrudRepository<Course, Integer> {

    @Query(value = "select * from COURSES where COURSE_NAME like %:courseName", nativeQuery=true)
    Optional<Course>getCourseName(String courseName);

    @Query(value = "Select * from COURSES", nativeQuery = true)
    Iterable<Course> getAllCourseInfo();

    @Query(value = "Select * from COURSES where COURSE_ID like :courseId", nativeQuery = true)
    Optional<Course> getCourseId(Integer courseId);

}
