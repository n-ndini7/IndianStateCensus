package IndianStateCensus;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import IndianStateCensus.StateCensusAnalyserException.ExceptionType;

public class OpenCSVBuilder implements ICSVBuilder {

	public <T> Iterator<T> getCSVFileIterator(Reader readFile, Class class1)
			throws StateCensusAnalyserException, CSVBuilderException {
		return (Iterator<T>) this.getCSVBean(readFile, class1).iterator();
	}

	public <T> List<T> getCsvFileList(Reader reader, Class csvClass)
			throws CSVBuilderException, StateCensusAnalyserException {
		return (List<T>) this.getCSVBean(reader, csvClass).parse();
	}

	private <T> CsvToBean<T> getCSVBean(Reader reader, Class csvClass)
			throws CSVBuilderException, StateCensusAnalyserException {
		try {
			CsvToBeanBuilder<T> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(csvClass);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			return csvToBeanBuilder.build();
		} catch (IllegalStateException e) {
			throw new StateCensusAnalyserException(ExceptionType.INVALID_CSV_TYPE,
					"Unable to parse... \nWrong CSV File type given!! \nInvalidCSVFileTypeException thrown...");
		}
	}

}
