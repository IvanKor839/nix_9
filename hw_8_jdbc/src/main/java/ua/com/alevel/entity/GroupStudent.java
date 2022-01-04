package ua.com.alevel.entity;

public class GroupStudent extends BaseEntity{

    private Group group;
    private Student student;

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
        return "GroupStudent{" +
                "id=" + id +
                ", group=" + group +
                ", student=" + student +
                '}';
    }
}
