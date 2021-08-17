package subsidiary;

import exceptions.InvalidFieldException;
import general.TicketType;

public interface TicketValidator {
    void validateName(String name) throws InvalidFieldException;

    void validateCoordinateY(Integer y) throws InvalidFieldException;

    void validatePrice(Float price) throws InvalidFieldException;

    void validateDiscount(Long discount) throws InvalidFieldException;

    void validateTicketType(TicketType ticketType) throws InvalidFieldException;


    void validateEventName(String eventName) throws InvalidFieldException;

    void validateDescription(String description) throws InvalidFieldException;

    void validateUserName(String userName) throws InvalidFieldException;
}
