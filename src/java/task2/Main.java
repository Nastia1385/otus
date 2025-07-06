package task2;

import task2.animals.Animal;
import task2.animals.birds.Duck;
import task2.animals.pets.Cat;
import task2.animals.pets.Dog;
import task2.enums.AnimalType;
import task2.enums.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        List<Animal> animals = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        List<String> commandNames = new ArrayList<>();

        for (Command command : Command.values()) {
            commandNames.add(command.name().toLowerCase());
        }

        List<String> animalTypes = new ArrayList<>();

        for (AnimalType animalType : AnimalType.values()) {
            animalTypes.add(animalType.getName());
        }

        while (true) {
            System.out.printf("Введите команду(%s):%n", String.join("/", commandNames));

            String userCommandConsole = scanner.next().trim().toLowerCase();

            if (!commandNames.contains(userCommandConsole)) {
                System.out.println("Ведена неверная команда.Повторите попытку.");
                continue;
            }

            if (userCommandConsole.equals(Command.LIST.getName())) {
                System.out.println("Все животные: " + animals);
                continue;
            }

            if (userCommandConsole.equals(Command.EXIT.getName())) {
                System.exit(-1);
            }

            while (true) {

                if (userCommandConsole.equals(Command.ADD.getName())) {
                    System.out.println("Какое животное(cat/dog/duck):");
                    String animal = scanner.next().trim().toLowerCase();
                    if (!animalTypes.contains(animal)) {
                        System.out.println("Неверно выбрано животное. Повторите попытку.");
                        continue;
                    }

                    System.out.println("Введите кличку животного:");
                    String name = scanner.next();

                    int age;
                    int weight;

                    while (true) {

                        System.out.println("Введите возраст животного:");
                        String stringAge = scanner.next();

                        try {
                            age = Integer.parseInt(stringAge);
                        } catch (NumberFormatException e) {
                            System.out.println("Введите цифру");
                            continue;
                        }

                        if (age < 1) {
                            System.out.println("Возраст не может быть меньше 1");
                        } else break;
                    }

                    while (true) {
                        System.out.println("Введите вес животного:");
                        String stringWeight = scanner.next();

                        try {
                            weight = Integer.parseInt(stringWeight);

                        } catch (NumberFormatException e) {
                            System.out.println("Введите цифру");
                            continue;
                        }
                        if (weight < 1) {
                            System.out.println("Вес не может быть меньше 1");
                        } else break;
                    }

                    System.out.println("Введите цвет животного:");
                    String color = scanner.next();

                    if (animal.equals(AnimalType.CAT.getName())) {
                        Cat cat = new Cat(name, age, weight, color);
                        animals.add(cat);
                        cat.say();
                    }

                    if (animal.equals(AnimalType.DOG.getName())) {
                        Dog dog = new Dog(name, age, weight, color);
                        animals.add(dog);
                        dog.say();
                    }

                    if (animal.equals(AnimalType.DUCK.getName())) {
                        Duck duck = new Duck(name, age, weight, color);
                        animals.add(duck);
                        duck.say();
                    }
                    break;
                }
            }
        }
    }
}
