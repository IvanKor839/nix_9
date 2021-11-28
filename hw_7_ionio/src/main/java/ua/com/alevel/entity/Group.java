package ua.com.alevel.entity;

public class Group extends BaseEntity {

    private String name;
    private String nameMentor;

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
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", nameMentor='" + nameMentor + '\'' +
                '}';
    }
}


