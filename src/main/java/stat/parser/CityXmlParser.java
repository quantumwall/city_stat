package stat.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

import stat.model.Details;
import stat.util.MapHelper;

public class CityXmlParser implements Parser {

	private static final String CITY_ATTRIBUTE_NAME = "city";
	private static final String STREET_ATTRIBUTE_NAME = "street";
	private static final String HOUSE_ATTRIBUTE_NAME = "house";
	private static final String FLOOR_ATTRIBUTE_NAME = "floor";
	private static final String DATA_TAG_NAME = "item";

	@Override
	public Details parse(Path path) {
		var xmlInputFactory = XMLInputFactory.newInstance();
		try (var inputStream = Files.newBufferedReader(path)) {
			var details = new Details();
			var duplicates = new HashMap<String, Integer>();
			var buildingsQuantity = new HashMap<String, Map<Integer, Integer>>();
			var eventReader = xmlInputFactory.createXMLEventReader(inputStream);
			while (eventReader.hasNext()) {
				var event = eventReader.nextEvent();
				if (event.isStartElement()) {
					var element = event.asStartElement();
					if (element.getName().getLocalPart().equals(DATA_TAG_NAME)) {
						var city = element.getAttributeByName(new QName(CITY_ATTRIBUTE_NAME)).getValue();
						var street = element.getAttributeByName(new QName(STREET_ATTRIBUTE_NAME)).getValue();
						var houseCount = element.getAttributeByName(new QName(HOUSE_ATTRIBUTE_NAME)).getValue();
						var floor = element.getAttributeByName(new QName(FLOOR_ATTRIBUTE_NAME)).getValue();
						var row = String.join(";", city, street, houseCount, floor);
						duplicates.merge(row, 1, Integer::sum);
						MapHelper.insertBuildings(buildingsQuantity, row);
					}
				}
			}
			MapHelper.deleteUniqueElements(duplicates);
			details.setDuplicates(duplicates);
			details.setBuildindsQuantity(buildingsQuantity);
			return details;
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (XMLStreamException e) {
			throw new RuntimeException(e);
		}

	}

//	private void deleteUniqueElements(Map<String, Integer> map) {
//		var keys = Set.copyOf(map.keySet());
//		for (String key : keys) {
//			map.remove(key, 1);
//		}
//	}
//
//	private void insertBuildings(Map<String, Map<Integer, Integer>> map, String row) {
//		var rowElements = row.split(";");
//		var city = rowElements[0];
//		var houseCount = Integer.parseInt(rowElements[2]);
//		var floors = Integer.parseInt(rowElements[3]);
//		if (map.putIfAbsent(city, new HashMap<>(Map.of(floors, houseCount))) != null) {
//			map.get(city).merge(floors, houseCount, Integer::sum);
//		}
//	}

}
