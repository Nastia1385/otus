package enums;


public enum Command {

    ADD("add"),
    LIST("list"),
    UPDATE("update"),
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
        return String.format("%s/%s/%s/%s", Command.ADD.getName(), Command.LIST.getName(), Command.UPDATE.getName(), Command.EXIT.getName());
    }
}
