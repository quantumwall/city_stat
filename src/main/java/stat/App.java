package stat;

import java.nio.file.Path;

import stat.parser.CityCsvParser;

public class App {

    public static void main(String[] args) {
	var path = Path.of(App.class.getClassLoader().getResource("address.csv").getFile());
	CityCsvParser.parseFrom(path);
    }
}
