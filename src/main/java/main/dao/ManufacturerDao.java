package main.dao;

import main.config.DatabaseConfig;
import main.model.Manufacturer;
import main.model.ManufacturerPrice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManufacturerDao {
    private final DatabaseConfig databaseConfig;

    public ManufacturerDao() {
        databaseConfig = new DatabaseConfig();
    }

    public List<ManufacturerPrice> getManufacturersByLowerTotalSum(double maxTotalSum) {
        String sql = """
                select m.name, sum(w.price * w.quantity) as totalPrice
                from manufacturers m
                join watches w on m.manufacturer_id = w.manufacturer_id
                group by m.manufacturer_id, m.name, m.country
                having sum(w.price * w.quantity) <= ?;
                """;
        try(Connection conn = databaseConfig.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setDouble(1, maxTotalSum);
            try(ResultSet rs = stmt.executeQuery()) {
                List<ManufacturerPrice> manufacturers = new ArrayList<>();
                while (rs.next()) {
                    manufacturers.add(new ManufacturerPrice(rs.getString("name"), rs.getDouble("totalPrice")));
                }
                return manufacturers;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Помилка отримання виробників за загальною сумою", e);
        }
    }

    private Manufacturer mapRowToManufacturer(ResultSet rs) throws SQLException {
        return new Manufacturer(
                rs.getInt("manufacturer_id"),
                rs.getString("name"),
                rs.getString("country")
        );
    }
}
