package animals.pets;

import animals.Animal;

public class Cat extends Animal {

    @Override
    public void say() {
        System.out.println("Мяу");
    }

    public Cat(String name, int age, int weight, String color) {
        super(name, age, weight, color);
    }
}
