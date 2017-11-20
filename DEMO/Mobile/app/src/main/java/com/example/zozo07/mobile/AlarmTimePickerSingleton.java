package com.example.zozo07.mobile;

/**
 * Created by Zozo07 on 2017.11.20..
 */

public class AlarmTimePickerSingleton {

    private static AlarmTimePickerSingleton instance = null;

    protected AlarmTimePickerSingleton() {

    }

    public static AlarmTimePickerSingleton getInstance() {
        if (instance == null) {
            instance = new AlarmTimePickerSingleton();
        }
        return  instance;
    }
}
