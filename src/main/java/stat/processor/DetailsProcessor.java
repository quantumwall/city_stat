package stat.processor;

import java.util.Map;
import java.util.Objects;

import stat.model.Details;

public class DetailsProcessor {

	private final Details details;

	public DetailsProcessor(Details details) {
		Objects.requireNonNull(details, "Details object must be non null");
		this.details = details;
	}

	public String getDuplicatesString() {
		var stringBuilder = new StringBuilder();
		stringBuilder.append("%-50s%s\n".formatted("Строка", "Повторений"));
		for (Map.Entry<String, Integer> pair : details.getDuplicates().entrySet()) {
			stringBuilder.append("%-50s%d\n".formatted(pair.getKey(), pair.getValue()));
		}
		return stringBuilder.toString();
	}

	public String getBuldingsStatString() {
		var stringBuilder = new StringBuilder();
		stringBuilder.append("%-20s%s%20s\n".formatted("Город", "Этажей в здании", "Количество зданий"));
		for (Map.Entry<String, Map<Integer, Integer>> pair : details.getBuildindsQuantity().entrySet()) {
			for (Map.Entry<Integer, Integer> houses : pair.getValue().entrySet()) {
				stringBuilder.append("%-20s%d%30d\n".formatted(pair.getKey(), houses.getKey(), houses.getValue()));
			}
			stringBuilder.append("\n");
		}

		return stringBuilder.toString();
	}

}
