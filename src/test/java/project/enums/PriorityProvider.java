package project.enums;

public enum PriorityProvider {
    PRIORITY_1(4),
    PRIORITY_2(3),
    PRIORITY_3(2),
    PRIORITY_4(1);

    private final int priority;

    PriorityProvider(int priority) {
        this.priority = priority;
    }

    public int getValue() {
        return priority;
    }
}