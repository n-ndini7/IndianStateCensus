package CSVBuilder;

import java.io.Reader;
import java.util.Iterator;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import IndianStateCensus.StateCensusAnalyserException;
import IndianStateCensus.StateCensusAnalyserException.ExceptionType;

public class OpenCSVBuilder implements ICSVBuilder {

	public <E> Iterator<E> getCsvFileIterator(Reader reader, Class csvClass) throws StateCensusAnalyserException {
		try {
			CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<E>(reader);
			csvToBeanBuilder.withType(csvClass);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<E> csvToBean = csvToBeanBuilder.build();
			return csvToBean.iterator();
		} catch (IllegalStateException e) {
			throw new StateCensusAnalyserException(ExceptionType.UNABLE_TO_PARSE,
					"Unable to parse State Census CSV File!! \nUnableToParseException thrown....");

		}
	}

}
