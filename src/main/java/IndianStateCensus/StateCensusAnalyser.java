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

import IndianStateCensus.StateCensusAnalyserException.ExceptionType;

//Refactor 2 : OpenCSVbuilder class added to ensure Single Responsibilty principle is followed
public class StateCensusAnalyser {

	private static String CSV_CENSUS_FILE = "./IndianStateCensusData.csv";
	private static String CSV_CENSUS_CODE_FILE = "./IndianStateCode.csv";

	public int readData(String DATA_FILE) throws StateCensusAnalyserException {
		int noOfEntries = 0;
		if (!DATA_FILE.contains(".csv")) {
			throw new StateCensusAnalyserException(ExceptionType.INVALID_TYPE,
					"Invalid Class Type in the State Census CSV File!! \nInvalidTypeException thrown....");
		}
		try {
			Reader readFile = Files.newBufferedReader(Paths.get(DATA_FILE));
			Iterator<IndianStateCensus> userIterator = new OpenCSVBuilder().getCsvFileIterator(readFile,
					IndianStateCensus.class);
			BufferedReader br = new BufferedReader(new FileReader(DATA_FILE));
			int count = 0;
			String line = "";
			while ((line = br.readLine()) != null) {
				if (count == 0) {
					String[] headerArray = line.split(",");
					if (!(headerArray[0].equals("State") && headerArray[1].equals("Population")
							&& headerArray[2].equals("Area") && headerArray[3].equals("Density")))
						throw new StateCensusAnalyserException(ExceptionType.INVALID_HEADER,
								"Invalid headers in State Census CSV File!! \nInvalidHeaderException thrown....");
					count++;

				}

			}
			noOfEntries = this.getEntriesCount(userIterator);
			br.close();

		} catch (IOException e) {
			throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.INVALID_FILE_PATH,
					"Invalid State Census CSV File Location given!! \nInvalidFilePathException thrown....");

		} catch (RuntimeException e) {
			throw new StateCensusAnalyserException(ExceptionType.INVALID_DELIMITER,
					"Invalid Delimiter in the State Census CSV File!! \nInvalidDelimiterException thrown....");

		}
		return noOfEntries;
	}

	// method to read indian state census csv file
	public int readCodeData(String DATA_FILE) throws StateCensusAnalyserException {
		int entries = 0;
		if (!DATA_FILE.contains(".csv")) {
			throw new StateCensusAnalyserException(ExceptionType.INVALID_TYPE,
					"Invalid Class Type in the State Code CSV File!! \nInvalidTypeException thrown....");
		}
		try {
			Reader readFile = Files.newBufferedReader(Paths.get(DATA_FILE));
			Iterator<CSVStates> userIterator = new OpenCSVBuilder().getCsvFileIterator(readFile, CSVStates.class);
			BufferedReader br = new BufferedReader(new FileReader(DATA_FILE));
			int count = 0;
			String line = "";
			while ((line = br.readLine()) != null) {
				if (count == 0) {
					String[] headerArray = line.split(",");
					if (!(headerArray[0].equals("State") && headerArray[1].equals("TIN")
							&& headerArray[2].equals("StateCode")))
						throw new StateCensusAnalyserException(ExceptionType.INVALID_HEADER,
								"Invalid headers in State Code CSV File!! \nInvalidHeaderException thrown....");
					count++;

				}

			}
			entries = this.getEntriesCount(userIterator);
			br.close();

		} catch (IOException e) {

			throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.INVALID_FILE_PATH,
					"Invalid State Code CSV File Location given!! \nInvalidFilePathException thrown....");

		} catch (RuntimeException e) {
			throw new StateCensusAnalyserException(ExceptionType.INVALID_DELIMITER,
					"Invalid Delimiter in the State Code CSV File!! \nInvalidDelimiterException thrown....");

		}

		return entries;

	}

	// method to read indian state code from csv file

	private <E> int getEntriesCount(Iterator<E> userIterator) {
		int entries = 0;
		while (userIterator.hasNext()) {
			entries++;
			E count = userIterator.next();
		}
		return entries;
	}

}
