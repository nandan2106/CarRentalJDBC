import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {

    public void addCar(CarDTO car) {
        String sql = "INSERT INTO cars(brand, model, price_per_day, available) VALUES(?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, car.getBrand());
            ps.setString(2, car.getModel());
            ps.setInt(3, car.getPricePerDay());
            ps.setBoolean(4, car.isAvailable());

            ps.executeUpdate();
            System.out.println("Car added successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CarDTO getCar(int id) {
        String sql = "SELECT * FROM cars WHERE car_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new CarDTO(
                        rs.getInt("car_id"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getInt("price_per_day"),
                        rs.getBoolean("available")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<CarDTO> getAllCars() {
        List<CarDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM cars";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new CarDTO(
                        rs.getInt("car_id"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getInt("price_per_day"),
                        rs.getBoolean("available")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void updateCar(CarDTO car) {
        String sql = "UPDATE cars SET brand=?, model=?, price_per_day=?, available=? WHERE car_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, car.getBrand());
            ps.setString(2, car.getModel());
            ps.setInt(3, car.getPricePerDay());
            ps.setBoolean(4, car.isAvailable());
            ps.setInt(5, car.getId());

            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("Car updated successfully!");
            else System.out.println("Car not found!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCar(int id) {
        String sql = "DELETE FROM cars WHERE car_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) System.out.println("Car deleted successfully!");
            else System.out.println("Car not found!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void rentCar(int id) {
        String checkSql = "SELECT available FROM cars WHERE car_id=?";
        String updateSql = "UPDATE cars SET available=false WHERE car_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement checkPs = con.prepareStatement(checkSql)) {

            checkPs.setInt(1, id);
            ResultSet rs = checkPs.executeQuery();

            if (!rs.next()) {
                System.out.println("Car not found!");
                return;
            }

            if (!rs.getBoolean("available")) {
                System.out.println("Car already rented!");
                return;
            }

            try (PreparedStatement updatePs = con.prepareStatement(updateSql)) {
                updatePs.setInt(1, id);
                updatePs.executeUpdate();
                System.out.println("Car rented successfully!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void returnCar(int id) {
        String updateSql = "UPDATE cars SET available=true WHERE car_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(updateSql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) System.out.println("Car returned successfully!");
            else System.out.println("Car not found!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
