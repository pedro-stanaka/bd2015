package dao;

import model.TaxiPosition;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Describe purpose of this file HERE MTF!
 *
 * @author Pedro Tanaka <pedro.stanaka@gmail.com>
 */
public class TaxiDAO extends DAO<TaxiPosition>{

    private final String queryRandom = "SELECT lat, lng, id, observ " +
            "FROM taxi_positions " +
            "WHERE id = 3 AND " +
            "observ BETWEEN '2008-02-03 00:00:00.000000'::TIMESTAMP WITHOUT TIME ZONE AND '2008-02-03 02:05:00.000000'::TIMESTAMP WITHOUT TIME ZONE " +
            "ORDER BY observ ASC;";


    TaxiDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(TaxiPosition taxiPosition) throws SQLException, NotImplementedException {
        throw new NotImplementedException();
    }

    @Override
    public TaxiPosition read(Integer id) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void update(TaxiPosition taxiPosition) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public void delete(Integer id) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public List<TaxiPosition> all() throws SQLException {
        throw new NotImplementedException();
    }

    public List<TaxiPosition> randomTrajectory() {
        try {
            PreparedStatement statement = connection.prepareStatement(queryRandom);
            ResultSet result = statement.executeQuery();

            List<TaxiPosition> positions = new ArrayList<>();
            while (result.next()) {
                TaxiPosition position = new TaxiPosition();

                position.setId(result.getInt("id"));
                position.setObserv(result.getTimestamp("observ"));
                position.setLat(result.getDouble("lat"));
                position.setLng(result.getDouble("lng"));

                positions.add(position);
            }

            return positions;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
