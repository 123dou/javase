package command.example;

import java.util.ArrayList;
import java.util.List;

public class Waiter {
    private List<Command> order = new ArrayList<>();
    public void setOrder(Command command) {
    	order.add(command);
    	System.out.println("增加订单: " + command.toString() + System.currentTimeMillis());
    }
    public void CancelOrder(Command command) {
    	order.remove(command);
    	System.out.println("取消订单: " + command.toString() + System.currentTimeMillis());
    }
    public void executeCommand() {
    	order.forEach(command -> command.execute());
    }
}
