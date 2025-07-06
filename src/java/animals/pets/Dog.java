package animals.pets;

import animals.Animal;

public class Dog extends Animal {

    @Override
    public void say() {
        System.out.println("Гав");
    }

    public Dog(String name, int age, int weight, String color) {
        super(name, age, weight, color);
    }
}
