package stat;

import java.nio.file.Paths;
import java.util.Scanner;

import stat.exception.UnsupportedFileException;
import stat.parser.Parser;
import stat.parser.ParserFactory;
import stat.parser.ParserFactory.ParserType;
import stat.processor.DetailsProcessor;

public class App {

	public static void main(String[] args) {
		try (var scanner = new Scanner(System.in)) {
			var input = getInput(scanner);
			while (!input.equalsIgnoreCase("exit")) {
				try {
					var path = Paths.get(input);
					var parser = getParser(input);
					System.out.println("\nОжидайте\n");
					var details = parser.parse(path);
					var processor = new DetailsProcessor(details);
					System.out.println(processor.getDuplicatesString());
					System.out.println(processor.getBuldingsStatString());
				} catch (Exception e) {
					e.printStackTrace();
				}
				input = getInput(scanner);
			}
			System.out.println("Программа завершена");
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

	private static String getInput(Scanner scanner) {
		System.out.print("\nВведите путь к файлу или exit для выхода:\n");
		return scanner.nextLine();
	}

}
