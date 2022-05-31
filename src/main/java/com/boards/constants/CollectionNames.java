package com.boards.constants;

public enum CollectionNames {
    TODOLIST("todolist"),
    TASK("task"),
    SUBTASK("subtask"),
    COMMENT("comment"),
    BOARD("board"), 
    PHOTO("photo"),
    SECTION("section");

    String collectionName;
    CollectionNames(String collectionName) {
        this.collectionName = collectionName;
    }

    public String get() {
        return this.collectionName;
    }
}
