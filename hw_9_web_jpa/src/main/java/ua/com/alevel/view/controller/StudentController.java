package ua.com.alevel.view.controller;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import ua.com.alevel.facade.GroupFacade;
import ua.com.alevel.facade.StudentFacade;
import ua.com.alevel.view.dto.request.StudentRequestDto;
import ua.com.alevel.view.dto.response.GroupResponseDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.StudentResponseDto;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/students")
public class StudentController extends AbstractController {

    private final StudentFacade studentFacade;
    private final GroupFacade groupFacade;

    public StudentController(StudentFacade studentFacade, GroupFacade groupFacade) {
        this.studentFacade = studentFacade;
        this.groupFacade = groupFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) throws IOException {
        HeaderName[] columnNames = getColumnNames();
        PageData<StudentResponseDto> response = studentFacade.findAll(request);
        response.initPaginationState(response.getCurrentPage());
        model.addAttribute("headerDataList", getHeaderDataList(columnNames, response));
        model.addAttribute("createUrl", "/students/all");
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "All Students");
        model.addAttribute("allowCreate", true);
        model.addAttribute("createNewItemUrl", "/students/new");
        return "pages/student/student_all";
    }

    private HeaderName[] getColumnNames() {
        return new HeaderName[]{
                new HeaderName("#", null, null),
                new HeaderName("created", "created", "created"),
                new HeaderName("updated", "updated", "updated"),
                new HeaderName("age", "age", "age"),
                new HeaderName("first Name", "firstName", "firstName"),
                new HeaderName("last Name", "lastName", "lastName"),
                new HeaderName("update", null, null),
                new HeaderName("details", null, null),
                new HeaderName("delete", null, null)
        };
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/students", model);
    }

    @GetMapping("/groups/{id}")
    public String findAll(Model model, WebRequest webRequest, @PathVariable Long id) throws IOException {
        PageData<StudentResponseDto> students = studentFacade.findAll(webRequest);
        model.addAttribute("students", students);
        return "pages/student/student_all";
    }


    @GetMapping("/new")
    public String redirectToNewStudentPage(Model model) {
        model.addAttribute("student", new StudentRequestDto());
        return "pages/student/student_new";
    }

    @PostMapping("/new")
    public String createNewStudent(@ModelAttribute("student") StudentRequestDto dto) {
        studentFacade.create(dto);
        return "redirect:/students";
    }

    @GetMapping("/update/{id}")
    public String redirectToUpdateStudentPage(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentFacade.findById(id));
        return "pages/student/student_update";
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@ModelAttribute("student") StudentRequestDto studentRequestDto, @PathVariable Long id) throws SQLException {
        studentFacade.update(studentRequestDto, id);
        return "redirect:/students";
    }

    @GetMapping("/new/{id}")
    public String redirectToNewRecordPage(@PathVariable Long id, Model model, WebRequest webRequest) throws IOException {
        List<StudentResponseDto> studentResponseDtos = studentFacade.findAll(webRequest).getItems();
        model.addAttribute("group", groupFacade.findById(id));
        model.addAttribute("students", studentResponseDtos);
        return "pages/student/student_link";
    }

    @GetMapping("/create/{studentId}/{groupId}")
    public String createRecord(@PathVariable Long studentId, @PathVariable Long groupId, Model model) {
        groupFacade.addStudent(groupId, studentId);
        Set<StudentResponseDto> students = groupFacade.getStudents(groupId);
        model.addAttribute("group", groupFacade.findById(groupId));
        model.addAttribute("students", students);
        return "pages/group/group_details";
    }

    @GetMapping("/details/{id}")
    public String findById(@PathVariable Long id, Model model) {
        Set<GroupResponseDto> groups = studentFacade.getGroups(id);
        model.addAttribute("student", studentFacade.findById(id));
        model.addAttribute("groups", groups);
        return "pages/student/student_details";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        Set<GroupResponseDto> groupResponseDtos = studentFacade.getGroups(id);
        groupResponseDtos.forEach(groupResponseDto -> {
            groupFacade.removeStudent(groupResponseDto.getId(), id);
        });
        studentFacade.delete(id);
        return "redirect:/students";
    }

    @GetMapping("/delete/group/{studentId}/{groupId}")
    public String deleteStudentFromGroup(@PathVariable Long studentId, @PathVariable Long groupId, Model model) {
        groupFacade.removeStudent(groupId, studentId);
        Set<StudentResponseDto> students = groupFacade.getStudents(groupId);
        model.addAttribute("group", groupFacade.findById(groupId));
        model.addAttribute("students", students);
        return "pages/group/group_details";
    }
}
