package ru.sndolgov.graduationproject;

public class ChangeableRestaurant {
    public static int id = 0;

    public static int id() {
        return id;
    }

    public static void setId(int id) {
        ChangeableRestaurant.id = id;
    }
}
