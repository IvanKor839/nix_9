package ua.com.alevel.view.controller;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.GroupStudentFacade;
import ua.com.alevel.view.dto.request.GroupRequestDto;
import ua.com.alevel.view.dto.response.GroupResponseDto;
import ua.com.alevel.facade.GroupFacade;
import ua.com.alevel.type.GroupType;
import ua.com.alevel.view.dto.response.PageData;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/groups")
public class GroupController extends AbstractController{

    private final GroupFacade groupFacade;
    private final GroupStudentFacade groupStudentFacade;

    public GroupController(GroupFacade groupFacade, GroupStudentFacade groupStudentFacade) {
        this.groupFacade = groupFacade;
        this.groupStudentFacade = groupStudentFacade;
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
                new HeaderName("name Mentor", "nameMentor", "name_mentor"),
                new HeaderName("group Type", "groupType", "group_type"),
                new HeaderName("student count", "studentCount", "studentCount"),
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
        model.addAttribute("group", groupFacade.findById(id));
        return "pages/group/group_details";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        groupFacade.delete(id);
        return "redirect:/groups";
    }

    @GetMapping("/all/student/{studentId}")
    public String findAllByStudent(@PathVariable Long studentId, Model model, WebRequest request) {
        HeaderName[] columnNames = getColumnNames();
        PageData<GroupResponseDto> response = groupFacade.findByStudentId(request, studentId);
        response.initPaginationState(response.getCurrentPage());
        List<HeaderData> headerDataList = getHeaderDataList(columnNames, response);
        model.addAttribute("headerDataList", headerDataList);
        model.addAttribute("createUrl", "/groups/all/student/" + studentId);
        model.addAttribute("createNewItemUrl", "/groups/new");
        model.addAttribute("pageData", response);
        model.addAttribute("allowCreate", false);
        model.addAttribute("cardHeader", "All Groups");
        return "pages/group/group_all";
    }
    @PostMapping("/all/student/{studentId}")
    public ModelAndView findAllByStudentRedirect(@PathVariable Long studentId, WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/groups/all/student/" + studentId, model);
    }
}
