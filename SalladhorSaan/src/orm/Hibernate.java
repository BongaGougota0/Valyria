package orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.concurrent.atomic.AtomicLong;

public class Hibernate<T> {

	private Connection connection;
	private AtomicLong id = new AtomicLong(0L);
	
	public <T> Hibernate<T> getConnection() throws Exception {
		return new Hibernate<T>();
	}
	
	private Hibernate() throws Exception{
		this.connection = DriverManager.getConnection("jdbc:h2:./Database","user","");
	}
	
	public T objectRepository(T t) {
		//write logic
		
		return t;
	}
}
