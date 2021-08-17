package database;

import collection.ServerTicket;
import exceptions.EnumNotFoundException;
import exceptions.InvalidFieldException;
import exceptions.SQLNoDataException;
import general.User;
import subsidiary.TicketBuilder;

import java.sql.*;
import java.time.ZonedDateTime;
import java.util.concurrent.CopyOnWriteArrayList;

public class TicketDataBaseImpl implements TicketDataBase {
    private final static String URL = "jdbc:postgresql://localhost:5432/postgres";
    private final static String login = "postgres";
    private final static String password = "makarov";
    private final TicketBuilder builder;

    public TicketDataBaseImpl(TicketBuilder builder) {
        this.builder = builder;
    }

    @Override
    public ServerTicket getTicket(long id) throws SQLNoDataException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM studs WHERE id = ?")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            return buildTicket(resultSet);

        } catch (SQLException e) {
            throw new SQLNoDataException();

        }
    }

    @Override
    public int getNextId() throws SQLNoDataException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT MAX(id) FROM studs")) {
            ResultSet res = statement.executeQuery();
            res.next();
            return res.getInt(1) + 1;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLNoDataException();
        }
    }

    @Override
    public void insertTicket(ServerTicket serverTicket) throws Exception {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO studs VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)")) {
            initStatement(statement, serverTicket);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean deleteTicket() {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("truncate studs")) {
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteTicketOfUsers(User user) throws SQLNoDataException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM tickets WHERE username = ?")) {
            statement.setString(1, user.getUserName());
            statement.execute();
            return true;
        } catch (SQLException e) {
            throw new SQLNoDataException();
        }
    }

    @Override
    public boolean deleteTicketById(long id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM tickets WHERE id = ?")) {
            statement.setInt(1, (int) id);
            statement.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public void updateTicket(long id, ServerTicket serverTicket) throws SQLNoDataException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE tickets SET " +
                     "name = ?, " +
                     "coordinate_x = ?," +
                     "coordinate_y = ?, " +
                     "zoned_date_time = ?, " +
                     "price = ?, " +
                     "discount = ?, " +
                     "refundable = ?, " +
                     "ticket_type = ?, " +
                     "event_id = ?, " +
                     "event_name = ?, " +
                     "event_description = ?, " +
                     "event_type = ?, " +
                     "username = ? " +
                     "WHERE id = ?")) {
            initStatement(statement, serverTicket);
            statement.setLong(13, id);
            statement.execute();
        } catch (SQLException e) {
            throw new SQLNoDataException();
        }
    }

    @Override
    public CopyOnWriteArrayList<ServerTicket> getAll() throws SQLNoDataException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM tickets")) {
            CopyOnWriteArrayList<ServerTicket> arrayList = new CopyOnWriteArrayList<>();
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                arrayList.add(buildTicket(res));
            }
            return arrayList;
        } catch (SQLException e) {
            throw new SQLNoDataException();
        }
    }

    private ServerTicket buildTicket(ResultSet res) throws SQLException {
        try {
            builder.setName(res.getString(2));
            builder.setCoordinateX(res.getLong(3));
            builder.setCoordinateY(res.getInt(4));
            builder.setCreationDate(ZonedDateTime.parse(res.getString(5)));
            builder.setPrice(res.getFloat(6));
            builder.setDiscount(res.getLong(7));
            builder.setRefundable(res.getBoolean(8));
            builder.setTicketType(builder.checkTicketType(res.getString(9)));
            builder.setEventName(res.getString(10));
            builder.setEventId(res.getLong(11));
            builder.setDescription(res.getString(12));
            builder.setEventType(builder.checkEventType(res.getString(13)));
            builder.setUsername(res.getString(14));
            return new ServerTicket(builder.getTicket(), res.getInt(1));

        } catch (InvalidFieldException | EnumNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Connection getConnection() throws SQLException {
        //    DataBaseConnector dataBaseConnector = new DataBaseConnector();
        // dataBaseConnector.connect();
        //return dataBaseConnector.getCon();
        return DriverManager.getConnection(URL, login, password);
    }

    private void initStatement(PreparedStatement statement, ServerTicket serverTicket) throws SQLException {
        statement.setString(1, serverTicket.getName());
        statement.setLong(2, serverTicket.getCoordinates().getX());
        statement.setInt(3, serverTicket.getCoordinates().getY());
        statement.setString(4, serverTicket.getCreationDate().toString());
        statement.setFloat(5, serverTicket.getPrice());
        statement.setLong(6, serverTicket.getDiscount());
        statement.setBoolean(7, serverTicket.isRefundable());
        statement.setString(8, serverTicket.getType().getUrl());
        statement.setLong(9, serverTicket.getEvent().getId());
        statement.setString(10, serverTicket.getEvent().getName());
        statement.setString(11, serverTicket.getEvent().getDescription());
        statement.setString(12, serverTicket.getEvent().getEventType().getUrl());
        statement.setString(13, serverTicket.getUserName());

    }
}
