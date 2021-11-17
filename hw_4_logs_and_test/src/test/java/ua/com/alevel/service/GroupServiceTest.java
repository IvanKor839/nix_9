package ua.com.alevel.service;

import org.junit.jupiter.api.*;
import ua.com.alevel.entity.Group;

import java.util.Arrays;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GroupServiceTest {

    private final static GroupService groupService = new GroupService();
    private final static int GROUP_COUNT = 10;

    @BeforeAll
    public static void setUp() {
        for (int i = 0; i < GROUP_COUNT; i++) {
            Group group = GenerationUtil.generateGroup(GenerationUtil.NAME_GROUP + i);
            groupService.create(group);
        }
        Assertions.assertEquals(GROUP_COUNT, groupService.findAll().length);
    }
    @Order(1)
    @Test
    public void shouldBeCreateGroupWhenNameIsEmpty(){
        Group group = new Group();
        group.setName(null);
        group.setNameMentor("asa");
        groupService.create(group);
        Assertions.assertEquals(GROUP_COUNT+1, groupService.findAll().length);
    }
    @Order(2)
    @Test
    public void shouldBeCreateGroup(){
        groupService.create(GenerationUtil.generateGroup("qq", "qqw"));
        Assertions.assertEquals(GROUP_COUNT+2, groupService.findAll().length);
    }
    @Order(3)
    @Test
    public void shouldBeUpdateGroup(){
        String id = getRandomIdFromGroupArray();
        Group group = getGroupFromGroupArray(id);
        group.setName("ww");
        group.setNameMentor("ww");
        groupService.update(group);
        group = groupService.findById(id);
        Assertions.assertEquals("ww", group.getName());
        Assertions.assertEquals("ww", group.getNameMentor());
    }
    @Order(4)
    @Test
    public void shouldBeDeleteGroup(){
        String id = getRandomIdFromGroupArray();
        groupService.delete(id);
        Assertions.assertEquals(GROUP_COUNT+1, groupService.findAll().length);
    }
    @Order(5)
    @Test
    public void shouldFindGroupWhenIdIsRandom(){
        Group group = getGroupFromGroupArray((getRandomIdFromGroupArray()));
        Assertions.assertNotNull(group);
    }

    private String getRandomIdFromGroupArray() {
        return Arrays.stream(groupService.findAll()).findFirst().get().getId();
    }

    private Group getGroupFromGroupArray(String id) {
        return groupService.findById(id);
    }
}
