package beans;

import java.util.ArrayList;
import annotation.Autowired;
import annotation.Component;
import model.Orders;

@Component
public class DataService {
	
	@Autowired
	public final DataRepository dataRepository;
	
	public DataService(DataRepository dataRepo) {
		this.dataRepository = dataRepo;
	}
	
	public ArrayList<Orders> getAllOrders(){
		return dataRepository.orders;
	}

}
