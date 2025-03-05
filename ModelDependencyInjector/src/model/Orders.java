package model;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

public class Orders {
	public int orderNumber;
	public String orderDate;
	public String requiredDate;
	public String shippedDate;
	public String status;
	public String comments;
	public int customerNumber;
	
	public void initializeObjectData(Map<String, String> rowData) {
		this.orderNumber = Integer.valueOf(rowData.get("orderNumber"));
		this.orderDate = rowData.get("orderDate");
		this.requiredDate = rowData.get("requiredDate");
		this.shippedDate = Objects.requireNonNull(rowData.get("shippedDate"), LocalDateTime.now().toString());
		this.status = rowData.get("status");
		this.comments = Objects.requireNonNull(rowData.get("comments"), "empty");
		this.customerNumber = Integer.valueOf(rowData.get("customerNumber"));
	}

}
