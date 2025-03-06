package model;

import java.time.LocalDateTime;
import java.util.Map;

public class Orders {
	public int orderNumber;
	public String orderDate;
	public String requiredDate;
	public String shippedDate;
	public String status;
	public String comments;
	@Override
	public String toString() {
		return "Orders [orderNumber=" + orderNumber + ", orderDate=" + orderDate + ", requiredDate=" + requiredDate
				+ ", shippedDate=" + shippedDate + ", status=" + status + ", comments=" + comments + ", customerNumber="
				+ customerNumber + "]";
	}

	public int customerNumber;
	
	public void initializeObjectData(Map<String, String> rowData) {
		this.orderNumber = Integer.valueOf(rowData.get("orderNumber"));
		this.orderDate = rowData.get("orderDate");
		this.requiredDate = rowData.get("requiredDate");
		
		// Handle null shippedDate
	    String shippedDate = rowData.get("shippedDate");
	    this.shippedDate = (shippedDate != null) ? shippedDate : LocalDateTime.now().toString();
	    this.status = rowData.get("status");
	    
	    // Handle null comments
	    String comments = rowData.get("comments");
	    this.comments = (comments != null) ? comments : "placeholder_string";
	    this.customerNumber = Integer.valueOf(rowData.get("customerNumber"));
	}

}
