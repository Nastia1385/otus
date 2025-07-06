package factory;

import animals.Animal;
import animals.birds.Duck;
import animals.pets.Cat;
import animals.pets.Dog;
import enums.AnimalType;

public class AnimalFactory {

    private final String name;

    private final int age;

    private final int weight;

    private final String color;

    public AnimalFactory(String name, int age, int weight, String color) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.color = color;
    }

    public Animal create(AnimalType animalType) {
        switch (animalType) {
            case CAT: {
                return new Cat(name, age, weight, color);
            }
            case DOG: {
                return new Dog(name, age, weight, color);
            }
            case DUCK: {
                return new Duck(name, age, weight, color);
            }
        }
        throw new RuntimeException(String.format("Animal %s not supported", animalType.getName()));
    }
       }
