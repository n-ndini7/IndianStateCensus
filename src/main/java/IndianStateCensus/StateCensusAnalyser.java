package IndianStateCensus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Scanner;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import IndianStateCensus.StateCensusAnalyserException.ExceptionType;

//Refactor 1A : refactor the previous UCs as DRY was violated to extract data from Open CSV
//Refactor 1B : getEntriesCount method included to get the count 
public class StateCensusAnalyser {

	private static String CSV_CENSUS_FILE = "./IndianStateCensusData.csv";
	private static String CSV_CENSUS_CODE_FILE = "./IndianStateCode.csv";

	public int readData(String DATA_FILE) throws StateCensusAnalyserException, CSVBuilderException {

		int cd = 0;
		if (!DATA_FILE.contains(".csv")) {
			throw new StateCensusAnalyserException(ExceptionType.INVALID_TYPE,
					"Invalid Class Type in the State Census File!! \nInvalidTypeException thrown....");
		}
		try (Reader readFile = Files.newBufferedReader(Paths.get(DATA_FILE))) {
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			Iterator<IndianStateCensus> userIterator = csvBuilder.getCSVFileIterator(readFile, IndianStateCensus.class);
			int noOfEntries = this.getEntriesCount(userIterator);
			BufferedReader br = new BufferedReader(new FileReader(DATA_FILE));
			int count = 0;
			String line = "";
			while ((line = br.readLine()) != null) {
				if (line.contains(","))
					cd++;
				if (count == 0) {
					String[] headerArray = line.split(",");
					if (!(headerArray[0].equals("State") && headerArray[1].equals("Population")
							&& headerArray[2].equals("Area") && headerArray[3].equals("Density")))
						throw new StateCensusAnalyserException(ExceptionType.INVALID_HEADER,
								"Invalid headers in State Census File!! \nInvalidHeaderException thrown....");
					count++;

				}

			}
			br.close();
			while (userIterator.hasNext()) {
				IndianStateCensus csvuser = userIterator.next();
				System.out.println(csvuser);
				System.out.println(
						"===================================================================================================");
			}
			return noOfEntries;

		} catch (IOException e) {
			throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.INVALID_FILE_PATH,
					"Invalid State Census File Location given!! \nInvalidFilePathException thrown....");
		} catch (RuntimeException e) {
			throw new StateCensusAnalyserException(ExceptionType.INVALID_DELIMITER,
					"Invalid Delimiter in the State Census File!! \nInvalidDelimiterException thrown....");

		}

	}

	// method to read indian state census csv file

	public int readCodeData(String DATA_FILE) throws StateCensusAnalyserException, CSVBuilderException {
		if (!DATA_FILE.contains(".csv")) {
			throw new StateCensusAnalyserException(ExceptionType.INVALID_TYPE,
					"Invalid Class Type in the State Code File!! \nInvalidTypeException thrown....");
		}
		try (Reader readFile = Files.newBufferedReader(Paths.get(DATA_FILE))) {
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			Iterator<CSVStates> userIterator = csvBuilder.getCSVFileIterator(readFile, CSVStates.class);
			int noOfEntries = this.getEntriesCount(userIterator);
			BufferedReader br = new BufferedReader(new FileReader(DATA_FILE));
			int count = 0;
			String line = "";
			while ((line = br.readLine()) != null) {
				if (count == 0) {
					String[] headerArray = line.split(",");
					if (!(headerArray[0].equals("State") && headerArray[1].equals("TIN")
							&& headerArray[2].equals("StateCode")))
						throw new StateCensusAnalyserException(ExceptionType.INVALID_HEADER,
								"Invalid headers in State Code File!! \nInvalidHeaderException thrown....");
					count++;

				}

			}
			br.close();
			while (userIterator.hasNext()) {
				CSVStates csvuser = userIterator.next();
				System.out.println(csvuser);
				System.out.println(
						"===================================================================================================");
			}
			return noOfEntries;

		} catch (IOException e) {
			throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.INVALID_FILE_PATH,
					"Invalid State Code File Location given!! \nInvalidFilePathException thrown....");
		} catch (RuntimeException e) {
			throw new StateCensusAnalyserException(ExceptionType.INVALID_DELIMITER,
					"Invalid Delimiter in the State Code File!! \nInvalidDelimiterException thrown....");

		}

	}

	// method to read indian state code from csv file

	private <E> int getEntriesCount(Iterator<E> userIterator) {
		int entries = 0;
		while (userIterator.hasNext()) {
			entries++;
			E move = userIterator.next();
		}
		return entries;
	}

	// gets the count of entries in a csv file

}
