package CSVBuilder;

public class CSVBuilderFactory<T> {
	@SuppressWarnings("rawtypes")
	public static ICSVBuilder createCSVBuilder() throws CSVBuilderException {
		return new OpenCSVBuilder();
	}

}
