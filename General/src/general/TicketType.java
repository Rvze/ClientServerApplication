package general;

import java.io.Serializable;

public enum TicketType implements Serializable {
    USUAL("usual", 1),

    BUDGETARY("budgetary", 2),

    CHEAP("cheap", 3);

    private static long serialVersionUID = 14314542L;
    private String url;
    private final int value;

    TicketType(String url, int value) {
        this.url = url;
        this.value = value;
    }

    public static void printType() {
        System.out.println("List of TicketType enum values: ");
        for (TicketType ticketType : TicketType.values()) {
            System.out.println(ticketType);
        }
    }

    public int getValue() {
        return value;
    }

    public String getUrl() {
        return url;
    }
}
