package IndianStateCensus;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

public class StateCensusAnalyserTest {

	private static String CSV_CENSUS_FILE = "./IndianStateCensusData.csv";
	private static String CSV_FILE = "./IndianStateCensusDataExceptionDemo.csv";
	private static String CSV_CENSUS_FILE_INVALID_DELIMITER = "./IndianStateCensusInvalidDelimiter.csv";

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

}
