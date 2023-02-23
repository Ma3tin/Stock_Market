import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class StockMarket {
    private int id;
    private String name;
    private String tickerSymbol;

    public StockMarket() {
    }

    public StockMarket(int id, String name, String tickerSymbol) {
        this.id = id;
        this.name = name;
        this.tickerSymbol = tickerSymbol;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }
    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }
}
