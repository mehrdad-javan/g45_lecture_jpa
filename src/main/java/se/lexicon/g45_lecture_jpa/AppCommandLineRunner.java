package se.lexicon.g45_lecture_jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.lexicon.g45_lecture_jpa.dao.StudentDao;
import se.lexicon.g45_lecture_jpa.entity.Student;

import java.time.LocalDate;

@Component
public class AppCommandLineRunner implements CommandLineRunner {

    @Autowired
    StudentDao studentDao;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("####################");
        Student createdStudent1 = studentDao.persist(
                new Student(
                        "Test",
                        "Test",
                        "test.test@test.se",
                        LocalDate.parse("2000-01-01"))
        );

        System.out.println("#################### : " + createdStudent1.getId());


    }
}
