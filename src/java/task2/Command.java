package task2;


public enum Command {

    ADD("add"),
    LIST("list"),
    EXIT("exit");

    private final String name;

    Command(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("%s/%s/%s", Command.ADD.getName(), Command.LIST.getName(), Command.EXIT.getName());
    }
}
