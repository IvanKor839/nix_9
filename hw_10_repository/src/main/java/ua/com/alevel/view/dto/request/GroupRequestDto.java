package ua.com.alevel.view.dto.request;

import ua.com.alevel.persistence.type.GroupType;
import ua.com.alevel.view.dto.RequestDto;

public class GroupRequestDto extends RequestDto {

    private String name;
    private GroupType groupType;
    private String nameMentor;

    public GroupRequestDto(String name, GroupType groupType, String nameMentor) {
        this.name = name;
        this.groupType = groupType;
        this.nameMentor = nameMentor;
    }
    public GroupRequestDto(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ua.com.alevel.persistence.type.GroupType getGroupType() {
        return groupType;
    }

    public void setGroupType(GroupType groupType) {
        this.groupType = groupType;
    }

    public String getNameMentor() {
        return nameMentor;
    }

    public void setNameMentor(String nameMentor) {
        this.nameMentor = nameMentor;
    }
}
