package interview.doctolib.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CalendarDao {

    // TODO. 注意java类型和java.sql类型的映射
    public List<Timestamp> getAvailableSlots(LocalDate localDate) throws Exception {
        List<Timestamp> slotsList = new ArrayList<>();
        String query = "Select * from t_calendar where kind = 'opening' and timestamp >= ? and timestamp <= ?";
        try (Connection connection = DriverManager.getConnection("url");
             PreparedStatement statement = connection.prepareStatement(query)) {

            // The first date is inclusive, but the second is exclusive
            statement.setDate(1, Date.valueOf(localDate));
            statement.setDate(2, Date.valueOf(localDate.plusDays(8)));

            ResultSet results = statement.executeQuery();
            while (results.next()) {
                slotsList.add(results.getTimestamp("timestamp"));
            }
        } catch (SQLException exception) {
            throw new Exception("Can not get available slots: " + exception.getMessage());
        }
        return slotsList;
    }
}
