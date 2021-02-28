package ada.wm2.CRUD.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Table(name="STUDENTS")
public class Student{
    @Id
    @NotNull(message = "Must enter Student ID")
    Integer stId;

    @Size(min=2, max=50, message = "Must enter a Name between 2 and 50 characters")
    String firstName;

    @Size(min=2, max=50, message = "Must enter a Surname between 2 and 50 characters")
    String lastName;

    @Size(min=2, max=5, message = "Must enter a Major between 2 and 50 characters")
    String major;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="ENROLLMENTS"
            , joinColumns = @JoinColumn(name = "ST_ID")
            , inverseJoinColumns = @JoinColumn(name = "COURSE_ID"))

            @ToString.Exclude
    List<Course> courses;


}
