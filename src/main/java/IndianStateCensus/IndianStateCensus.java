package IndianStateCensus;

import com.opencsv.bean.CsvBindByName;

public class IndianStateCensus {

	@CsvBindByName(column = "State")
	public String stateName;

	@CsvBindByName(column = "Population")
	public String population;

	@CsvBindByName(column = "Area")
	public String area;

	@CsvBindByName(column = "Density")
	public String density;

	public int PopulationData() {
		int pop = Integer.parseInt(population);
		return pop;
	}

	public int DensityData() {
		int den = Integer.parseInt(density);
		return den;
	}

	@Override
	public String toString() {
		return "\nState Name : " + stateName + "| Population :  " + population + "| Area(persqkm) : " + area
				+ "| Density(persqkm) : " + density;
	}
}
