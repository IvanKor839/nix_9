package ua.com.alevel.view.dto.response;

import ua.com.alevel.entity.GroupStudent;
import ua.com.alevel.view.dto.ResponseDto;
import ua.com.alevel.entity.Group;
import ua.com.alevel.entity.Student;

public class GroupStudentResponseDto extends ResponseDto {

    private Long groupId;
    private Long studentId;

    public GroupStudentResponseDto(GroupStudent groupStudent){
        this.groupId = groupStudent.getGroup().getId();
        this.studentId = groupStudent.getStudent().getId();
        setId(groupStudent.getId());
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "GroupStudentResponseDto{" +
                "groupId=" + groupId +
                ", studentId=" + studentId +
                '}';
    }
}
