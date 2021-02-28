package ada.wm2.CRUD.controller;

import ada.wm2.CRUD.entity.Course;
import ada.wm2.CRUD.entity.Student;
import ada.wm2.CRUD.exception.CourseException;
import ada.wm2.CRUD.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/app")
public class CourseController {
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    @Qualifier("courseAddedMessage")
    String courseAddedMessage;

    @Autowired
    @Qualifier("courseUpdatedSuccessMessage")
    String courseUpdatedSuccessMessage;

    @Autowired
    @Qualifier("courseDeletedSuccessMessage")
    String courseDeletedSuccessMessage;

    @Autowired
    @Qualifier("courseDeletedErrorMessage")
    String courseDeletedErrorMessage;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/course")
    public String getCoursePage(Model model) throws CourseException{
        logger.info("Entered getCoursePage(): no errors " );

        return "/course";
    }

    @GetMapping("/newC")
    public String newCoursePage(Model model) throws CourseException{
        logger.info("Entered newCoursePage(): no errors " );
        model.addAttribute("course", new Course());
        return "/newCourse";

    }

    @GetMapping("/findCourse")
    public String findCourse(){
        logger.info("Entered findCourse(): no errors " );

        return "/findCourse";

    }

    @GetMapping("/courseList")
    public String courseList(Model model) throws CourseException{
        logger.info("Entered courseList(): no errors " );
        Iterable<Course> courses = courseRepository.getAllCourseInfo();
        model.addAttribute("courses", courses);
        logger.info("courseList(): got " + courseRepository.getAllCourseInfo());

        return "/courseList";

    }

    @GetMapping("/findByCourseId")
    public String findByCourseId(Model model, @RequestParam Integer courseId) throws CourseException{

        logger.info("Entered findByCourseId(): no errors " );
        Optional<Course> result = courseRepository.getCourseId(courseId);

        if(result.isPresent()){
            Course course = result.get();
            List<Course> cList = new ArrayList<Course>();
            cList.add(course);
            logger.info("findByCourseId(): found " + courseRepository.getCourseId(courseId));
            model.addAttribute("courses", cList);
        }
        return "/courseList";
    }

    @GetMapping("/course/update")
    public String showCourseUpdatePage(Model model,@RequestParam Integer cId) {
        Optional<Course> result = courseRepository.getCourseId(cId);
        if (result.isPresent()) {
            Course course = result.get();
            model.addAttribute("course",course);
//
//            Iterable<Course> courses = courseRepository.getAllCourseInfo();
//            model.addAttribute("allCourses",courses);

        }

        return "/updateCourse";
    }

    @GetMapping("/findByCourseName")
    public String findByCourseName(Model model, @RequestParam String courseName) throws CourseException{
        logger.info("Entered findByCourseName(): no errors " );

        Optional<Course> result = courseRepository.getCourseName(courseName);

        if(result.isPresent()){
            Course course = result.get();
            List<Course> cList = new ArrayList<Course>();
            cList.add(course);
            logger.info("findByCourseName(): found " + courseRepository.getCourseName(courseName));
            model.addAttribute("courses", cList);
        }
        return "/courseList";
    }

    @PostMapping("/saveCourse")
    public String saveCourse(Model model, @Valid Course courseData, BindingResult bindingR) throws CourseException{

        logger.info("Entered saveCourse(): no errors " );
        if (bindingR.hasErrors()){

            return "/newCourse";
        }
        courseRepository.save(courseData);

        model.addAttribute("courseAddedMessage", courseAddedMessage);

        return "/course";

    }

    @PostMapping("/updateCourse")
    public String updateCourse(Model model, @Valid Course courseData, BindingResult bindingR) throws CourseException{

        logger.info("Entered updateCourse(): no errors " );
        if (bindingR.hasErrors()){

            return "/updateCourse";
        }
        courseRepository.save(courseData);

        model.addAttribute("courseUpdated", courseUpdatedSuccessMessage);

        return "/course";

    }


    @GetMapping("/deleteCourse")
    public String deleteStudent() {
        logger.info("Entered deleteStudent(): no errors " );

        return "/deleteCourse";
    }

    @GetMapping("/deleteCr")
    public String deleteCourseById(Model model, @RequestParam Integer courseId) throws CourseException{

        try {
            logger.info("Entered deleteCourseById(): no errors " );
            Optional<Course> result = courseRepository.getCourseId(courseId);

            courseRepository.delete(result.get());
            logger.info("deleteCourseById(): deleted " + courseRepository.getCourseId(courseId));
            model.addAttribute("courseDeletedSuccess", courseDeletedSuccessMessage);
        }
        catch (Exception exc){
            logger.error("deleteCourseById(): an error occurred for courseId " + courseRepository.getCourseId(courseId));
            model.addAttribute("courseDeletedError", courseDeletedErrorMessage);
        }

        return "/course";
    }
//
//
//    @GetMapping("/deleteCrName")
//    public String deleteCourseByName(Model model, @RequestParam String courseName) throws CourseException{
//
//        try {
//            logger.info("Entered deleteCourseByName(): no errors " );
//            Optional<Course> result = courseRepository.getCourseName(courseName);
//
//            courseRepository.delete(result.get());
//            logger.info("deleteCourseByName(): deleted " + courseRepository.getCourseName(courseName));
//            model.addAttribute("courseDeletedSuccess", courseDeletedSuccessMessage);
//        }
//        catch (Exception exc){
//            logger.error("deleteCourseByName(): an error occurred for courseName " + courseRepository.getCourseName(courseName));
//
//            model.addAttribute("courseDeletedError", courseDeletedErrorMessage);
//        }
//        logger.warn("Course you entered cannot be deleted" );
//
//        return "/course";
//    }
//



}
