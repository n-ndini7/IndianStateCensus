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
public class StateCensusAnalyser {

	private static String CSV_CENSUS_FILE = "./IndianStateCensusData.csv";
	private static String CSV_CENSUS_CODE_FILE = "./IndianStateCode.csv";

	public int readData(String DATA_FILE) throws StateCensusAnalyserException, CSVBuilderException {
		int noOfEntries = 0;
		int cd = 0;
		if (!DATA_FILE.contains(".csv")) {
			throw new StateCensusAnalyserException(ExceptionType.INVALID_TYPE,
					"Invalid Class Type in the State Census File!! \nInvalidTypeException thrown....");
		}
		try (Reader readFile = Files.newBufferedReader(Paths.get(DATA_FILE))) {
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			Iterator<IndianStateCensus> userIterator = csvBuilder.getCSVFileIterator(readFile, IndianStateCensus.class);
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
				noOfEntries++;
			}
		} catch (IOException e) {
			throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.INVALID_FILE_PATH,
					"Invalid State Census File Location given!! \nInvalidFilePathException thrown....");
		} catch (RuntimeException e) {
			throw new StateCensusAnalyserException(ExceptionType.INVALID_DELIMITER,
					"Invalid Delimiter in the State Code File!! \nInvalidDelimiterException thrown....");

		}
		return noOfEntries;
	}

	// method to read indian state census csv file
	public int readCodeData(String DATA_FILE) throws StateCensusAnalyserException, CSVBuilderException {
		int noOfEntries = 0;
		int cd = 0;
		if (!DATA_FILE.contains(".csv")) {
			throw new StateCensusAnalyserException(ExceptionType.INVALID_TYPE,
					"Invalid Class Type in the State Code File!! \nInvalidTypeException thrown....");
		}
		try (Reader readFile = Files.newBufferedReader(Paths.get(DATA_FILE))) {
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			Iterator<CSVStates> userIterator = csvBuilder.getCSVFileIterator(readFile, CSVStates.class);
			BufferedReader br = new BufferedReader(new FileReader(DATA_FILE));
			int count = 0;
			String line = "";
			while ((line = br.readLine()) != null) {
				if (!line.contains(","))

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
				noOfEntries++;
			}
		} catch (IOException e) {
			throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.INVALID_FILE_PATH,
					"Invalid State Code File Location given!! \nInvalidFilePathException thrown....");
		} catch (RuntimeException e) {
			throw new StateCensusAnalyserException(ExceptionType.INVALID_DELIMITER,
					"Invalid Delimiter in the State Code File!! \nInvalidDelimiterException thrown....");

		}
		return noOfEntries;
	}

	// method to read indian state code from csv file
	public static void main(String[] args) throws StateCensusAnalyserException, CSVBuilderException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to Indian State Census Analyser Program!");
		System.out.println("================================================");
		System.out.println("1.Read Indian State Census Data \n2.Exit");
		System.out.println("Enter your choice:");
		int choice = Integer.parseInt(sc.nextLine());
		StateCensusAnalyser object = new StateCensusAnalyser();
		switch (choice) {
		case 1:
			System.out.println("Press '8' to read Indian State Census Data \nPress '9' to read Indian State Code Data");
			int select = Integer.parseInt(sc.nextLine());
			if (select == 8) {
				try {
					object.readData(CSV_CENSUS_FILE);
				} catch (StateCensusAnalyserException e) {
					e.printStackTrace();
				}
			} else if (select == 9) {
				try {
					object.readCodeData(CSV_CENSUS_CODE_FILE);
				} catch (StateCensusAnalyserException e) {
					e.printStackTrace();
				}
			}
			break;
		case 2:
			System.out.println("Thanks for using application!");
			System.exit(0);
		}
	}
}
