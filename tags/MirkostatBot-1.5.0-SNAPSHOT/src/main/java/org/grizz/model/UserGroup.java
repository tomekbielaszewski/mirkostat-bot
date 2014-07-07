package org.grizz.model;

/**
 * Created by Grizz on 2014-07-05.
 */
public enum UserGroup {
    GREEN(0),
    ORANGE(1),
    RED(2),
    SILVER(1001),
    BLACK(5),
    BLUE(2001),
    DELETED(1002);

    int value;

    UserGroup(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static UserGroup byValue(int value) {
        UserGroup[] allUserGroups = values();

        for (UserGroup userGroup : allUserGroups) {
            if(userGroup.getValue() == value) {
                return userGroup;
            }
        }

        throw new IllegalArgumentException("Group with id "+value+" not found!");
    }
}
