package ua.com.alevel.dto.group;

import ua.com.alevel.dto.RequestDto;
import ua.com.alevel.type.GroupType;

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

    public GroupType getGroupType() {
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
