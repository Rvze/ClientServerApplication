package subsidiary;

import exceptions.EnumNotFoundException;
import exceptions.InvalidFieldException;
import general.*;

import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.Scanner;

public class TicketBuilderImpl implements TicketBuilder, IO {
    private final boolean isScript;
    private final TicketValidator validator;
    private final Scanner scanner;
    private String line;

    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Float price; //Поле может быть null, Значение поля должно быть больше 0
    private Long discount; //Поле может быть null, Значение поля должно быть больше 0, Максимальное значение поля: 100
    private boolean refundable;
    private TicketType type; //Поле не может быть null
    private Event event; //Поле может быть null
    private String username;

    public TicketBuilderImpl(boolean isScript, TicketValidator validator, Scanner scanner) {
        this.isScript = isScript;
        this.validator = validator;
        this.scanner = scanner;
    }

    @Override
    public void setTicketId(Long id) {
        if (id == null || id < 0) {
            println("Invalid value");
            askTicketId();
        }
        this.id = id;
    }

    @Override
    public void setName(String name) throws InvalidFieldException {
        validator.validateName(name);
        this.name = name;
    }

    @Override
    public void setCoordinateX(long x) throws InvalidFieldException {
        if (coordinates == null)
            this.coordinates = new Coordinates();
        coordinates.setX(x);
    }


    @Override
    public void setCoordinateY(Integer y) throws InvalidFieldException {
        validator.validateCoordinateY(y);
        if (coordinates == null)
            this.coordinates = new Coordinates();
        coordinates.setY(y);
    }

    @Override
    public void setPrice(Float price) throws InvalidFieldException {
        validator.validatePrice(price);
        this.price = price;
    }

    @Override
    public void setDiscount(Long discount) throws InvalidFieldException {
        validator.validateDiscount(discount);
        this.discount = discount;
    }

    @Override
    public Boolean setRefundable(boolean refundable) throws InvalidFieldException {
        return refundable;
    }

    @Override
    public void setUsername(String username) throws InvalidFieldException {
        validator.validateUserName(username);
        this.username = username;
    }

    @Override
    public void setTicketType(TicketType ticketType) throws InvalidFieldException {
        validator.validateTicketType(ticketType);
        this.type = ticketType;
    }

    @Override
    public void setEventName(String eventName) throws InvalidFieldException {
        validator.validateEventName(eventName);
        if (event == null)
            event = new Event();
        event.setName(eventName);
    }

    @Override
    public void setEventId(Long eventId) throws InvalidFieldException {
        if (eventId == null || eventId <= 0) {
            print("Invalid value");
            askEventId();
        } else if (event == null)
            event = new Event();
        event.setId(eventId);

    }

    @Override
    public void setDescription(String description) throws InvalidFieldException {
        validator.validateDescription(description);
        event.setDescription(description);
    }

    @Override
    public void setEventType(EventType eventType) throws InvalidFieldException {
        event.setEventType(eventType);
    }

    @Override
    public void setCreationDate() {
        creationDate = ZonedDateTime.now();
    }

    @Override
    public void setCreationDate(ZonedDateTime parse) throws InvalidFieldException {
        if (creationDate == null)
            throw new InvalidFieldException("creation date can't be null");
        this.creationDate = parse;
    }

    @Override
    public Ticket getTicket() {
        return new Ticket(name, coordinates, price, discount, refundable, type, event, username);
    }

    @Override
    public TicketType checkTicketType(String s) throws InvalidFieldException, EnumNotFoundException {
        for (TicketType ticketType : TicketType.values()) {
            if (s.equalsIgnoreCase(ticketType.getUrl())) {
                validator.validateTicketType(ticketType);
                return ticketType;
            }
        }
        throw new EnumNotFoundException("There is no enum named " + s);
    }

    @Override
    public EventType checkEventType(String s) throws InvalidFieldException, EnumNotFoundException {
        for (EventType eventType : EventType.values()) {
            if (s.equalsIgnoreCase(eventType.getUrl())) {
                return eventType;
            }
        }
        throw new EnumNotFoundException("There is no enum named " + s);
    }

    /**
     * Ввод всех полей
     */

