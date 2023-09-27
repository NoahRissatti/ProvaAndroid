package com.example.provaandroid;

public class Item {
    private String id;
    private String text;
    private String option;

    public Item(String id, String text, String option) {
        this.id = id;
        this.text = text;
        this.option = option;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getOption() {
        return option;
    }
}
