package stat;

import java.net.URISyntaxException;
import java.nio.file.Path;

import stat.parser.CityCsvParser;

class CityCsvParserTest extends ParserTest {

	private static final String FILE_NAME = "address.csv";

	public CityCsvParserTest() throws URISyntaxException {
		super(new CityCsvParser(), Path.of(CityCsvParserTest.class.getClassLoader().getResource(FILE_NAME).toURI()));
	}

}
