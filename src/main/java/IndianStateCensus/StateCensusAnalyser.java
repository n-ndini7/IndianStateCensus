package IndianStateCensus;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Scanner;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

//UC1 - ability of analyser to load Indian state census information from a csv file
//TC1.1 added - refactored UC1 to check for no of entries while reading equals to the no of entries in a csv file
//TC1.2 added - custom exception thrown in case of invalid file path given
public class StateCensusAnalyser {

	private static String CSV_CENSUS_FILE = "./IndianStateCensusData.csv";

	public int readData(String DATA_FILE) throws StateCensusAnalyserException {
		int noOfEntries = 0;
		try {
			Reader readFile = Files.newBufferedReader(Paths.get(DATA_FILE));
			CsvToBean<IndianStateCensus> user = new CsvToBeanBuilder(readFile).withType(IndianStateCensus.class)
					.withIgnoreLeadingWhiteSpace(true).build();
			Iterator<IndianStateCensus> userIterator = user.iterator();
			while (userIterator.hasNext()) {
				IndianStateCensus csvuser = userIterator.next();
				System.out.println(csvuser);
				System.out.println(
						"===================================================================================================");
				noOfEntries++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.INVALID_FILE_PATH,
					"Invalid File Location given!! \nInvalidFilePathException thrown....");

		}
		return noOfEntries;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to Indian State Census Analyser Program!");
		System.out.println("================================================");
		System.out.println("1.Read Indian State Census Data \n2.Exit");
		System.out.println("Enter your choice:");
		int choice = Integer.parseInt(sc.nextLine());
		StateCensusAnalyser object = new StateCensusAnalyser();
		switch (choice) {
		case 1:
			try {
				object.readData(CSV_CENSUS_FILE);
			} catch (StateCensusAnalyserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 2:
			System.out.println("Thanks for using application!");
			System.exit(0);
		}
	}
}
