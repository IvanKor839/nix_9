package ua.com.alevel.view.dto.request;

import ua.com.alevel.view.dto.RequestDto;

public class StudentRequestDto extends RequestDto {

    private String firstName;
    private String lastName;
    private Integer age;

    public StudentRequestDto(String firstName, String lastName, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
    public StudentRequestDto(){}

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
