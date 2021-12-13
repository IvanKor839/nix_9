package ua.com.alevel.entity;

public class GroupStudent extends BaseEntity{

    private Long groupId;
    private Long studentId;

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
        return "GroupStudent{" +
                "id=" + id +
                ", groupId=" + groupId +
                ", studentId=" + studentId +
                '}';
    }

}
