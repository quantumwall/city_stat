package stat.model;

import java.util.Map;
import java.util.Objects;

public class Details {

	private Map<String, Integer> duplicates;
	private Map<String, Map<Integer, Integer>> buildingsQuantity;

	public void setBuildindsQuantity(Map<String, Map<Integer, Integer>> buildindsQuantityByCity) {
		this.buildingsQuantity = buildindsQuantityByCity;
	}

	public Map<String, Map<Integer, Integer>> getBuildindsQuantity() {
		return buildingsQuantity;
	}

	public void setDuplicates(Map<String, Integer> duplicates) {
		if (Objects.nonNull(duplicates)) {
			this.duplicates = duplicates;
		}
	}

	public Map<String, Integer> getDuplicates() {
		return duplicates;
	}
}
