package com;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import com.repository.CourseRepository;
import com.repository.ExamRepository;
import com.repository.StudentEnrollmentRepository;
import com.repository.StudentRepository;
import com.repository.TestPaperRepository;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class OnlineExamSystemApplication {// implements CommandLineRunner {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	StudentEnrollmentRepository studentEnrollmentRepository;

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	TestPaperRepository testPaperRepository;

	@Autowired
	ExamRepository examRepository;

//	@Bean
//	@Primary
//	public ModelMapper modelMapper() {
//		return new ModelMapper();
//	}

	public static void main(String[] args) {
		SpringApplication.run(OnlineExamSystemApplication.class, args);
	}

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com")).build();
	}

	// http://localhost:8080/swagger-ui/index.html

//	@Override
//	public void run(String... args) throws Exception {
//		System.out.println("Connected....");
//		
//		Exam e = new Exam(); 
//        e.setMaximumScore(100);
//        
//        Exam e1 = new Exam();
//        //e1.setEnrollmentId(2);     
//        e1.setMaximumScore(25);
//        
//        Exam e2 = new Exam();
//        //e2.setEnrollmentId(3);
//        e2.setMaximumScore(50);
//        
//        List<Exam> el = new ArrayList<Exam>();
//        el.add(e);
//        el.add(e1);
//        el.add(e2);
//        
//        List<Exam> el1 = new ArrayList<Exam>();
//        el1.add(e2);
//        el1.add(e);
//        
//        StudentEnrollment se = new StudentEnrollment();
//        se.setBatchName("Avengers");
//        se.setStatus(true);
//        long millis=System.currentTimeMillis();  
//        java.util.Date date = new java.util.Date(millis);  
//        se.setEnrollmentDate(date);
//        se.setExam(el);
//       
//        StudentEnrollment se1 = new StudentEnrollment();
//        se1.setBatchName("Justice League");
//        se1.setStatus(false);
//        long milli=System.currentTimeMillis();  
//        java.util.Date date1 = new java.util.Date(milli); 
//        se1.setEnrollmentDate(date1);
//        se1.setExam(el1);
//        
//        List<StudentEnrollment> sel1 = new ArrayList<StudentEnrollment>();
//        sel1.add(se);
//        sel1.add(se1);
//        
//        Student s1 = new Student();
//        s1.setEmail("zslf@yahoo.com");
//        s1.setFirstName("John");
//        s1.setLastName("Cena");
//        s1.setGender("Male");
//        s1.setMobileNo("3573946596");
//        s1.setStudentEnrollment(sel1);
//        
//        List<StudentEnrollment> sel2 = new ArrayList<StudentEnrollment>();
//        sel1.add(se1);
//        
//        Student s2 = new Student();
//        s2.setEmail("aofh@gmail.com");
//        s2.setFirstName("CM");
//        s2.setLastName("Punk");
//        s2.setGender("Male");
//        s2.setMobileNo("9233846343");
//        s2.setStudentEnrollment(sel2);
//        
//        Course c1 = new Course();
//        c1.setCourseName("Python for everybody");
//        c1.setCourseType("Coding");
//        //c1.setStudentenrollment(se);
//        se.setCourse(c1);
//        
//        Course c2 = new Course();
//        c2.setCourseName("Java for heroes");
//        c2.setCourseType("Coding");
//       // c2.setStudentenrollment(se1);
//        se1.setCourse(c2);
//        
//        TestQuestion tq = new TestQuestion();
//        tq.setQuestion("ORM full form?");
//        tq.setQuestionNo(1);
//        tq.setOption1(1);
//        tq.setOption2(2);
//        tq.setOption3(3);
//        tq.setOption4(4);
//        tq.setCorrectAnswer(3);
//        
//        TestQuestion tq1 = new TestQuestion();
//        tq1.setQuestion("JPA stands for?");
//        tq1.setQuestionNo(2);
//        tq1.setOption1(1);
//        tq1.setOption2(2);
//        tq1.setOption3(3);
//        tq1.setOption4(4);
//        tq1.setCorrectAnswer(4);
//        
//        List<TestQuestion> tql = new ArrayList<TestQuestion>();
//        tql.add(tq1);
//        tql.add(tq);
//        
//        List<TestQuestion> tql1 = new ArrayList<TestQuestion>();
//        tql1.add(tq);
//        
//        TestPaper tp = new TestPaper();
//        tp.setDifficultyLevel("low");
//        tp.setDescription("MCQ");
//        tp.setTestquestion(tql);
//        e.setTestpaper(tp);
//        e1.setTestpaper1(tp);
//        
//        TestPaper tp1 = new TestPaper();
//        tp1.setDifficultyLevel("High");
//        tp1.setDescription("Coding");
//        tp1.setTestquestion(tql);
//        e1.setTestpaper(tp1);
//        e.setTestpaper(tp);
//        e2.setTestpaper1(tp);
//        e2.setTestpaper(tp1);
//        
//        List<TestPaper> tpl1 = new ArrayList<TestPaper>();
//        tpl1.add(tp1);
//        tpl1.add(tp);
//        c1.setTestpaper(tpl1);
//        c2.setTestpaper(tpl1);
//        
//        studentRepository.save(s1);
//        studentRepository.save(s2);
//        
//        studentEnrollmentRepository.save(se);
//        studentEnrollmentRepository.save(se1);
//        
//        courseRepository.save(c1);
//        courseRepository.save(c2);
//        
//        testPaperRepository.save(tp);
//        testPaperRepository.save(tp1);
//            
	// }

}