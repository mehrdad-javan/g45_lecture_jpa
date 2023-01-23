package se.lexicon.g45_lecture_jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.g45_lecture_jpa.dao.*;
import se.lexicon.g45_lecture_jpa.entity.*;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class AppCommandLineRunner implements CommandLineRunner {

    @Autowired
    StudentDao studentDao;

    @Autowired
    AddressDao addressDao;

    @Autowired
    BookDao bookDao;

    @Autowired
    CourseDao courseDao;

    @Autowired
    CompetenceDao competenceDao;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        ex6();
    }


    public void ex1() {

        Address addressData = new Address("Växjö", "Teleborg", "35256");
        Address createdAddress = addressDao.persist(addressData);

        //Student studentData = new Student("Test", "Test", "test.test1@test.se", LocalDate.parse("2000-01-01"), createdAddress);
        Student studentData = new Student("Test", "Test", "test.test1@test.se", LocalDate.parse("2000-01-01"), createdAddress);
        studentData.setAddress(createdAddress);
        Student createdStudent = studentDao.persist(studentData);



    }


    public void ex2(){
        Student studentData = new Student(
                "Test",
                "Test",
                "test.test1@test.se",
                LocalDate.parse("2000-01-01"),
                new Address("Växjö", "Teleborg", "35256"));

      Student createdStudent =  studentDao.persist(studentData);


      studentDao.remove(createdStudent.getId());


    }


    public void ex3(){
        Student studentData = new Student(
                "Test",
                "Test",
                "test.test1@test.se",
                LocalDate.parse("2000-01-01"),
                new Address("Växjö", "Teleborg", "35256")); // 1

        Student createdStudent =  studentDao.persist(studentData);

        addressDao.findAll().forEach(address -> {
            System.out.println(address);
            System.out.println(address.getStudent());
        });

    }


    public void ex4(){
        Student studentData = new Student(
                "Test",
                "Test",
                "test.test1@test.se",
                LocalDate.parse("2000-01-01"),
                new Address("Växjö", "Teleborg", "35256")); // 1

        Student createdStudent =  studentDao.persist(studentData);

        Book ocaBookData = new Book("OCA");
        Book ocpBookData = new Book("OCP");

        ocaBookData.setBorrower(createdStudent);
        //ocpBookData.setBorrower(createdStudent);

        Book createdOcaBook = bookDao.persist(ocaBookData);
        Book createdOcpBook = bookDao.persist(ocpBookData);


        createdStudent.borrowBook(createdOcpBook);
        createdStudent.returnBook(createdOcaBook);

        Optional<Student> optionalStudent = studentDao.findById(createdStudent.getId());
        if (optionalStudent.isPresent()){
            System.out.println(optionalStudent.get().getBorrowedBooks().size());
        }


        studentDao.remove(createdStudent.getId());

    }


    public void ex5(){
        Course java = courseDao.persist(new Course("Java"));
        Course cSharp = courseDao.persist(new Course("C#"));

        Student studentData = new Student(
                "Test",
                "Test",
                "test.test1@test.se",
                LocalDate.parse("2000-01-01"),
                new Address("Växjö", "Teleborg", "35256"));
        studentData.enrollCourse(java);
        Student createdStudent1 = studentDao.persist(studentData);

        cSharp.addStudent(createdStudent1);


    }





    public void ex6(){
        Competence javaSE = competenceDao.persist(new Competence("Java SE"));
        Competence spring = competenceDao.persist(new Competence("Spring Core"));
        Competence springBoot = competenceDao.persist(new Competence("Spring Boot"));


        Student studentData = new Student("Test", "Test", "test.test1@test.se", LocalDate.parse("2020-01-01"));
        studentData.setAddress(new Address("Växjö","Teleborg", "35264"));
        Student createdStudent1 = studentDao.persist(studentData);
        System.out.println("createdStudent1 >>>>> " + createdStudent1);


        Student studentData2 = new Student("Test", "Test", "test.test2@test.se", LocalDate.parse("2020-01-01"));
        studentData2.setAddress(new Address("Växjö","Teleborg", "35264"));
        Student createdStudent2 = studentDao.persist(studentData2);
        System.out.println("createdStudent2 >>>>> " + createdStudent2);


        spring.addStudent(createdStudent1);
        spring.addStudent(createdStudent2);

        springBoot.addStudent(createdStudent1);

        createdStudent1.addCompetence(javaSE);
        createdStudent1.addCompetence(spring);
        createdStudent1.addCompetence(springBoot);


        createdStudent1.removeCompetence(springBoot);


    }

}
