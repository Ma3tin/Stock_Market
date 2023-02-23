import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.IOException;
import java.util.List;

@ApplicationScoped
@Named
public class StockMarketService {
    public void createNewStock(String symbol, String name) {
        StockMarketRepository smr = new StockMarketRepository();
        smr.createNewStock(symbol, name);
    }

    public List<StockMarket> getAll() {
        StockMarketRepository smr = new StockMarketRepository();
        return smr.getAll();
    }

    public void deleteMarket() {
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        StockMarketRepository smr = new StockMarketRepository();
        smr.deleteMarket(Integer.parseInt(id));
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("mainPage.xhtml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public StockMarket getOneMarket() {
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        StockMarketRepository smr = new StockMarketRepository();
        return smr.getOneMarket(Integer.parseInt(id));
    }

    public void editMarket(String name, String tickerSymbol) {
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        StockMarketRepository smr = new StockMarketRepository();
        smr.editMarket(Integer.parseInt(id), name, tickerSymbol);
    }
}
