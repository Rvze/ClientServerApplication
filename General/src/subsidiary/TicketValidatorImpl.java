package subsidiary;

import exceptions.InvalidFieldException;
import general.TicketType;

public class TicketValidatorImpl implements TicketValidator {
    @Override
    public void validateName(String name) throws InvalidFieldException {
        if (name == null || name.equals(" ")) {
            throw new InvalidFieldException("Invalid value for ticket name");
        }
    }

    @Override
    public void validateCoordinateY(Integer y) throws InvalidFieldException {
        if (y == null)
            throw new InvalidFieldException("Invalid value for coordinate(y)");
    }

    @Override
    public void validatePrice(Float price) throws InvalidFieldException {
        if (price < 0)
            throw new InvalidFieldException("Invalid value for price");
    }

    @Override
    public void validateDiscount(Long discount) throws InvalidFieldException {
        if (100 <= discount && discount < 0)
            throw new InvalidFieldException("Invalid value for discount");
    }

    @Override
    public void validateTicketType(TicketType ticketType) throws InvalidFieldException {
        if (ticketType == null)
            throw new InvalidFieldException("Invalid value for ticketType");
    }

    @Override
    public void validateEventName(String eventName) throws InvalidFieldException {
        if (eventName == null || eventName.equals(" "))
            throw new InvalidFieldException("Invalid value for event name");
    }

    @Override
    public void validateDescription(String description) throws InvalidFieldException {
        if (description.equals(" "))
            throw new InvalidFieldException("Invalid value for event description");
    }

    @Override
    public void validateUserName(String userName) throws InvalidFieldException {
        if (userName == null || userName.equals(" "))
            throw new InvalidFieldException("Invalid value for username");
    }
}
