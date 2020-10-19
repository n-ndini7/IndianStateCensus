package IndianStateCensus;

//Exception class and types
public class StateCensusAnalyserException extends Exception {

	enum ExceptionType {
		INVALID_FILE_PATH,INVALID_DELIMITER;
	}

	ExceptionType type;

	public StateCensusAnalyserException(ExceptionType type, String message) {
		super(message);
		this.type = type;
	}

}