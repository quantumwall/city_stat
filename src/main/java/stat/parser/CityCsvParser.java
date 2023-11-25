package stat.parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import stat.model.Details;
import stat.util.MapHelper;

public class CityCsvParser implements Parser {

	private static final String CITY_COLUMN_NAME = "city";
	private static final String STREET_COLUMN_NAME = "street";
	private static final String HOUSE_COLUMN_NAME = "house";
	private static final String FLOOR_COLUMN_NAME = "floor";
	private static final char DELIMITER = ';';
	private static final char QUOTE_TYPE = '"';

	@Override
	public Details parse(Path path) {
		try (var reader = new FileReader(path.toFile())) {
			var details = new Details();
			CSVParser parser = CSVFormat.Builder.create().setDelimiter(DELIMITER).setSkipHeaderRecord(true)
					.setIgnoreEmptyLines(true)
					.setHeader(CITY_COLUMN_NAME, STREET_COLUMN_NAME, HOUSE_COLUMN_NAME, FLOOR_COLUMN_NAME)
					.setQuote(QUOTE_TYPE).setTrim(true).build().parse(reader);
			var duplicates = new HashMap<String, Integer>();
			var buildingsQuantity = new HashMap<String, Map<Integer, Integer>>();
			parser.forEach(csvRec -> {
				var row = getRow(csvRec);
				duplicates.merge(row, 1, Integer::sum);
				MapHelper.insertBuildings(buildingsQuantity, row);
			});
			MapHelper.deleteUniqueElements(duplicates);
			details.setDuplicates(duplicates);
			details.setBuildindsQuantity(buildingsQuantity);
			return details;
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private String getRow(CSVRecord csvRec) {
		return csvRec.stream().collect(Collectors.joining(Character.toString(DELIMITER)));
	}

//	private void deleteUniqueElements(Map<String, Integer> map) {
//		var keys = Set.copyOf(map.keySet());
//		for (String key : keys) {
//			map.remove(key, 1);
//		}
//	}

//	private void insertBuildings(CSVRecord csvRec, Map<String, Map<Integer, Integer>> map) {
//		var city = csvRec.get(CITY_COLUMN_NAME);
//		var houseCount = Integer.parseInt(csvRec.get(HOUSE_COLUMN_NAME));
//		var floors = Integer.parseInt(csvRec.get(FLOOR_COLUMN_NAME));
//		if (map.putIfAbsent(city, new HashMap<>(Map.of(floors, houseCount))) != null) {
//			map.get(city).merge(floors, houseCount, Integer::sum);
//		}
//	}
}
