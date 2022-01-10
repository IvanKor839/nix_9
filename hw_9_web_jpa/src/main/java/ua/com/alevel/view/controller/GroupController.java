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
import ua.com.alevel.persistence.type.GroupType;
import ua.com.alevel.view.dto.request.GroupRequestDto;
import ua.com.alevel.view.dto.response.GroupResponseDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.StudentResponseDto;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/groups")
public class GroupController extends AbstractController {

    private final GroupFacade groupFacade;
    private final StudentFacade studentFacade;

    public GroupController(GroupFacade groupFacade, StudentFacade studentFacade) {
        this.groupFacade = groupFacade;
        this.studentFacade = studentFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest webRequest) throws IOException {
        HeaderName[] columnNames = getColumnNames();
        PageData<GroupResponseDto> response = groupFacade.findAll(webRequest);
        response.initPaginationState(response.getCurrentPage());
        model.addAttribute("headerDataList", getHeaderDataList(columnNames, response));
        model.addAttribute("createUrl", "/groups/all");
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "All Groups");
        model.addAttribute("allowCreate", true);
        model.addAttribute("createNewItemUrl", "/groups/new");
        return "pages/group/group_all";
    }

    private HeaderName[] getColumnNames() {
        return new HeaderName[]{
                new HeaderName("#", null, null),
                new HeaderName("created", "created", "created"),
                new HeaderName("updated", "updated", "updated"),
                new HeaderName("name", "name", "name"),
                new HeaderName("name Mentor", "nameMentor", "nameMentor"),
                new HeaderName("group Type", "groupType", "groupType"),
                new HeaderName("student count", "studentCount", "studentCount"),
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
        return new ModelAndView("redirect:/groups", model);
    }

    @GetMapping("/new")
    public String redirectToNewGroupPage(Model model) {
        model.addAttribute("group", new GroupRequestDto());
        model.addAttribute("types", GroupType.values());
        return "pages/group/group_new";
    }

    @PostMapping("/new")
    public String createNewGroup(@ModelAttribute("group") GroupRequestDto groupRequestDto) {
        groupFacade.create(groupRequestDto);
        return "redirect:/groups";
    }

    @GetMapping("/details/{id}")
    public String findById(@PathVariable Long id, Model model) {
        Set<StudentResponseDto> students = groupFacade.getStudents(id);
        model.addAttribute("group", groupFacade.findById(id));
        model.addAttribute("students", students);
        return "pages/group/group_details";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        groupFacade.delete(id);
        return "redirect:/groups";
    }

    @GetMapping("/update/{id}")
    public String redirectToUpdateGroupPage(@PathVariable Long id, Model model) {
        model.addAttribute("group", groupFacade.findById(id));
        return "pages/group/group_update";
    }

    @PostMapping("/update/{id}")
    public String updateGroup(@ModelAttribute("group") GroupRequestDto groupRequestDto, @PathVariable Long id) throws SQLException {
        groupFacade.update(groupRequestDto, id);
        return "redirect:/groups";
    }

    @GetMapping("/delete/student/{studentId}/{groupId}")
    public String deleteStudentFromGroup(@PathVariable Long studentId, @PathVariable Long groupId, Model model) {
        groupFacade.removeStudent(groupId, studentId);
        Set<GroupResponseDto> groups = studentFacade.getGroups(studentId);
        model.addAttribute("student", studentFacade.findById(studentId));
        model.addAttribute("groups", groups);
        return "pages/student/student_details";
    }
}
