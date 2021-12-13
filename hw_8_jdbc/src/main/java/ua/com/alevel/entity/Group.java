package ua.com.alevel.entity;

import ua.com.alevel.type.GroupType;

import java.util.Objects;

public class Group extends BaseEntity{

    private GroupType groupType;
    private String name;
    private String nameMentor;

    public Group(){
        super();
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
