package stat.parser;

public class ParserFactory {

	private ParserFactory() {
	}

	public static Parser getParser(ParserType type) {
		return switch (type) {
			case CSV -> new CityCsvParser();
			case XML -> new CityXmlParser();
		};
	}

	public enum ParserType {
		CSV, XML
	}
}
