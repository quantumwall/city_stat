package stat;

import java.nio.file.Path;
import java.nio.file.Paths;

import stat.model.Details;
import stat.parser.CityCsvParser;
import stat.parser.Parser;
import stat.processor.DetailsProcessor;

public class App {

	public static void main(String[] args) {
		var filename = "address.csv";
		Path path = Paths.get(App.class.getClassLoader().getResource(filename).getPath());
		var details = parse(new CityCsvParser(), path);
		var processor = new DetailsProcessor(details);
		System.out.println(processor.getDuplicatesString());
		System.out.println(processor.getBuldingsStatString());
	}

	private static Details parse(Parser parser, Path path) {
		return parser.parse(path);
	}
}
