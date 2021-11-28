package ua.com.alevel.dto.student;

import ua.com.alevel.dto.BaseResponseDto;
import ua.com.alevel.entity.Student;

public class StudentResponseDto extends BaseResponseDto {

    private String idGroup;
    private String firstName;
    private String lastName;

    public StudentResponseDto(Student student) {
        setId(student.getId());
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.idGroup = student.getIdGroup();
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

    public String getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(String idGroup) {
        this.idGroup = idGroup;
    }

    @Override
    public String toString() {
        return "StudentResponseDto{" +
                "id='" + getId() + '\'' +
                "idGroup='" + idGroup + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
