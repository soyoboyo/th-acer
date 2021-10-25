import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseService {

	public void getData(UserArguments args) {
		String query = "SELECT " + args.getColumns() + " FROM " + args.getTableName() + ";";

		try (Connection conn = DriverManager.getConnection(args.getHost(), args.getUser(), args.getPassword())) {
			ResultSet rs = conn.createStatement().executeQuery(query);
			while (rs.next()) {
				System.out.print("ID: " + rs.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("bye");
		}
	}
}
