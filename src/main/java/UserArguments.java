import lombok.Getter;

import java.util.Map;

@Getter
public class UserArguments {


	private String host;
	private String user;
	private String password;
	private String jdbcPath;
	private String tableName;
	private String columns;

	public UserArguments(Map<String, String> mappedArguments) {
		host = mappedArguments.get("--host");
		user = mappedArguments.get("--user");
		password = mappedArguments.get("--password");
		jdbcPath = mappedArguments.get("--jdbcPath");
		tableName = mappedArguments.get("--tableName");
		columns = mappedArguments.get("--columns");
		String pageSize = mappedArguments.get("--pageSize");
		String pageNumber = mappedArguments.get("--pageNumber");
	}

}
