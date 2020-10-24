package CSVBuilder;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import IndianStateCensus.StateCensusAnalyserException;
import IndianStateCensus.StateCensusAnalyserException.ExceptionType;

public class OpenCSVBuilder<E> implements ICSVBuilder {
	public Iterator<E> getCsvFileIterator(Reader reader, Class csvClass) throws CsvException {
		return this.getCSVBean(reader, csvClass).iterator();
	}

	public List getCsvFileList(Reader reader, Class csvClass) throws CsvException {
		return this.getCSVBean(reader, csvClass).parse();
	}

	private CsvToBean<E> getCSVBean(Reader reader, Class csvClass) throws CsvException {
		try {
			CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<E>(reader);
			csvToBeanBuilder.withType(csvClass);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true).withThrowExceptions(false);
			return csvToBeanBuilder.build();
		} catch (IllegalStateException e) {
			throw new CsvException();
		}
	}

}
