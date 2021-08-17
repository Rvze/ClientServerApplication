package general;

import java.util.Objects;

public abstract class AbstractCommand implements Command {
    private final String name;
    private final String description;
    private boolean isTicketCommand = false;

    public AbstractCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public AbstractCommand(String name, String description, boolean isTicketCommand) {
        this(name, description);
        this.isTicketCommand = isTicketCommand;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean isTicketCommand() {
        return isTicketCommand;
    }

    public void setTicketCommand(boolean ticketCommand) {
        isTicketCommand = ticketCommand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractCommand that = (AbstractCommand) o;
        return isTicketCommand == that.isTicketCommand && name.equals(that.name) && description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, isTicketCommand);
    }
}
