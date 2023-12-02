package stat;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import stat.parser.Parser;

@TestInstance(Lifecycle.PER_CLASS)
public abstract class ParserTest {

	private final Map<String, Integer> expectedDuplicates;
	private final Map<String, Map<Integer, Integer>> expectedStatistic;
	private final Path testDataFile;
	private final Parser parser;

	public ParserTest(Parser parser, Path testDataFile) {
		this.parser = parser;
		this.testDataFile = testDataFile;
		expectedDuplicates = new HashMap<String, Integer>();
		expectedStatistic = new HashMap<String, Map<Integer, Integer>>();
	}

	@BeforeAll
	void initTestData() {
		expectedDuplicates.clear();
		expectedStatistic.clear();
		populateDuplicates(expectedDuplicates);
		populateStatistic(expectedStatistic);
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

	private void populateDuplicates(Map<String, Integer> duplicates) {
		duplicates.put("Барнаул;Дальняя улица;56;2", 2);
		duplicates.put("Братск;7-я Вишнёвая улица;49;5", 2);
		duplicates.put("Армавир;Евгения Родионова, улица;22;4", 4);
	}

	private void populateStatistic(Map<String, Map<Integer, Integer>> statistic) {
		statistic.put("Барнаул", new HashMap<Integer, Integer>(Map.of(2, 1)));
		statistic.put("Братск", new HashMap<Integer, Integer>(Map.of(5, 2, 4, 1)));
		statistic.put("Балаково", new HashMap<Integer, Integer>(Map.of(2, 1, 4, 1)));
		statistic.put("Азов", new HashMap<Integer, Integer>(Map.of(3, 1)));
		statistic.put("Видное", new HashMap<Integer, Integer>(Map.of(3, 1)));
		statistic.put("Батайск", new HashMap<Integer, Integer>(Map.of(4, 1)));
		statistic.put("Великий Новгород", new HashMap<Integer, Integer>(Map.of(1, 1)));
		statistic.put("Абакан", new HashMap<Integer, Integer>(Map.of(3, 2, 2, 1)));
		statistic.put("Бугульма", new HashMap<Integer, Integer>(Map.of(2, 1, 1, 1, 4, 1)));
		statistic.put("Ачинск", new HashMap<Integer, Integer>(Map.of(4, 1, 3, 1)));
		statistic.put("Балашов", new HashMap<Integer, Integer>(Map.of(1, 2)));
		statistic.put("Бийск", new HashMap<Integer, Integer>(Map.of(3, 1, 5, 1)));
		statistic.put("Армавир", new HashMap<Integer, Integer>(Map.of(1, 1, 4, 1)));
		statistic.put("Астрахань", new HashMap<Integer, Integer>(Map.of(5, 2)));
		statistic.put("Владивосток", new HashMap<Integer, Integer>(Map.of(3, 1, 1, 1)));
		statistic.put("Белово", new HashMap<Integer, Integer>(Map.of(1, 1)));
		statistic.put("Архангельск", new HashMap<Integer, Integer>(Map.of(3, 1)));
		statistic.put("Белебей", new HashMap<Integer, Integer>(Map.of(1, 1)));
		statistic.put("Благовещенск", new HashMap<Integer, Integer>(Map.of(2, 1)));
		statistic.put("Буйнакск", new HashMap<Integer, Integer>(Map.of(2, 1)));
		statistic.put("Бердск", new HashMap<Integer, Integer>(Map.of(3, 1)));
		statistic.put("Ангарск", new HashMap<Integer, Integer>(Map.of(3, 1)));
		statistic.put("Анжеро-Судженск", new HashMap<Integer, Integer>(Map.of(1, 1)));
		statistic.put("Балашиха", new HashMap<Integer, Integer>(Map.of(2, 1)));
	}
}
