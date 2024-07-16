package com.github.wikicode96.entity;

public class SourceEntity {
    private String name;
    private int age;

    public SourceEntity() {
    }

    public SourceEntity(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
