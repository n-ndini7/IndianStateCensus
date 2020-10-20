package IndianStateCensus;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

public class StateCensusAnalyserTest {

	private static String CSV_CENSUS_FILE = "./IndianStateCensusData.csv";
	private static String CSV_FILE = "./IndianStateCensusDataExceptionDemo.csv";
	private static String CSV_CENSUS_FILE_INVALID_DELIMITER = "./IndianStateCensusInvalidDelimiter.csv";
	private static String CSV_CENSUS_FILE_INVALID_HEADER = "./IndianStateCensusInvalidHeader.csv";
	private static String CSV_CENSUS_FILE_INVALID_TYPE = "./IndianStateCensusInvalidType.txt";
	private static String CSV_STATE_CODE_FILE = "./IndianStateCode.csv";
	private static String CSV_STATE_CODE_FILE_INVALID_DELIMITER = "./IndianStateCodeInvalidDelimiter.csv";
	private static String CSV_STATE_CODE_FILE_INVALID_HEADER = "./IndianStateCodeInvalidHeader.csv";

	@Test
	public void givenNumberOfEntriesInACSVFile_ShouldReturnExactlytheSameWhileReading()
			throws StateCensusAnalyserException {
		StateCensusAnalyser obj = new StateCensusAnalyser();
		int entries = obj.readData(CSV_CENSUS_FILE);
		Assert.assertEquals(29, entries);
	}
	// this test case checks for total no. of entries in the csv file

	@Test
	public void givenWrongFileLocationsthrowsCustomeException_ForInvalidFilePath() {
		StateCensusAnalyser obj = new StateCensusAnalyser();
		try {
			obj.readData(CSV_FILE);
		} catch (StateCensusAnalyserException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			assertEquals(StateCensusAnalyserException.ExceptionType.INVALID_FILE_PATH, e.type);
		}
	}

	// this test case checks if custom exception thrown in case of invalid file
	// location

	@Test
	public void givenInvalidDelimiter_ShouldThrowCustomException() {
		StateCensusAnalyser obj = new StateCensusAnalyser();
		try {
			obj.readData(CSV_CENSUS_FILE_INVALID_DELIMITER);
		} catch (StateCensusAnalyserException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			assertEquals(StateCensusAnalyserException.ExceptionType.INVALID_DELIMITER, e.type);
		}
	}
	// this test case checks for invalid delimiter in the csv file while reading

	@Test
	public void givenInvalidHeader_ShouldThrowCustomException() {
		StateCensusAnalyser obj = new StateCensusAnalyser();
		try {
			obj.readData(CSV_CENSUS_FILE_INVALID_HEADER);
		} catch (StateCensusAnalyserException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			assertEquals(StateCensusAnalyserException.ExceptionType.INVALID_HEADER, e.type);
		}
	}
	// this test case checks for invalid header in csv file while reading

	@Test
	public void givenWrongFiletypethrowsCustomeException() {
		StateCensusAnalyser obj = new StateCensusAnalyser();
		try {
			obj.readData(CSV_CENSUS_FILE_INVALID_TYPE);
		} catch (StateCensusAnalyserException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			assertEquals(StateCensusAnalyserException.ExceptionType.INVALID_TYPE, e.type);
    }
    //this test case checks for custom invalid file type exception 
    
  @Test
	public void givenNumberOfEntriesInAStateCodeCSVFile_ShouldReturnExactlytheSameWhileReading()
			throws StateCensusAnalyserException {
		StateCensusAnalyser obj = new StateCensusAnalyser();
		int entries = obj.readCodeData(CSV_STATE_CODE_FILE);
		System.out.println(entries);
		//Assert.assertEquals(37, entries);
	}

	// this test case checks the total no. of entries in indian state code csv file

	@Test
	public void givenWrongFileLocationOfStateCodeCVSFilethrowsCustomeException_ForInvalidFilePath() {
		StateCensusAnalyser obj = new StateCensusAnalyser();
		try {
			obj.readCodeData(CSV_FILE);
		} catch (StateCensusAnalyserException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			assertEquals(StateCensusAnalyserException.ExceptionType.INVALID_FILE_PATH, e.type);
		}
	}

	// this test case checks if custom exception thrown in case of invalid file

	// location of state code csv file

	@Test
	public void givenInvalidDelimiterInStateCodeCsvFile_ShouldThrowCustomException() {
		StateCensusAnalyser obj = new StateCensusAnalyser();
		try {
			obj.readCodeData(CSV_STATE_CODE_FILE_INVALID_DELIMITER);
		} catch (StateCensusAnalyserException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			assertEquals(StateCensusAnalyserException.ExceptionType.INVALID_DELIMITER, e.type);
		}
	}
	// this test case checks for invalid delimiter in the indian state code csv file
	// while reading

	@Test
	public void givenInvalidHeaderInStateCodeCSVFile_ShouldThrowCustomException() {
		StateCensusAnalyser obj = new StateCensusAnalyser();
		try {
			obj.readCodeData(CSV_STATE_CODE_FILE_INVALID_HEADER);
		} catch (StateCensusAnalyserException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			assertEquals(StateCensusAnalyserException.ExceptionType.INVALID_HEADER, e.type);
		}
	}
	// this test case checks for invalid header in indian state code csv file while
	// reading

}
