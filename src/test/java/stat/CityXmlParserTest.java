package stat;

import java.net.URISyntaxException;
import java.nio.file.Path;

import stat.parser.CityXmlParser;

class CityXmlParserTest extends ParserTest {

	private static final String FILE_NAME = "address.xml";

	public CityXmlParserTest() throws URISyntaxException {
		super(new CityXmlParser(), Path.of(CityXmlParserTest.class.getClassLoader().getResource(FILE_NAME).toURI()));
	}

}
