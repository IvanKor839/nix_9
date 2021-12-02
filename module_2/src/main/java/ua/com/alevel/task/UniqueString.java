package ua.com.alevel.task;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


public class UniqueString {

    public String firstUniqueNameInList(List<String> names) {
        if (names.isEmpty()) {
            throw new RuntimeException("List is empty!");
        }
        Set<String> stringSet = new LinkedHashSet<String>(names);
        return stringSet.iterator().next();
    }
}
