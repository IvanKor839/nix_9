package ua.com.alevel.view.dto.response;

import ua.com.alevel.view.dto.ResponseDto;
import ua.com.alevel.entity.Student;

public class StudentResponseDto extends ResponseDto {

    private String firstName;
    private String lastName;
    private Integer age;

    public StudentResponseDto(Student student){
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.age = student.getAge();
        super.setId(student.getId());
        super.setCreated(student.getCreated());
        super.setUpdated(student.getUpdated());
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "StudentResponseDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
