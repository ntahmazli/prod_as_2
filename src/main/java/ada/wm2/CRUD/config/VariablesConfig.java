package ada.wm2.CRUD.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.String;

@Configuration
public class VariablesConfig {

    @Bean
    public String studentAddedMessage(){

        return new String("Student Added! Click 'View All Students' link to see the changes.");
    }

    @Bean
    public String studentUpdatedMessage(){

        return new String("Student Updated! Click 'View All Students' link to see the changes.");
    }

    @Bean
    public String courseAddedMessage(){

        return new String("Course Added! Click 'View All Courses' link to see the changes.");
    }

    @Bean
    public String findByMajorErrorMessage(){

        return new String("Sorry, but a student with this Major does not exist.");
    }

    @Bean
    public String deleteStudentSuccessMessage(){

        return new String("Student Deleted! Click 'View All Students' link to see the changes.");
    }

    @Bean
    public String courseDeletedSuccessMessage(){

        return new String("Course Deleted! Click 'View All Courses' link to see the changes.");
    }

    @Bean
    public String courseUpdatedSuccessMessage(){

        return new String("Course Updated! Click 'View All Courses' link to see the changes.");
    }

    @Bean
    public String courseDeletedErrorMessage(){

        return new String("You cannot delete this course, because a student is enrolled in it.");
    }
}
