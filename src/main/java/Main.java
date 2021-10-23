import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {


		ArgumentsProcessor arguments = new ArgumentsProcessor(args);
		String query = "SELECT " + arguments.getColumns() + " FROM " + arguments.getTableName() + ";";

		try (Connection conn = DriverManager.getConnection
				(arguments.getHost(),
						arguments.getUser(),
						arguments.getPassword());

		) {
			ResultSet rs = conn.createStatement().executeQuery(query);
			while (rs.next()) {
				System.out.print("ID: " + rs.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}
	}


}
