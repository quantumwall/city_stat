package stat.parser;

import java.nio.file.Path;
import stat.model.Details;

public interface Parser {

	Details parse(Path path);
}
