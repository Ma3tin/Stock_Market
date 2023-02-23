import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.Serializable;

@SessionScoped
@Named

public class SaveMarket implements Serializable {
    StockMarketService smc = new StockMarketService();
    private StockMarket activeMarket = smc.getOneMarket();

    public StockMarket getActiveMarket() {
        if (activeMarket.getId() != Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"))) activeMarket = smc.getOneMarket();
        return activeMarket;
    }
}
