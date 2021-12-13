package ua.com.alevel.dto.group;

import ua.com.alevel.dto.ResponseDto;
import ua.com.alevel.entity.Group;
import ua.com.alevel.type.GroupType;

public class GroupResponseDto extends ResponseDto {

    private String name;
    private GroupType groupType;
    private String nameMentor;

    public GroupResponseDto() {
    }

    public GroupResponseDto(Group group) {
        this.name = group.getName();
        this.nameMentor = group.getNameMentor();
        this.groupType = group.getGroupType();
        super.setId(group.getId());
        super.setUpdated(group.getUpdated());
        super.setCreated(group.getCreated());
    }

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

    @Override
    public String toString() {
        return "GroupResponseDto{" +
                "name='" + name + '\'' +
                ", groupType=" + groupType +
                ", nameMentor='" + nameMentor + '\'' +
                '}';
    }
}
