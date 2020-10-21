package CSVBuilder;

import java.io.Reader;
import java.util.Iterator;

import IndianStateCensus.StateCensusAnalyserException;

public interface ICSVBuilder {
	
	public <E> Iterator<E> getCsvFileIterator(Reader reader, Class csvClass) throws StateCensusAnalyserException, StateCensusAnalyserException;
}
