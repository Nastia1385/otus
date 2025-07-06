package task2.enums;

public enum AnimalType {

    CAT("cat"),
    DOG("dog"),
    DUCK("duck");

    private final String name;

    AnimalType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
