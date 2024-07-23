package orm;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import annotations.Column;
import annotations.SerialNumber;

public class Hibernate<T> {

	private Connection connection;
	private AtomicLong id = new AtomicLong(0L);
	
	public static <T> Hibernate<T> getConnection() throws Exception {
		return new Hibernate<T>();
	}
	
	private Hibernate() throws Exception{
		this.connection = DriverManager
				.getConnection("jdbc:h2:C:/Users/gmvn/Documents/Java/Valyria/SalladhorSaan/database/databasefile","user","");
	}
	
	public T objectReader(T t, long id) throws SQLException,
												NoSuchMethodException, 
												SecurityException, 
												InstantiationException, // or just use Exception
												IllegalAccessException, 
												IllegalArgumentException, 
												InvocationTargetException {
		Field[] flds = t.getClass().getDeclaredFields();
		// Find the primary key to query by
		Field primaryKey = null;
		for(Field f : flds) {
			if(f.isAnnotationPresent(SerialNumber.class)) {
				primaryKey = f;
				break;
			}
		}
		String sql = "select * from "+t.getClass().getSimpleName() + " where "+primaryKey.getName()+"="+id;
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		Constructor<? extends Object> constructor = t.getClass().getDeclaredConstructor();
		constructor.setAccessible(true);
		T obj = (T) constructor.newInstance();
		
		while(rs.next()) {
			for(Field f : flds) {
				if(f.getType() == String.class) {
					String strValue = rs.getString(f.getName());
				}else if(f.getType() == long.class){
					int intvalue = rs.getInt(f.getModifiers());
				}else if(f.getType() == double.class) {
					double dbl = rs.getDouble(f.getName());
				}
			}
		}
		return t;
	}
	
	public T objectRepository(T t) throws SQLException,
										  IllegalArgumentException,
										  IllegalAccessException {
		//write logic
		Class<? extends Object> clss = t.getClass();
		Field[] flds = t.getClass().getDeclaredFields();

		System.out.println("Size of fields "+flds.length);
		
		StringJoiner joiner = new StringJoiner(",");
		Field pkey = null;
		ArrayList<Field> columns = new ArrayList<>();
		
		for(Field f : flds) {
			if(f.isAnnotationPresent(SerialNumber.class)) {
				pkey = f;
			}else if(f.isAnnotationPresent(Column.class)) {
				f.setAccessible(true);
				joiner.add(f.getName());
				columns.add(f);
			}
		}
		
		int columnCount = columns.size()+1;
		
		String sqlPlaceHolders = IntStream.range(0, columnCount)
				.mapToObj(e -> "?")
				.collect(Collectors.joining());
		String sql = "insert into "+ clss.getSimpleName()+ " ("+pkey.getName()+","+joiner+")"+ "values" +"(?,?,?,?)";
		System.out.println("view sql statement:\n "+sql);
		
		//Set first field (primary key) in the sql statement
		PreparedStatement stmt = connection.prepareStatement(sql);
		if(pkey.getType() == long.class) {
			stmt.setLong(1, id.incrementAndGet());
		}
		//Set other fields, ints, strings, etc.
		int indexCount = 2;
		for(Field f : columns) {
			if(f.getType() == int.class) {
				stmt.setInt(indexCount++, (int)f.get(t));
			}else if(f.getType() == double.class) {
				stmt.setDouble(indexCount++,(double) f.get(t));
			}else if(f.getType() == String.class){
				stmt.setString(indexCount++, (String)f.get(t));
			}
		}
		
		stmt.executeUpdate();
		
		return t;
	}
}
