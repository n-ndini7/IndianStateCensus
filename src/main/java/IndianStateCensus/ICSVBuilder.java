package IndianStateCensus;

import java.io.Reader;
import java.util.Iterator;

public interface ICSVBuilder {
	
	public <E> Iterator<E> getCsvFileIterator(Reader reader, Class csvClass) throws StateCensusAnalyserException;
}
