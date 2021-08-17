package general;

import java.io.Serializable;

public enum EventType implements Serializable {
    CONCERT("Concert"),

    FOOTBALL("Football"),

    BASEBALL("Baseball"),

    BASKETBALL("Basketball");

    private static final long serialVersionUID = 2324L;
    private String url;

    EventType(String url) {
        this.url = url;
    }

    public static void printValues() {
        System.out.println("List of EventType enum values: ");
        for (EventType eventType : EventType.values()) {
            System.out.println(eventType.getUrl());
        }
    }

    public String getUrl() {
        return url;
    }
}
