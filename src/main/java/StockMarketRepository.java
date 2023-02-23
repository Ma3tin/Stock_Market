import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Named
public class StockMarketRepository {
    public void createNewStock(String symbol, String name) {
        try (
                Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost/stock_market?user=root&password=");
                PreparedStatement preparedStatement = connection.prepareStatement("""
                        INSERT INTO stock_market (ticker_symbol, descriptive_name)
                        VALUES (?, ?)
                        """
                );


        ) {
            preparedStatement.setString(1, symbol);
            preparedStatement.setString(2, name);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteMarket(int id) {
        try (

                Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost/stock_market?user=root&password=");
                PreparedStatement preparedStatement = connection.prepareStatement("""
                        DELETE FROM stock_market
                        WHERE stock_market_id = ?;
                        """
                );


        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<StockMarket> getAll() {
        try (
                Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost/stock_market?user=root&password=");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("""
                                        select sm.stock_market_id, sm.ticker_symbol, sm.descriptive_name
                                        from stock_market as sm
                        """)
        ) {
            ArrayList<StockMarket> stockMarkets = new ArrayList<>();

            while (resultSet.next()) {
                stockMarkets.add(new StockMarket(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3)
                ));
            }

            return stockMarkets;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public StockMarket getOneMarket(int id) {
        try (
                Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost/stock_market?user=root&password=");
                PreparedStatement preparedStatement = connection.prepareStatement("""
                                        select sm.stock_market_id, sm.ticker_symbol, sm.descriptive_name
                                        from stock_market as sm
                                        where sm.stock_market_id = ?
                        """)
        ) {


            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return new StockMarket(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3)
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editMarket(int id, String name, String tickerSymbol) {
        try (
                Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost/stock_market?user=root&password=");
                PreparedStatement preparedStatement = connection.prepareStatement("""
                                        UPDATE stock_market
                                        SET ticker_symbol = ?, descriptive_name = ?
                                        WHERE stock_market_id = ?;
                        """)
        ) {

            preparedStatement.setString(1, tickerSymbol);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, id);

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
