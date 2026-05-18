package models;

public class Person {

    // بيانات أساسية لأي شخص
    private String id;
    private String name;
    private int age;

    // Constructor
    public Person(String id, String name, int age) {

        this.id = id;
        this.name = name;
        this.age = age;
    }

    // Getter للـ ID
    public String getId() {
        return id;
    }

    // Setter للـ ID
    public void setId(String id) {
        this.id = id;
    }

    // Getter للاسم
    public String getName() {
        return name;
    }

    // Setter للاسم
    public void setName(String name) {
        this.name = name;
    }

    // Getter للعمر
    public int getAge() {
        return age;
    }

    // Setter للعمر
    public void setAge(int age) {
        this.age = age;
    }


    // عرض بيانات الشخص
    @Override
    public String toString() {

        return "Person{" +"id='" + id + '\'' +", name='" + name + '\'' +", age=" + age +'}';
    }
}