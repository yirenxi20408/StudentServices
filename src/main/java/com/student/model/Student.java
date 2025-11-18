package com.student.model;

/**
 * Student entity class
 */
public class Student {
    private String sid;    // Student ID
    private String name;   // Student name
    private String tele;   // Telephone number

    public Student() {
    }

    public Student(String sid, String name, String tele) {
        this.sid = sid;
        this.name = name;
        this.tele = tele;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid='" + sid + '\'' +
                ", name='" + name + '\'' +
                ", tele='" + tele + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return sid != null && sid.equals(student.sid);
    }

    @Override
    public int hashCode() {
        return sid != null ? sid.hashCode() : 0;
    }
}
