package com.educacionit.model;

public enum Action {
  UPDATE("update"),
  CREATE("create"),
  DELETE("delete");

  private String value;

  Action(String value) {
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
