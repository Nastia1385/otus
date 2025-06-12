package task2;

public class Dog extends Animal{

    @Override
    public void say() {
        System.out.println("Гав");
    }

    public Dog(String name, int age, int weight, String color) {
        super(name, age, weight, color);
    }
}
