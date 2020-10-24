package IndianStateCensus;

import com.opencsv.bean.CsvBindByName;

public class IndianStateCensus {

	@CsvBindByName(column = "State")
	public String stateName;

	@CsvBindByName(column = "Population")
	private String population;

	@CsvBindByName(column = "Area")
	private String area;

	@CsvBindByName(column = "Density")
	private String density;

	@Override
	public String toString() {
		return "\nState Name : " + stateName + "| Population :  " + population + "| Area(persqkm) : " + area
				+ "| Density(persqkm) : " + density;
	}
}
