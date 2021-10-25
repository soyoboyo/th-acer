import java.util.Map;

public class Main {

	public static void main(String[] args) {

		Map<String, String> mappedArguments = ArgumentsProcessor.processAllArguments(args);
		UserArguments userArguments = new UserArguments(mappedArguments);
		DatabaseService dbService = new DatabaseService();
		dbService.getData(userArguments);
	}

}
