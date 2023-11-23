package stat.parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

public class CityCsvParser {

    public static void parseFrom(Path path) {
	try (var reader = new FileReader(path.toFile())) {
	    CSVParser parser = CSVFormat.Builder.create().setDelimiter(';').setSkipHeaderRecord(true)
		    .setIgnoreEmptyLines(true).setHeader("city", "street", "house", "floor").setQuote('"').setTrim(true)
		    .build().parse(reader);
	    parser.forEach(r -> System.out.println(r.get("street")));
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }
}
