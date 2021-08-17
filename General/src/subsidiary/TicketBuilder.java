package subsidiary;

import exceptions.EnumNotFoundException;
import exceptions.InvalidFieldException;
import general.EventType;
import general.Ticket;
import general.TicketType;

import java.time.ZonedDateTime;

public interface TicketBuilder {
    void setTicketId(Long id);

    void setName(String name) throws InvalidFieldException;

    void setCoordinateX(long x) throws InvalidFieldException;

    void setCoordinateY(Integer y) throws InvalidFieldException;

    void setPrice(Float price) throws InvalidFieldException;

    void setDiscount(Long discount) throws InvalidFieldException;

    Boolean setRefundable(boolean refundable) throws InvalidFieldException;

    void setUsername(String username) throws InvalidFieldException;

    void setTicketType(TicketType ticketType) throws InvalidFieldException;

    void setEventName(String eventName) throws InvalidFieldException;

    void setEventId(Long eventId) throws InvalidFieldException;

    void setDescription(String description) throws InvalidFieldException;

    void setEventType(EventType eventType) throws InvalidFieldException;

    void setCreationDate();

    void setCreationDate(ZonedDateTime parse) throws InvalidFieldException;

    Ticket getTicket();

    TicketType checkTicketType(String s) throws InvalidFieldException, EnumNotFoundException;

    EventType checkEventType(String s) throws InvalidFieldException, EnumNotFoundException;

    void inputFieldsFile();

    Long askTicketId();

    void askName();

    void askCoordinateX();

    void askCoordinateY();

    void askPrice();

    void askDiscount();

    void askRefundable();

    void askTicketType();

    void askEventId();

    void askEventName();

    void askEventDescription();

    void askEventType();

    Ticket askTicket();

    void askUserName();
}
