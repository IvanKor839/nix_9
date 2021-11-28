package ua.com.alevel.dto.group;

import ua.com.alevel.dto.BaseRequestDto;

public class GroupRequestDto extends BaseRequestDto {

    private String name;
    private String nameMentor;

    public GroupRequestDto(String name, String nameMentor) {
        this.name = name;
        this.nameMentor = nameMentor;
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
}
