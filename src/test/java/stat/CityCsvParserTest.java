package stat;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URISyntaxException;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import stat.parser.CityCsvParser;

@TestInstance(Lifecycle.PER_CLASS)
class CityCsvParserTest extends CsvParserTest {

	private static final String FILE_NAME = "address.csv";
	private final Path csvFile;
	private final CityCsvParser parser;

	public CityCsvParserTest() throws URISyntaxException {
		this.csvFile = Path.of(CityCsvParserTest.class.getClassLoader().getResource(FILE_NAME).toURI());
		parser = new CityCsvParser();
	}

	@Test
	void shouldReturnCorrectDuplicatesMap() {
		var details = parser.parse(csvFile);
		var duplicates = details.getDuplicates();
		assertEquals(expectedDuplicates, duplicates);
	}
	
	@Test
	void shouldReturnCorrectStatistic() {
		var details = parser.parse(csvFile);
		var stat = details.getBuildindsQuantity();
		assertEquals(expectedStatistic, stat);
	}

}
