package ua.com.alevel.dto.student;

import ua.com.alevel.dto.BaseRequestDto;

public class StudentRequestDto extends BaseRequestDto {

    private String idGroup;
    private String firstName;
    private String lastName;

    public StudentRequestDto(String idGroup, String firstName, String lastName) {
        this.idGroup = idGroup;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(String idGroup) {
        this.idGroup = idGroup;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
