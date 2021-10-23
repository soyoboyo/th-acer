public class Main {

	public static void main(String[] args) {
		if (args.length == 1) {
			// use custom configuration
		}
		
		String host = args[0];
		String jdbcPath = args[1];
		String tableName = args[2];
		String columns = args[3];
		String pageSize, pageNumber;
		if (args.length == 6) {
			pageSize = args[4];
			pageNumber = args[5];
		}
	}

}
