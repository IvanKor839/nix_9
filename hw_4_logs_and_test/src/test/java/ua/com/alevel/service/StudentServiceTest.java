package ua.com.alevel.service;

import org.junit.jupiter.api.*;
import ua.com.alevel.entity.Student;

import java.util.Arrays;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentServiceTest {

    private final static StudentService studentService = new StudentService();
    private final static int STUDENT_COUNT = 10;

    @BeforeAll
    public static void setUp() {
        for (int i = 0; i < STUDENT_COUNT; i++) {
            Student student = GenerationUtil.generateStudent(GenerationUtil.NAME_STUDENT + i);
            studentService.create(student);
        }
        Assertions.assertEquals(STUDENT_COUNT, studentService.findAll().length);
    }

    @Order(1)
    @Test
    public void shouldBeCreateStudentWhenNameIsEmpty() {
        Student student = new Student();
        student.setName(null);
        studentService.create(student);
        Assertions.assertEquals(STUDENT_COUNT + 1, studentService.findAll().length);
    }

    @Order(2)
    @Test
    public void shouldBeCreateStudent() {
        studentService.create(GenerationUtil.generateStudent("qq"));
        Assertions.assertEquals(STUDENT_COUNT + 2, studentService.findAll().length);
    }

    @Order(3)
    @Test
    public void shouldBeUpdateStudent() {
        String id = getRandomIdFromStudentArray();
        Student student = getStudentFromStudentArray(id);
        student.setName("ww");
        studentService.update(student);
        student = studentService.findById(id);
        Assertions.assertEquals("ww", student.getName());
    }

    @Order(4)
    @Test
    public void shouldBeDeleteStudent() {
        String id = getRandomIdFromStudentArray();
        studentService.delete(id);
        Assertions.assertEquals(STUDENT_COUNT + 1, studentService.findAll().length);
    }

    @Order(5)
    @Test
    public void shouldFindStudentWhenIdIsRandom() {
        Student student = getStudentFromStudentArray((getRandomIdFromStudentArray()));
        Assertions.assertNotNull(student);
    }

    private String getRandomIdFromStudentArray() {
        return Arrays.stream(studentService.findAll()).findFirst().get().getId();
    }

    private Student getStudentFromStudentArray(String id) {
        return studentService.findById(id);
    }
}
