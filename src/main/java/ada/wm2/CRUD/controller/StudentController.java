package ada.wm2.CRUD.controller;

import ada.wm2.CRUD.entity.Course;
import ada.wm2.CRUD.entity.Student;
import ada.wm2.CRUD.exception.StudentException;
import ada.wm2.CRUD.repository.StudentRepository;
import ada.wm2.CRUD.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/app")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;


    @Autowired
    @Qualifier("studentAddedMessage")
    String studentAddedMessage;

    @Autowired
    @Qualifier("studentUpdatedMessage")
    String studentUpdatedMessage;

    @Autowired
    @Qualifier("findByMajorErrorMessage")
    String findByMajorError;

    @Autowired
    @Qualifier("deleteStudentSuccessMessage")
    String studentDeleteSuccess;

    Logger logger = LoggerFactory.getLogger(this.getClass());


    @GetMapping("/")
    public String getWelcomePage() throws StudentException{
        logger.info("Entered getWelcomePage(): no errors " );

        return "/index";
    }

    @GetMapping("/student")
    public String getStudentPage(Model model) throws StudentException{
        logger.info("Entered getStudentPage(): no errors " );


        return "/student";
    }


    @GetMapping("/new")
    public String newStudentPage(Model model) throws StudentException{
        logger.info("Entered newStudentPage(): no errors " );
        model.addAttribute("student", new Student());
        model.addAttribute("allCourses",courseRepository.findAll());

        return "/newStudent";

    }

    @GetMapping("/student/update")
    public String showUpdatePage(Model model,@RequestParam Integer id) {

        Optional<Student> result = studentRepository.getStudentId(id);
        if (result.isPresent()) {
            Student st = result.get();
            model.addAttribute("student",st);

            Iterable<Course> courses = courseRepository.findAll();
            model.addAttribute("allCourses",courses);
        }

        return "/updateStudent";
    }

    @PostMapping("/student/updateStudent")
    public String updateStudent(Model model,  @Valid Student studentData, BindingResult bindingR) throws StudentException{
        if (bindingR.hasErrors()){
            logger.error("updateStudent(): an error occurred for firstName " + studentData.getFirstName());

            Iterable<Course> courses = courseRepository.findAll();
            model.addAttribute("allCourses",courses);


            return "/updateStudent";
        }
        logger.info("Entered updateStudent(): no errors " );
        studentRepository.save(studentData);
        logger.info("updateStudent(): saved " + studentData.getFirstName());
        model.addAttribute("studentUpdated", studentUpdatedMessage);
        return "/student";

    }


    @PostMapping("/save")
    public String saveStudent(Model model,  @Valid Student studentData, BindingResult bindingR) throws StudentException{
        if (bindingR.hasErrors()){
            logger.error("saveStudent(): an error occurred for firstName " + studentData.getFirstName());

            Iterable<Course> courses = courseRepository.findAll();
            model.addAttribute("allCourses",courses);

            return "/newStudent";


        }
        logger.info("Entered saveStudent(): no errors " );
        studentRepository.save(studentData);
        logger.info("saveStudent(): saved " + studentData.getFirstName());
        model.addAttribute("studentAdded", studentAddedMessage);
        return "/student";

    }


    @GetMapping("/studentList")
    public String getStudentList(Model model) throws StudentException{
        logger.info("Entered getStudentList(): no errors " );
        Iterable<Student> students = studentRepository.getAllStudentInfo();
        model.addAttribute("students", students);
        logger.info("getStudentList(): found " + studentRepository.getAllStudentInfo());

        return "/studentList";
    }

    @GetMapping("/findStudent")
    public String findStudent() throws StudentException{

        logger.info("Entered findStudent(): no errors " );
        return "/findStudent";
    }

    @GetMapping("/findById")
    public String findStudentById(Model model, @RequestParam Integer id) throws StudentException{

        logger.info("Entered findStudentById(): no errors " );
        Optional<Student> result = studentRepository.getStudentId(id);

        if (result.isPresent()) {
            Student student = result.get();
            List<Student> stList = new ArrayList<Student>();
            stList.add(student);
            logger.info("findStudentById(): found " + studentRepository.getStudentId(id));
            model.addAttribute("students", stList);
        }



        return "/studentList";
    }

    @GetMapping("/findByName")
    public String findStudentByName(Model model, @RequestParam String stName) throws StudentException{
        logger.info("Entered findStudentByName(): no errors " );

        List<Student> stList = studentRepository.getFirstName(stName);
        logger.info("findStudentByName(): found " + studentRepository.getSurname(stName));
        model.addAttribute("students", stList);
        return "/studentList";


    }

    @GetMapping("/findBySurname")
    public String findStudentBySurname(Model model, @RequestParam String stSurname) throws StudentException{

        logger.info("Entered findStudentBySurname(): no errors " );
        List<Student> stList = studentRepository.getSurname(stSurname);
        logger.info("findStudentBySurname(): found " + studentRepository.getSurname(stSurname));
        model.addAttribute("students", stList);
        return "/studentList";

    }

    @GetMapping("/findByMajor")
    public String findStudentByMajor(Model model, @RequestParam String major) throws StudentException{

        try {
            logger.info("Entered findStudentByMajor(): no errors " );
            List<Student> stList = studentRepository.getMajor(major);
            logger.info("findStudentByMajor(): found " + studentRepository.getMajor(major));
            model.addAttribute("students", stList);
            return "/studentList";
        }
        catch(Exception exc){
            logger.error("findStudentByMajor(): an error occurred for major " + studentRepository.getMajor(major));
            model.addAttribute("findByMajorError", findByMajorError);
            return "/student";
        }
    }

//
//    @GetMapping("/deleteStudent")
//    public String deleteStudent() throws StudentException{
//
//        logger.info("Entered deleteStudent(): no errors " );
//        return "/deleteStudent";
//    }


    @GetMapping("/deleteStById")
    public String deleteStudentById(Model model, Student deleteStudent, @RequestParam Integer stId) throws StudentException{

        try {
            logger.info("Entered deleteStudentById(): no errors " );
            Optional<Student> result = studentRepository.getStudentId(stId);

            studentRepository.delete(result.get());
            logger.info("deleteStudentById(): deleted " + deleteStudent.getStId());
            model.addAttribute("deleteStudentSuccess", studentDeleteSuccess);
        }

        catch (Exception exc){
            logger.error("deleteStudentById(): an error occurred for stID " + deleteStudent.getStId());

        }

        return "/student";
    }

}
