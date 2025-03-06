
import java.util.List;

import beans.DataService;
import config.ApplicationContext;
import config.ProjectConfig;
import model.Orders;

public class Main {
	
	public static void main(String[] args) {
		ApplicationContext app = new ApplicationContext(ProjectConfig.class);
		DataService dataService = app.getBean(DataService.class);
		List<Orders> orders = dataService.getAllOrders();
		for(Orders order : orders) {
			System.out.println(order.toString());
		}
	}
}
