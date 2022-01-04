package ua.com.alevel.view.dto.response;

import ua.com.alevel.view.dto.ResponseDto;
import ua.com.alevel.entity.Group;
import ua.com.alevel.type.GroupType;

public class GroupResponseDto extends ResponseDto {

    private String name;
    private GroupType groupType;
    private String nameMentor;
    private Long studentCount;

    public Long getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(Long studentCount) {
        this.studentCount = studentCount;
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
