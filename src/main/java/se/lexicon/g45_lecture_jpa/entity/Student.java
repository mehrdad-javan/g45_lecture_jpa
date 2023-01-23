package se.lexicon.g45_lecture_jpa.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
//@Table(name = "TBL_STUDENTS")
public class Student {

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private int id;*/

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @Column(nullable = false, length = 100)
    private String firstName;
    @Column(nullable = false, length = 100)
    private String lastName;
    @Column(nullable = false, length = 120, unique = true)
    private String email;
    @Column(nullable = false)
    private LocalDate birthDate;
    private boolean status;
    private LocalDateTime registrationDate;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "address_id")
    private Address address;


    @OneToMany(mappedBy = "borrower", orphanRemoval = true)
    private List<Book> borrowedBooks;


    @ManyToMany(mappedBy = "participants")
    private List<Course> enrolledCourses;

    // ctors

    public Student() {
        this.status = true;
        this.registrationDate = LocalDateTime.now();
    }

    public Student(String firstName, String lastName, String email, LocalDate birthDate) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
    }


    public Student(String firstName, String lastName, String email, LocalDate birthDate, Address address) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        setAddress(address);
    }

    // setters & getters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        //bi directional
        if (address != null) address.setStudent(this);

        this.address = address;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public List<Course> getEnrolledCourses() {
        if (enrolledCourses == null) enrolledCourses = new ArrayList<>();
        return enrolledCourses;
    }

    public void setEnrolledCourses(List<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    public void borrowBook(Book book) {
        if (book == null) throw new IllegalArgumentException("Book data was null");
        if (borrowedBooks == null) borrowedBooks = new ArrayList<>();

        borrowedBooks.add(book);
        book.setBorrower(this);

    }

    public void returnBook(Book book) {
        if (book == null) throw new IllegalArgumentException("Book data was null");
        if (borrowedBooks != null) {
            book.setBorrower(null);
            borrowedBooks.remove(book);
        }
    }


    public void enrollCourse(Course course) {
        if (course == null) throw new IllegalArgumentException("course data was null");
        if (enrolledCourses == null) enrolledCourses = new ArrayList<>();

        enrolledCourses.add(course);
        course.getParticipants().add(this);
    }


    public void unEnrollCourse(Course course) {
        if (course == null) throw new IllegalArgumentException("course data was null");
        if (enrolledCourses != null) {
            course.getParticipants().remove(this);
            enrolledCourses.remove(course);
        }
    }


    // equal & hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return status == student.status && Objects.equals(id, student.id) && Objects.equals(firstName, student.firstName) && Objects.equals(lastName, student.lastName) && Objects.equals(email, student.email) && Objects.equals(birthDate, student.birthDate) && Objects.equals(registrationDate, student.registrationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, birthDate, status, registrationDate);
    }

    // toString

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", status=" + status +
                ", registrationDate=" + registrationDate +
                '}';
    }

}
