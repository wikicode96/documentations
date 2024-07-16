package com.github.wikicode96.entity;

public class TargetEntity {
    private String fullName;
    private int age;

    public TargetEntity() {
    }

    public TargetEntity(String fullName, int age) {
        this.fullName = fullName;
        this.age = age;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
