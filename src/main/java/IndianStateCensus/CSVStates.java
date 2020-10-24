package IndianStateCensus;

import com.opencsv.bean.CsvBindByName;

public class CSVStates {

	@CsvBindByName(column = "State")
	public String stateName;

	@CsvBindByName(column = "TIN")
	public String tin;

	@CsvBindByName(column = "StateCode")
	public String code;

	@Override
	public String toString() {
		return "\nState Name : " + stateName + "| TIN :  " + tin + "| State Code : " + code;
	}
}
