package stat;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URISyntaxException;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import stat.parser.CityXmlParser;

class CityXmlParserTest extends ParserTest {

	private static final String FILE_NAME = "address.xml";
	private final CityXmlParser parser;

	public CityXmlParserTest() throws URISyntaxException {
		super(Path.of(CityXmlParserTest.class.getClassLoader().getResource(FILE_NAME).toURI()));
		this.parser = new CityXmlParser();
	}

	@Test
	void shouldReturnCorrectDuplicatesMap() {
		var details = parser.parse(testDataFile);
		var duplicates = details.getDuplicates();
		assertEquals(expectedDuplicates, duplicates);
	}

	@Test
	void shouldReturnCorrectStatistic() {
		var details = parser.parse(testDataFile);
		var stat = details.getBuildindsQuantity();
		assertEquals(expectedStatistic, stat);
	}

}
