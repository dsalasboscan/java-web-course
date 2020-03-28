package com.educacionit.model;

public enum Action {
    LIST("list"),
    UPDATE("update"),
    DELETE("delete"),
    FILTER("filter"),
    CREATE("create");

    private String value;

    Action (String value) {
        this.value = value;
    }

    public static Action valueOfLabel(String value) {
        for (Action e : values()) {
            if (e.value.equals(value)) {
                return e;
            }
        }
        return null;
    }
}