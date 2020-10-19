package IndianStateCensus;

import org.junit.Assert;
import org.junit.Test;

public class StateCensusAnalyserTest {

	private static String CSV_CENSUS_FILE = "./IndianStateCensusData.csv";

	@Test
	public void givenNumberOfEntriesInACSVFile_ShouldReturnExactlytheSameWhileReading() {
		StateCensusAnalyser obj = new StateCensusAnalyser();
		int entries = obj.readData();
		Assert.assertEquals(29, entries);
	}
	// this test case checks for total no. of entries in the csv file

}
