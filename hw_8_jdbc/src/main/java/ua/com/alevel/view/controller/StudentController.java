package ua.com.alevel.view.controller;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.GroupFacade;
import ua.com.alevel.facade.GroupStudentFacade;
import ua.com.alevel.view.dto.request.GroupStudentRequestDto;
import ua.com.alevel.view.dto.request.StudentRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.StudentResponseDto;
import ua.com.alevel.facade.StudentFacade;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/students")
public class StudentController extends AbstractController {

    private final StudentFacade studentFacade;
    private final GroupFacade groupFacade;
    private final GroupStudentFacade groupStudentFacade;

    public StudentController(StudentFacade studentFacade, GroupFacade groupFacade, GroupStudentFacade groupStudentFacade) {
        this.studentFacade = studentFacade;
        this.groupFacade = groupFacade;
        this.groupStudentFacade = groupStudentFacade;
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
                new HeaderName("firstname", "firstName", "first_name"),
                new HeaderName("lastname", "lastName", "last_name"),
                new HeaderName("age", "age", "age"),
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

    @GetMapping("/new/{id}")
    public String redirectToNewRecordPage(Model model) {
        model.addAttribute("groupStudent", new GroupStudentRequestDto());
        model.addAttribute("groups", groupFacade.findAll());
        model.addAttribute("students", studentFacade.findAll());
        return "pages/student/student_link";
    }

    @PostMapping("/create")
    public String createRecord(@ModelAttribute("group_student") GroupStudentRequestDto dto) {
        groupStudentFacade.create(dto);
        return "redirect:/students";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        StudentResponseDto dto = studentFacade.findById(id);
        model.addAttribute("student", dto);
        return "pages/student/student_details";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        studentFacade.delete(id);
        return "redirect:/students";
    }

    @GetMapping("/all/group/{groupId}")
    public String findAllByGroup(@PathVariable Long groupId, Model model, WebRequest request) {
        HeaderName[] columnNames = getColumnNames();
        PageData<StudentResponseDto> response = studentFacade.findByGroupId(request, groupId);
        response.initPaginationState(response.getCurrentPage());
        List<HeaderData> headerDataList = getHeaderDataList(columnNames, response);
        model.addAttribute("headerDataList", headerDataList);
        model.addAttribute("createUrl", "/students/all/group/" + groupId);
        model.addAttribute("createNewItemUrl", "/students/new");
        model.addAttribute("pageData", response);
        model.addAttribute("allowCreate", false);
        model.addAttribute("cardHeader", "All Students");
        return "pages/student/student_all";
    }

    @PostMapping("/all/group/{groupId}")
    public ModelAndView findAllByCompanyRedirect(@PathVariable Long groupId, WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/students/all/group/" + groupId, model);
    }
}
