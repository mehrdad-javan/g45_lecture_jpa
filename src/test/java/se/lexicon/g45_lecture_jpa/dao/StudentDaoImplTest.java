package se.lexicon.g45_lecture_jpa.dao;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.g45_lecture_jpa.entity.Student;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
@AutoConfigureTestEntityManager
@DirtiesContext
public class StudentDaoImplTest {


    @Autowired
    TestEntityManager em;

    @Autowired
    StudentDaoImpl testObject;

    String createdStudentId1;
    String createdStudentId2;

    @BeforeEach
    public void setup() {
        Student studentData1 = new Student("Johny", "Johny", "johny@test.test", LocalDate.parse("2000-01-01"));
        Student studentData2 = new Student("Oscar", "Oscar", "oscar@test.test", LocalDate.parse("2000-01-01"));

        Student createdStudent1 = em.persist(studentData1);
        Student createdStudent2 = em.persist(studentData2);

        createdStudentId1 = createdStudent1.getId();
        createdStudentId2 = createdStudent2.getId();

    }

    @Test
    public void persist(){
        Student studentData = new Student("Peter", "Peter", "peter@test.test", LocalDate.parse("2000-01-02"));
        Student createdStudent = testObject.persist(studentData);

        assertNotNull(createdStudent);
        assertNotNull(createdStudent.getId());


    }

    // todo: add more tests

}
