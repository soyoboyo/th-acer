import lombok.Getter;

import java.util.Map;
import java.util.Objects;

@Getter
public class UserArguments {


	private final String host;
	private final String jdbcPath;
	private final String user;
	private final String password;
	private final String tableName;
	private final String columns;
	private String pageSize;
	private String pageNumber;


	public UserArguments(Map<String, String> mappedArguments) {
		host = mappedArguments.get("--host");
		user = mappedArguments.get("--user");
		password = mappedArguments.get("--password");
		jdbcPath = mappedArguments.get("--jdbcPath");
		tableName = mappedArguments.get("--tableName");
		columns = mappedArguments.get("--columns");
		pageSize = mappedArguments.get("--pageSize");
		pageNumber = mappedArguments.get("--pageNumber");
	}

	public boolean hasPagnation() {
		return !(Objects.isNull(pageSize) || Objects.isNull(pageNumber));
	}

}
