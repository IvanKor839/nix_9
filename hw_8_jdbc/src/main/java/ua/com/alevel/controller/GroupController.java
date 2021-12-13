package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.dto.group.GroupRequestDto;
import ua.com.alevel.dto.group.GroupResponseDto;
import ua.com.alevel.facade.GroupFacade;
import ua.com.alevel.type.GroupType;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/groups")
public class GroupController {

    private final GroupFacade groupFacade;
    
    public GroupController(GroupFacade groupFacade) {
        this.groupFacade = groupFacade;
    }

    @GetMapping
    public String findAll(Model model) throws IOException {
        List<GroupResponseDto> groups = groupFacade.findAll();
        model.addAttribute("groups", groups);
        return "pages/group/group_all";
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

}
