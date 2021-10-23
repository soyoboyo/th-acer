import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ArgumentsProcessor {

	private String host;
	private String user;
	private String password;
	private String jdbcPath;
	private String tableName;
	private String columns;

	public ArgumentsProcessor(String[] args) {
		if (args.length == 1) {
			// use custom configuration
		}

		Map<String, String> mappedArguments = mapArguments(args);

		host = mappedArguments.get("--host");
		user = mappedArguments.get("--user");
		password = mappedArguments.get("--password");
		jdbcPath = mappedArguments.get("--jdbcPath");
		tableName = mappedArguments.get("--tableName");
		columns = mappedArguments.get("--columns");
		String pageSize = mappedArguments.get("--pageSize");
		String pageNumber = mappedArguments.get("--pageNumber");
	}

	private Map<String, String> mapArguments(String[] args) {
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

}
