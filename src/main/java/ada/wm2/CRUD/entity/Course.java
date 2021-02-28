package ada.wm2.CRUD.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Table(name = "COURSES")
public class Course {

    @Id
    @NotNull(message = "Must enter Course ID")
    Integer courseId;

    @Size(min=2, max=50,  message = "Must enter a Course Name between 2 and 50 characters")
    String courseName;

    @ManyToMany(mappedBy = "courses", fetch = FetchType.EAGER)

    @ToString.Exclude
    List<Student> students;

}

