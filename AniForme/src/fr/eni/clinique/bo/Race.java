package fr.eni.clinique.bo;

public class Race {

	private String race;
	private String espece;


	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public String getEspece() {
		return espece;
	}
	public void setEspece(String espece) {
		this.espece = espece;
	}
	
	
	
	

	public Race() {
		super();
		this.setRace("");
		this.setEspece("");
	}


	public Race(String race, String espece) {
		this();
		this.setRace(race);
		this.setEspece(espece);
	}	
	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Race [race=");
		builder.append(race);
		builder.append(", espece=");
		builder.append(espece);
		builder.append("]");
		return builder.toString();
	}





}
