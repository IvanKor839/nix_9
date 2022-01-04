package ua.com.alevel.view.dto.request;

import ua.com.alevel.view.dto.RequestDto;

public class GroupStudentRequestDto extends RequestDto {

    private Long groupId;
    private Long studentId;

    public GroupStudentRequestDto(){}

    public GroupStudentRequestDto(Long groupId, Long studentId) {
        this.groupId = groupId;
        this.studentId = studentId;
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
}
