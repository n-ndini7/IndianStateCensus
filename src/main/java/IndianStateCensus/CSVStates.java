package IndianStateCensus;

import com.opencsv.bean.CsvBindByName;

public class CSVStates {

	@CsvBindByName(column = "State")
	private String stateName;

	@CsvBindByName(column = "TIN")
	private String tin;

	@CsvBindByName(column = "StateCode")
	private String code;

	@Override
	public String toString() {
		return "\nState Name : " + stateName + "| TIN :  " + tin + "| State Code : " + code;
	}
}
