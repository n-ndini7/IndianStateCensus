package IndianStateCensus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import CSVBuilder.CSVBuilderException;
import CSVBuilder.CSVBuilderFactory;
import CSVBuilder.ICSVBuilder;
import IndianStateCensus.StateCensusAnalyserException.ExceptionType;

//Refactor 5 : getCSVFileList() method included to take in list of census data 
//UC3 - sort state census csv file data alphabetically and return it as json file
//UC4-  sort state code csv file data alphabetically and return it as json file
//UC5- sort data of state census csv file in order of most to least populous
//UC6- sort data of state census file in order of most to least population density
public class StateCensusAnalyser {

	private static String CSV_CENSUS_FILE = "./IndianStateCensusData.csv";
	private static String CSV_CENSUS_CODE_FILE = "./IndianStateCode.csv";

	List<IndianStateCensus> censusCSVList;
	List<CSVStates> codeCSVList;

	public StateCensusAnalyser() {
		this.censusCSVList = new ArrayList<IndianStateCensus>();
		this.codeCSVList = new ArrayList<CSVStates>();
	}

	public int readData(String DATA_FILE) throws StateCensusAnalyserException {
		int noOfEntries = 0;
		if (!DATA_FILE.contains(".csv")) {
			throw new StateCensusAnalyserException(ExceptionType.INVALID_TYPE,
					"Invalid Class Type in the State Census CSV File!! \nInvalidTypeException thrown....");
		}
		try (Reader reader = Files.newBufferedReader(Paths.get(DATA_FILE));) {
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			List<IndianStateCensus> censusCSVList = null;
			censusCSVList = csvBuilder.getCsvFileList(reader, IndianStateCensus.class);
			noOfEntries = censusCSVList.size();
			BufferedReader br = new BufferedReader(new FileReader(CSV_CENSUS_FILE));
			String line = "";
			int ctr = 0;
			while ((line = br.readLine()) != null) {
				if (ctr == 0) {
					String[] headerArray = line.split(",");
					if (!(headerArray[0].equals("State") && headerArray[1].equals("Population")
							&& headerArray[2].equals("Area") && headerArray[3].equals("Density")))
						throw new StateCensusAnalyserException(ExceptionType.INVALID_HEADER,
								"Invalid headers in the CSV File!! \nInvalidHeaderException thrown...");
					ctr++;
				}
			}
			br.close();
		} catch (IOException e) {
			throw new StateCensusAnalyserException(ExceptionType.INVALID_FILE_PATH,
					"Invalid file location given!! \nInvalidFilePAthException thrown...");
		} catch (CSVBuilderException e1) {
			System.out.println("Unable to parse!! \nCSVBuilderException thrown....");
		} catch (RuntimeException e) {
			throw new StateCensusAnalyserException(ExceptionType.INVALID_DELIMITER,
					"Invalid Delimiter in the State Code CSV File!! \nInvalidDelimiterException thrown....");

		} catch (CsvException e) {

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
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			codeCSVList = csvBuilder.getCsvFileList(readFile, CSVStates.class);
			entries = codeCSVList.size();
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
			br.close();

		} catch (IOException e) {

			throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.INVALID_FILE_PATH,
					"Invalid State Code CSV File Location given!! \nInvalidFilePathException thrown....");

		} catch (RuntimeException e) {
			throw new StateCensusAnalyserException(ExceptionType.INVALID_DELIMITER,
					"Invalid Delimiter in the State Code CSV File!! \nInvalidDelimiterException thrown....");

		} catch (CSVBuilderException e) {
			System.out.println("Unable to parse!! \nCSVBuilderException thrown....");
		} catch (CsvException e) {
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

	public String sortedCensusData() throws CSVBuilderException, IOException {
		try {
			Reader readFile = Files.newBufferedReader(Paths.get(CSV_CENSUS_FILE));
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			this.censusCSVList = csvBuilder.getCsvFileList(readFile, IndianStateCensus.class);
		} catch (IOException e) {
			throw new CSVBuilderException("Unable to parse!! \nCSVBuilderException thrown....",
					CSVBuilderException.ExceptionType.UNABLE_TO_PARSE);
		} catch (CSVBuilderException e) {
			System.out.println("Unable to parse!! \nCSVBuilderException thrown....");
		} catch (CsvException e) {
		}
		Comparator<IndianStateCensus> comp = Comparator.comparing(census -> census.stateName);
		this.sort(comp);
		String sortedDataJson = new Gson().toJson(censusCSVList);
		return sortedDataJson;
	}

	public String sortedStateCodeData() throws CSVBuilderException {
		try {
			Reader readFile = Files.newBufferedReader(Paths.get(CSV_CENSUS_CODE_FILE));
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			this.codeCSVList = csvBuilder.getCsvFileList(readFile, CSVStates.class);
		} catch (IOException e) {
			throw new CSVBuilderException("Unable to parse!! \nCSVBuilderException thrown....",
					CSVBuilderException.ExceptionType.UNABLE_TO_PARSE);
		} catch (CSVBuilderException e) {
			System.out.println("Unable to parse!! \nCSVBuilderException thrown....");
		} catch (CsvException e) {
		}
		Collections.sort(codeCSVList, Comparator.comparing(code -> code.code));
		String sortedDataJson = new Gson().toJson(codeCSVList);
		return sortedDataJson;
	}

	public void sort(Comparator<IndianStateCensus> comp) {
		for (int i = 0; i < censusCSVList.size(); i++) {
			for (int j = 0; j < censusCSVList.size() - 1 - 1; j++) {
				IndianStateCensus c1 = censusCSVList.get(j);
				IndianStateCensus c2 = censusCSVList.get(j + 1);
				if (comp.compare(c1, c2) > 0) {
					censusCSVList.set(j, c2);
					censusCSVList.set(j + 1, c1);
				}
			}
		}
	}

	public String sortCensusDataAccordingtoPopulation() throws CSVBuilderException {
		try {
			Reader readFile = Files.newBufferedReader(Paths.get(CSV_CENSUS_FILE));
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			this.censusCSVList = csvBuilder.getCsvFileList(readFile, IndianStateCensus.class);
		} catch (IOException e) {
			throw new CSVBuilderException("Unable to parse!! \nCSVBuilderException thrown....",
					CSVBuilderException.ExceptionType.UNABLE_TO_PARSE);
		} catch (CSVBuilderException e) {
			System.out.println("Unable to parse!! \nCSVBuilderException thrown....");
		} catch (CsvException e) {
		}
		Collections.sort(censusCSVList,
				Comparator.comparing(census -> ((IndianStateCensus) census).PopulationData()).reversed());
		String sortedDataJson = new Gson().toJson(censusCSVList);
		return sortedDataJson;
	}

	public String sortCensusDataAccordingtoPopulationDensity() throws CSVBuilderException {
		try {
			Reader readFile = Files.newBufferedReader(Paths.get(CSV_CENSUS_FILE));
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			this.censusCSVList = csvBuilder.getCsvFileList(readFile, IndianStateCensus.class);
		} catch (IOException e) {
			throw new CSVBuilderException("Unable to parse!! \nCSVBuilderException thrown....",
					CSVBuilderException.ExceptionType.UNABLE_TO_PARSE);
		} catch (CSVBuilderException e) {
			System.out.println("Unable to parse!! \nCSVBuilderException thrown....");
		} catch (CsvException e) {
		}
		Collections.sort(censusCSVList,
				Comparator.comparing(census -> ((IndianStateCensus) census).DensityData()).reversed());
		String sortedDataJson = new Gson().toJson(censusCSVList);
		return sortedDataJson;
	}

}
