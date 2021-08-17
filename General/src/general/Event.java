package general;

import java.io.Serializable;

public class Event implements Serializable {
    private static final long serialVersionUID = 9853L;
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String description; //Строка не может быть пустой, Поле может быть null
    private EventType eventType; //Поле может быть null

    public Event(String name, String description, EventType eventType) {
        this.name = name;
        this.description = description;
        this.eventType = eventType;
    }

    public Event() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EventType getEventType() {
        return eventType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", eventType=" + eventType +
                '}';
    }
}

