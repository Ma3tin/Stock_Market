import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@ApplicationScoped
@Named
public class OrderService {
    public void createNewOrder(int direction, int amount, int price) {
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        OrderRepository or = new OrderRepository();
        or.createNewOrder(direction, amount, price, Integer.parseInt(id));
    }

    public void getOrder() {
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        OrderRepository or = new OrderRepository();
        or.getOrder(Integer.parseInt(id));
    }
}
