package org.grizz.model;

import java.util.Arrays;

public enum UserGroup {
    GREEN(0),
    ORANGE(1),
    RED(2),
    SILVER(1001),
    BLACK(5),
    BLUE(2001),
    DELETED(1002);

    private int value;

    UserGroup(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static UserGroup byValue(int value) {
        return Arrays.stream(values())
                .filter(ug -> ug.getValue() == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Group with id " + value + " not found!"));
    }
}
