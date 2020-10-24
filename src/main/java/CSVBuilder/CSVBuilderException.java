package CSVBuilder;

public class CSVBuilderException extends Exception {

	public enum ExceptionType {
		UNABLE_TO_PARSE;
	}

	ExceptionType type;

	public CSVBuilderException(String message, ExceptionType Type) {
		super(message);
		this.type = type;
	}
}
