package ua.com.alevel.dto.groupstudent;

import ua.com.alevel.dto.ResponseDto;
import ua.com.alevel.entity.Group;
import ua.com.alevel.entity.Student;

public class GroupStudentResponseDto extends ResponseDto {

    private Group group;
    private Student student;

    public GroupStudentResponseDto() { }

    public GroupStudentResponseDto(Long id, Group group, Student student){
        this.group = group;
        this.student = student;
        setId(id);
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "GroupStudentResponseDto{" +
                "group=" + group +
                ", student=" + student +
                '}';
    }
}
