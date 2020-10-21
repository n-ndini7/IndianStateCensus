package IndianStateCensus;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface ICSVBuilder{

	public <T> Iterator<T> getCSVFileIterator(Reader reader, Class class1)
			throws StateCensusAnalyserException, CSVBuilderException;

	public <T> List<T> getCsvFileList(Reader reader, Class csvClass)
			throws StateCensusAnalyserException, CSVBuilderException;

}