import java.sql.*;
import java.time.LocalDateTime;

public class OrderRepository {
    public void createNewOrder(int direction, int amount, int price, int stockId) {
        try (Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost/stock_market?user=root&password="); PreparedStatement preparedStatement = connection.prepareStatement("""
                INSERT INTO `order` (order_direction, amount, price, stock_market_id)
                values(?, ?, ?, ?)
                """);


        ) {
            preparedStatement.setInt(1, direction);
            preparedStatement.setInt(2, amount);
            preparedStatement.setInt(3, price);
            preparedStatement.setInt(4, stockId);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Order getOrder(int id) {
        try (Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost/stock_market?user=root&password="); PreparedStatement preparedStatement = connection.prepareStatement("""
                select o.order_id, o.order_direction, o.amount, o.price, o.stock_market_id
                from `order` as o
                where stock_market_id = ?
                """)


        ) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                System.out.println(resultSet.getInt(3));
                System.out.println(resultSet.getInt(4));
                System.out.println(resultSet.getInt(2));

                return new Order(resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}