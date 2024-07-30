package streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Application {

	public static void main(String[] args) {
		
		Stream<Integer> intStr = Stream.of(12,24,5,2,5,4,64,64,-1);
		long count = intStr
				.filter(v -> v < 5).count();
		System.out.println("Count is: "+count);
		
		Stream<Integer> intStr2 = Stream.of(12,24,5,2,5,4,64,64,-1);
		int result = intStr2.reduce(0, (i, v) -> v+i);
		System.out.println("Result is :"+result);
		
		Stream<String> words = Stream.of("Bonga", "Thato","Huawei","Samsung","Panasonic","WitBixs","UJ","Sahara","Creche");
		
		Map<Integer, String> map = words.collect(Collectors.toMap(k -> k.length(),			  // Keys
																			v -> v, 			  // Values
																			(k, v) -> k+","+v )); //Defines way to handle duplicate keys
		System.out.println(map);
		
		String[] words_ = {"Bonga", "Thato","Huawei","Pulse","Samsung","Panasonic","Wits","UJ","DYICT","Creche","Poi"};
		List<String> w2 = Stream.of(words_).sorted().filter(e -> e.startsWith("P") || e.endsWith("s")).collect(Collectors.toList());
		for(String w : w2) {
			System.out.println(w);
		}
		
		Stream.of("Bonga","Aluminium", "Coryo","Huawei","Samsung","Panasonic","WitBixs","UJ","Sahara","Creche")
		.filter(w -> w.length() > 3 && !w.startsWith("C"))
		.forEach(System.out::println);

	}

}
