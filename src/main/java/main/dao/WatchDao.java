package main.dao;

import main.config.DatabaseConfig;
import main.model.Watch;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WatchDao {
    private final DatabaseConfig databaseConfig;

    public WatchDao() {
        databaseConfig = new DatabaseConfig();
    }

    public List<String> getBrandsByType(String type) {
        String sql = """
                select brand from watches 
                where type = ?;
                """;
        try(Connection conn = databaseConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, type);
            return getBrands(ps);
        } catch (SQLException e) {
            throw new RuntimeException("Помилка отримання марок за типом", e);
        }
    }

    public List<String> getBrandsByCountry(String country) {
        String sql = """
                select distinct w.brand from watches w
                join manufacturers m on w.manufacturer_id = m.manufacturer_id
                where m.country = ?;
               """;
        try(Connection conn = databaseConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, country);
            return getBrands(ps);
        } catch (SQLException e) {
            throw new RuntimeException("Помилка отримання марок за країною", e);
        }
    }

    private List<String> getBrands(PreparedStatement ps) {
        try(ResultSet rs = ps.executeQuery()) {
            List<String> brands = new ArrayList<>();
            while (rs.next()) {
                brands.add(rs.getString("brand"));
            }
            return brands;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Watch> getMechanicalWatchesUnderMaxPrice(Double maxPrice) {
        String sql = """
                select * from watches 
                where price <= ? and type = 'mechanical';
                """;
        try(Connection conn = databaseConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setDouble(1, maxPrice);
            try(ResultSet rs = ps.executeQuery()) {
                List<Watch> watches = new ArrayList<>();
                while (rs.next()) {
                    watches.add(mapRowToWatch(rs));
                }
                return watches;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Помилка отримання механічних годинників за ціною", e);
        }
    }

    private Watch mapRowToWatch(ResultSet rs) throws SQLException {
        return new Watch(
                rs.getInt("watch_id"),
                rs.getString("brand"),
                rs.getString("type"),
                rs.getDouble("price"),
                rs.getInt("quantity"),
                rs.getInt("manufacturer_id")
        );
    }
}
