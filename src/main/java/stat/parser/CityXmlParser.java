package stat.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;

import stat.model.Details;
import stat.util.MapHelper;

public class CityXmlParser implements Parser {

	private static final String DATA_TAG_NAME = "item";
	private static final String CITY_ATTRIBUTE_NAME = "city";
	private static final String STREET_ATTRIBUTE_NAME = "street";
	private static final String HOUSE_ATTRIBUTE_NAME = "house";
	private static final String FLOOR_ATTRIBUTE_NAME = "floor";
	private static final Logger LOG = Logger.getLogger(CityXmlParser.class.getName());

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
						var row = getRow(element);
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
			LOG.severe("An error ocurred reading file %s".formatted(path.toString()));
			throw new RuntimeException(e);
		} catch (XMLStreamException e) {
			LOG.severe("An error while parsing file %s".formatted(path.toString()));
			throw new RuntimeException(e);
		}
	}

	private String getRow(StartElement element) {
		var city = element.getAttributeByName(new QName(CITY_ATTRIBUTE_NAME)).getValue();
		var street = element.getAttributeByName(new QName(STREET_ATTRIBUTE_NAME)).getValue();
		var houseCount = element.getAttributeByName(new QName(HOUSE_ATTRIBUTE_NAME)).getValue();
		var floor = element.getAttributeByName(new QName(FLOOR_ATTRIBUTE_NAME)).getValue();
		return String.join(";", city, street, houseCount, floor);
	}
}
