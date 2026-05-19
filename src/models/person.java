package models;

public class Person {

    // بيانات أساسية لأي شخص
    private String id;
    private String name;
    private int age;
    private String gender;

    // Constructor
    public Person(String id, String name, int age, String gender) {

        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    // Getters & Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {

        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}