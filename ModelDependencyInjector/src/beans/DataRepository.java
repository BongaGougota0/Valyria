package beans;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import annotation.Component;
import annotation.PostConstruct;
import model.Orders;

@Component
public class DataRepository {
	public static String QUERY = "SELECT * FROM Orders";
	
	public ArrayList<Orders> orders = new ArrayList<>();
	
	@PostConstruct
	public void createDataByConnectingToMysql() {
		try {
			Connection connect = DriverManager
					.getConnection("localhost:3306/classicmodels", "root", "@ZXCp0001");
			Statement stmt = connect.createStatement();
			ResultSet rs =  stmt.executeQuery(QUERY);
			
			// Row data to capture.
			Map<String, String> rowData = null;
			Field[] fields = Orders.class.getDeclaredFields();
			
			while(rs.next()) {
				Orders order = new Orders();
				rowData = new HashMap<>();
				for(Field f : fields) {
					rowData.put(f.getName(), rs.getString(f.getName()));
				}
				order.initializeObjectData(rowData);
				orders.add(order);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
