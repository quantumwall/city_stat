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
		stringBuilder.append("Строка\t\tПовторений\n");
		for (Map.Entry<String, Integer> pair : details.getDuplicates().entrySet()) {
			stringBuilder.append("%s\t%d\n".formatted(pair.getKey(), pair.getValue()));
		}
		return stringBuilder.toString();
	}

	public String getBuldingsStatString() {
		var stringBuilder = new StringBuilder();
		stringBuilder.append("Город\t\tЭтажей в здании\t\tКоличество зданий\n");
		for (Map.Entry<String, Map<Integer, Integer>> pair : details.getBuildindsQuantity().entrySet()) {
			for (Map.Entry<Integer, Integer> houses : pair.getValue().entrySet()) {
				stringBuilder.append("%s\t%d\t%d\n".formatted(pair.getKey(), houses.getKey(), houses.getValue()));
			}
		}
		return stringBuilder.toString();
	}

}
