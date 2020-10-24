package CSVBuilder;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import IndianStateCensus.StateCensusAnalyserException;

public interface ICSVBuilder<T> {

	public <T> Iterator<T> getCsvFileIterator(Reader reader, Class csvClass) throws CSVBuilderException, CsvException;

	public <T> List<T> getCsvFileList(Reader reader, Class csvClass) throws CSVBuilderException, CsvException;

}
