package stat;

import java.nio.file.Path;

import stat.parser.CityCsvParser;
import stat.processor.DetailsProcessor;

public class App {

	public static void main(String[] args) {
		var path = Path.of(App.class.getClassLoader().getResource("address.csv").getFile());
		var parser = new CityCsvParser();
		var start = System.currentTimeMillis();
		var details = parser.parse(path);
		var end = System.currentTimeMillis();
		var processor = new DetailsProcessor(details);
		System.out.println(processor.getDuplicatesString());
		System.out.println(processor.getBuldingsStatString());
		System.out.printf("Time execution is %d\n", end - start);
	}
}
