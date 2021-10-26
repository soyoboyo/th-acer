import java.sql.*;

public class DatabaseService {

	public void getData(UserArguments args) {
		String[] columns = args.getColumns().split(",");

		try (Connection conn = DriverManager.getConnection(args.getHost(), args.getUser(), args.getPassword())) {
			PreparedStatement preparedStatement;
			preparedStatement = getPreparedStatement(args, conn);

			try (ResultSet rs = preparedStatement.executeQuery()) {
				while (rs.next()) {
					printRow(columns, rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void printRow(String[] columns, ResultSet rs) throws SQLException {
		StringBuilder sb = new StringBuilder();
		for (String column : columns) {
			sb.append(rs.getString(column));
		}
		System.out.println(sb);
	}

	private PreparedStatement getPreparedStatement(UserArguments args, Connection conn) {
		if (args.hasPagination()) {
			return preparePaginationQuery(conn, args);
		} else {
			return prepareBasicQuery(conn, args);
		}
	}

	private PreparedStatement prepareBasicQuery(Connection conn, UserArguments userArguments) {
		String query = "SELECT ? FROM ?;";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, userArguments.getColumns());
			preparedStatement.setString(2, userArguments.getTableName());
			return preparedStatement;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new IllegalStateException("Problem creating query: " + query);
	}

	private PreparedStatement preparePaginationQuery(Connection conn, UserArguments userArguments) {
		int startOffset = userArguments.getPageSize() * userArguments.getPageNumber();
		String query = "SELECT ? FROM ? ORDER BY ? OFFSET " + startOffset + " ROWS FETCH NEXT ? ROWS ONLY;";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, userArguments.getColumns());
			preparedStatement.setString(2, userArguments.getTableName());
			preparedStatement.setString(3, userArguments.getColumns().split(",")[0]);
			preparedStatement.setString(4, String.valueOf(userArguments.getPageSize()));
			return preparedStatement;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new IllegalStateException("Problem creating query: " + query);
	}

}
