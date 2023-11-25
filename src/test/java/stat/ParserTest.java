package stat;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public abstract class ParserTest {

	protected final Map<String, Integer> expectedDuplicates;
	protected final Map<String, Map<Integer, Integer>> expectedStatistic;
	protected final Path testDataFile;

	public ParserTest(Path testDataFile) {
		this.testDataFile = testDataFile;
		expectedDuplicates = new HashMap<String, Integer>();
		populateDuplicates(expectedDuplicates);
		expectedStatistic = new HashMap<String, Map<Integer, Integer>>();
		populateStatistic(expectedStatistic);
	}

	private void populateDuplicates(Map<String, Integer> duplicates) {
		duplicates.put("Барнаул;Дальняя улица;56;2", 2);
		duplicates.put("Братск;7-я Вишнёвая улица;49;5", 2);
		duplicates.put("Армавир;Евгения Родионова, улица;22;4", 4);
	}

	private void populateStatistic(Map<String, Map<Integer, Integer>> statistic) {
		statistic.put("Барнаул", new HashMap<Integer, Integer>(Map.of(2, 112)));
		statistic.put("Братск", new HashMap<Integer, Integer>(Map.of(5, 163, 4, 60)));
		statistic.put("Балаково", new HashMap<Integer, Integer>(Map.of(2, 67, 4, 122)));
		statistic.put("Азов", new HashMap<Integer, Integer>(Map.of(3, 156)));
		statistic.put("Видное", new HashMap<Integer, Integer>(Map.of(3, 185)));
		statistic.put("Батайск", new HashMap<Integer, Integer>(Map.of(4, 133)));
		statistic.put("Великий Новгород", new HashMap<Integer, Integer>(Map.of(1, 44)));
		statistic.put("Абакан", new HashMap<Integer, Integer>(Map.of(3, 241, 2, 177)));
		statistic.put("Бугульма", new HashMap<Integer, Integer>(Map.of(2, 92, 1, 91, 4, 13)));
		statistic.put("Ачинск", new HashMap<Integer, Integer>(Map.of(4, 39, 3, 154)));
		statistic.put("Балашов", new HashMap<Integer, Integer>(Map.of(1, 191)));
		statistic.put("Бийск", new HashMap<Integer, Integer>(Map.of(3, 14, 5, 128)));
		statistic.put("Армавир", new HashMap<Integer, Integer>(Map.of(1, 69, 4, 88)));
		statistic.put("Астрахань", new HashMap<Integer, Integer>(Map.of(5, 152)));
		statistic.put("Владивосток", new HashMap<Integer, Integer>(Map.of(3, 196, 1, 105)));
		statistic.put("Белово", new HashMap<Integer, Integer>(Map.of(1, 116)));
		statistic.put("Архангельск", new HashMap<Integer, Integer>(Map.of(3, 85)));
		statistic.put("Белебей", new HashMap<Integer, Integer>(Map.of(1, 93)));
		statistic.put("Благовещенск", new HashMap<Integer, Integer>(Map.of(2, 31)));
		statistic.put("Буйнакск", new HashMap<Integer, Integer>(Map.of(2, 142)));
		statistic.put("Бердск", new HashMap<Integer, Integer>(Map.of(3, 53)));
		statistic.put("Ангарск", new HashMap<Integer, Integer>(Map.of(3, 197)));
		statistic.put("Анжеро-Судженск", new HashMap<Integer, Integer>(Map.of(1, 42)));
		statistic.put("Балашиха", new HashMap<Integer, Integer>(Map.of(2, 113)));
	}
}
