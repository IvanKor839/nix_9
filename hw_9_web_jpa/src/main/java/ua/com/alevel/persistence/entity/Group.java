package ua.com.alevel.persistence.entity;

import ua.com.alevel.persistence.type.GroupType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "groups")
public class Group extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "group_type")
    private GroupType groupType;

    private String name;

    @Column(name = "name_mentor")
    private String nameMentor;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "groups_students",
            joinColumns = @JoinColumn(name = "id_groups"),
            inverseJoinColumns = @JoinColumn(name = "id_student")
    )

    private Set<Student> students;

    public Group(){
        super();
        students = new HashSet<>();
    }
    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public GroupType getGroupType() {
        return groupType;
    }

    public void setGroupType(GroupType groupType) {
        this.groupType = groupType;
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

    public void addStudent(Student student) {
        students.add(student);
        student.getGroups().add(this);
    }

    public void removeStudent(Student student) {
        students.remove(student);
        student.getGroups().remove(this);
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", groupType=" + groupType +
                ", name='" + name + '\'' +
                ", nameMentor='" + nameMentor + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return groupType == group.groupType && Objects.equals(name, group.name) && Objects.equals(nameMentor, group.nameMentor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupType, name, nameMentor);
    }
}
