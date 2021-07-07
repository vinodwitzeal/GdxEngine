package com.badlogic.engine.enums;

public enum UIFont {
    ARIAL("arial"),ROBOTO_BOLD("rbb");

    private String name;
    UIFont(String name){
        this.name=name;
    }

    @Override
    public String toString() {
        return name;
    }
}
