package stat;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URISyntaxException;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import stat.parser.CityCsvParser;

@TestInstance(Lifecycle.PER_CLASS)
class CityCsvParserTest extends ParserTest {

	private static final String FILE_NAME = "address.csv";
	private final CityCsvParser parser;

	public CityCsvParserTest() throws URISyntaxException {
		super(Path.of(CityCsvParserTest.class.getClassLoader().getResource(FILE_NAME).toURI()));
		parser = new CityCsvParser();
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
