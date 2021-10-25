import lombok.experimental.UtilityClass;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UtilityClass
public class ArgumentsProcessor {

	public Map<String, String> processAllArguments(String[] args) {
		List<String> arguments = readArgumentsFrom(args);
		Map<String, String> mappedArguments = mapArguments(arguments);
		JdbcJarLoader.addToClasspath(mappedArguments.get("--jdbcPath"));
		return mappedArguments;
	}


	private Map<String, String> mapArguments(List<String> args) {
		Map<String, String> mappedAgs = new HashMap<>();
		for (String arg : args) {
			if (arg.startsWith("--")) {
				String[] params = arg.split("=");
				if (params.length != 2) {
					throw new IllegalArgumentException("Wrong settings for argument: " + arg);
				}
				String name = params[0];
				String value = params[1];
				mappedAgs.put(name, value);
			}
		}
		return mappedAgs;
	}


	public static List<String> readArgumentsFrom(String[] args) {
		if (args.length == 1) {
			return getArgumentsFromFile(args);
		}
		return getArgumentsFromCLI(args);
	}

	private static List<String> getArgumentsFromFile(String[] args) {
		try {
			File f = new File(args[0].split("=")[1]);
			return FileUtils.readLines(f, Charset.defaultCharset());
		} catch (IOException e) {
			e.printStackTrace();
		}
		throw new IllegalStateException("Incorrect argument. Must be: --filename=<path to file>");
	}

	private static List<String> getArgumentsFromCLI(String[] args) {
		try {
			FileUtils.writeLines(new File("config_" + Instant.now().getNano()), Arrays.asList(args));
			return Arrays.asList(args);
		} catch (IOException e) {
			e.printStackTrace();
		}
		throw new IllegalStateException("Problem with saving file");
	}

}
