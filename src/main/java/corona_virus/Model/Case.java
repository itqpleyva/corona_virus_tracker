package corona_virus.Model;

public class Case {

	private String state;
	private String country;
	private int latestTotal;
	private int yesterdayTotal;
	
	
	public Case(String state, String country, int latestTotal, int yesterdayTotal) {
		super();
		this.state = state;
		this.country = country;
		this.latestTotal = latestTotal;
		this.yesterdayTotal = yesterdayTotal;
		
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getLatestTotal() {
		return latestTotal;
	}
	public void setLatestTotal(int latestTotal) {
		this.latestTotal = latestTotal;
	}
	
	@Override
	public String toString() {
		return "Case [state=" + state + ", country=" + country + ", latestTotal=" + latestTotal + "]";
	}

	public int getYesterdayTotal() {
		return yesterdayTotal;
	}

	public void setYesterdayTotal(int yesterdayTotal) {
		this.yesterdayTotal = yesterdayTotal;
	}
	
}
