package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.dto.student.StudentRequestDto;
import ua.com.alevel.dto.student.StudentResponseDto;
import ua.com.alevel.facade.StudentFacade;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentFacade studentFacade;

    public StudentController(StudentFacade studentFacade) {
        this.studentFacade = studentFacade;
    }
    @GetMapping
    public String findAll(Model model) throws IOException {
        List<StudentResponseDto> students = studentFacade.findAll();
        model.addAttribute("students", students);
        return "pages/students/student_all";
    }

    @GetMapping("/groups/{id}")
    public String findAll(Model model, @PathVariable Long id) throws IOException {
        //
         List<StudentResponseDto> students = studentFacade.findAll();
        model.addAttribute("students", students);
        return "pages/students/student_all";
    }

    @GetMapping("/new/{groupId}")
    public String redirectToNewStudentPage(@PathVariable Long groupId, Model model) {
        System.out.println("groupId = " + groupId);
        StudentRequestDto dto = new StudentRequestDto();
       // dto.setDepartmentId(departmentId);
        model.addAttribute("student", dto);
        model.addAttribute("groupId", groupId);
        return "pages/students/student_new";
    }

    @PostMapping("/new")
    public String createNewEmployee(@ModelAttribute("student") StudentRequestDto dto) {
        studentFacade.create(dto);
        return "redirect:/students";
    }

    @GetMapping("/details/{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentFacade.findById(id));
        return "pages/students/student_details";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        studentFacade.delete(id);
        return "redirect:/students";
    }
}
