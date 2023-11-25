package stat;

import java.nio.file.Path;
import java.nio.file.Paths;

import stat.exception.UnsupportedFileException;
import stat.parser.Parser;
import stat.parser.ParserFactory;
import stat.parser.ParserFactory.ParserType;
import stat.processor.DetailsProcessor;

public class App {

	public static void main(String[] args) {
		var filename = "address.xml";
		try {
			Path path = Paths.get(App.class.getClassLoader().getResource(filename).getPath());
			var parser = getParser(filename);
			var details = parser.parse(path);
			var processor = new DetailsProcessor(details);
			System.out.println(processor.getDuplicatesString());
			System.out.println(processor.getBuldingsStatString());
		} catch (UnsupportedFileException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	private static Parser getParser(String filename) {
		var extension = getFileExtension(filename);
		return switch (extension.toLowerCase()) {
			case "csv" -> ParserFactory.getParser(ParserType.CSV);
			case "xml" -> ParserFactory.getParser(ParserType.XML);
			default -> throw new UnsupportedFileException("File %s is unsupported".formatted(filename));
		};
	}

	private static String getFileExtension(String filename) {
		var dotIndex = filename.lastIndexOf(".");
		return dotIndex >= 0 ? filename.substring(dotIndex + 1) : "";
	}

}
