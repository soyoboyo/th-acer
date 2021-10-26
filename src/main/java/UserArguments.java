import lombok.Getter;

import java.util.Map;

@Getter
public class UserArguments {


	private final String host;
	private final String jdbcPath;
	private final String user;
	private final String password;
	private final String tableName;
	private final String columns;
	private Integer pageSize;
	private Integer pageNumber;


	public UserArguments(Map<String, String> mappedArguments) {
		host = mappedArguments.get("--host");
		user = mappedArguments.get("--user");
		password = mappedArguments.get("--password");
		jdbcPath = mappedArguments.get("--jdbcPath");
		tableName = mappedArguments.get("--tableName");
		columns = mappedArguments.get("--columns");
		if (mappedArguments.get("--pageSize") != null && mappedArguments.get("--pageNumber") != null) {
			pageSize = Integer.parseInt(mappedArguments.get("--pageSize"));
			pageNumber = Integer.parseInt(mappedArguments.get("--pageNumber"));
		}

	}

	public boolean hasPagination() {
		return pageSize != null && pageNumber != null;
	}

}
