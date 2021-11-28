package ua.com.alevel.dto.group;

import ua.com.alevel.dto.BaseResponseDto;
import ua.com.alevel.entity.Group;

public class GroupResponseDto extends BaseResponseDto {

    private String name;
    private String nameMentor;

    public GroupResponseDto(Group group) {
        setId(group.getId());
        this.name = group.getName();
        this.nameMentor = group.getNameMentor();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameMentor() {
        return nameMentor;
    }

    public void setNameMentor(String nameMentor) {
        this.nameMentor = nameMentor;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id='" + getId() + '\'' +
                "name='" + name + '\'' +
                ", nameMentor='" + nameMentor + '\'' +
                '}';
    }
}