    @Override
    public void inputFieldsFile() {
        askName();
        askCoordinateX();
        askCoordinateY();
        askPrice();
        askDiscount();
        askRefundable();
        askTicketType();
        askEventName();
        askEventId();
        askEventDescription();
        askEventType();
    }

    @Override
    public Long askTicketId() {
        String str;
        try {
            println("Enter the Ticket id: ");
            str = scanner.nextLine();
            if (InputChecker.checkLong(str.trim()))
                setTicketId(Long.parseLong(str));
            else {
                println("Ticket id should be long, try again: ");
                if (!isScript)
                    askTicketId();

            }
        } catch (Exception e) {
            print("Input error");
            if (!isScript)
                askTicketId();
        }
        return null;
    }

    @Override
    public void askName() {
        this.println("Input Ticket name: ");
        try {
            setName(inputLine());
        } catch (InvalidFieldException e) {
            println("Ticket name should be String and can't be null");
            if (!isScript)
                askName();
        }
    }

    @Override
    public void askCoordinateX() {
        this.println("Input Coordinate X: ");
        try {
            setCoordinateX(Long.parseLong(inputLine()));
        } catch (InvalidFieldException | NumberFormatException e) {
            println("Coordinate x should be long");
            if (!isScript)
                askCoordinateY();
        }
    }

    @Override
    public void askCoordinateY() {
        this.println("Input Coordinate Y: ");
        try {
            setCoordinateY(Integer.parseInt(inputLine()));
        } catch (InvalidFieldException | NumberFormatException e) {
            println("Coordinate Y should be int");
            if (!isScript)
                askCoordinateY();
        }
    }

    @Override
    public void askPrice() {
        this.println("Input Ticket price: ");
        try {
            setPrice(Float.parseFloat(inputLine()));
        } catch (InvalidFieldException e) {
            println("Ticket price should be float");
            if (!isScript)
                askPrice();
        }
    }

    @Override
    public void askDiscount() {
        this.println("Input Ticket discount");
        try {
            setDiscount(Long.parseLong(inputLine()));
        } catch (InvalidFieldException | NumberFormatException e) {
            println("Ticket discount should be long, greater than 0 and less than 100");
            if (!isScript)
                askDiscount();
        }
    }

    @Override
    public void askRefundable() {
        this.println("Input the Ticket refundable: ");
        try {
            setRefundable(Boolean.parseBoolean(inputLine()));
        } catch (InvalidFieldException e) {
            println("Ticket refundable should be boolean");
            if (!isScript)
                askRefundable();
        }
    }

    @Override
    public void askTicketType() {
        TicketType.printType();
        this.println("Input the Ticket type: ");
        try {
            setTicketType(checkTicketType(inputLine()));
        } catch (InvalidFieldException | EnumNotFoundException e) {
            println("Ticket type field can't be null");
            if (!isScript)
                askTicketType();
        }
    }

    @Override
    public void askEventId() {
        this.println("Input the event id: ");
        try {
            setEventId(Long.parseLong(inputLine()));
        } catch (InvalidFieldException e) {
            println("Event id should be long");
            if (!isScript)
                askEventId();
        }
    }

    @Override
    public void askEventName() {
        this.println("Input the event name: ");
        try {
            setEventName(inputLine());
        } catch (InvalidFieldException e) {
            println("Event name field can't be null and void");
            if (!isScript)
                askEventName();
        }
    }

    @Override
    public void askEventDescription() {
        this.println("Input the event description: ");
        try {
            setDescription(inputLine());
        } catch (InvalidFieldException e) {
            println("Event description can't be void");
            if (!isScript)
                askEventDescription();
        }
    }

    @Override
    public void askEventType() {
        this.println("Input the event type: ");
        try {
            setEventType(checkEventType(inputLine()));
        } catch (InvalidFieldException | EnumNotFoundException e) {
            println(e.getMessage());
            if (!isScript)
                askEventType();
        }
    }

    @Override
    public Ticket askTicket() {
        inputFieldsFile();
        return getTicket();
    }

    @Override
    public void askUserName() {
        println("Input username: ");
        try {
            setUsername(inputLine());
        } catch (InvalidFieldException e) {
            println("Username should be string, can't be null");
            if (!isScript)
                askUserName();
        }
    }

    private String inputLine() {
        line = scanner.nextLine().trim().toLowerCase();
        return line;
    }
}
