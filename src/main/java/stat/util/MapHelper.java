package stat.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapHelper {

	private static final String DELIMITER = ";";

	private MapHelper() {
	}

	public static void insertBuildings(Map<String, Map<Integer, Integer>> map, String row) {
		var rowElements = row.split(DELIMITER);
		var city = rowElements[0];
		var houseCount = Integer.parseInt(rowElements[2]);
		var floors = Integer.parseInt(rowElements[3]);
		if (map.putIfAbsent(city, new HashMap<>(Map.of(floors, houseCount))) != null) {
			map.get(city).merge(floors, houseCount, Integer::sum);
		}
	}

	public static void deleteUniqueElements(Map<String, Integer> map) {
		var keys = Set.copyOf(map.keySet());
		for (String key : keys) {
			map.remove(key, 1);
		}
	}
}
