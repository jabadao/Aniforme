package fr.eni.clinique.bo;

public class Animal {
	
	private int codeAnimal;
	private String nomAnimal;
	private String sexe;
	private String couleur;
	private Race race_espece;
	private Client client;
	private String tatouage;
	private String antecedent;
	private boolean archive;
	


	
	
	public int getCodeAnimal() {
		return codeAnimal;
	}
	public void setCodeAnimal(int codeAnimal) {
		this.codeAnimal = codeAnimal;
	}
	public String getNomAnimal() {
		return nomAnimal;
	}
	public void setNomAnimal(String nomAnimal) {
		this.nomAnimal = nomAnimal;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public String getCouleur() {
		return couleur;
	}
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	public String getAntecedent() {
		return antecedent;
	}
	public void setAntecedent(String antecedent) {
		this.antecedent = antecedent;
	}
	public String getTatouage() {
		return tatouage;
	}
	public void setTatouage(String tatouage) {
		this.tatouage = tatouage;
	}
	public boolean isArchive() {
		return archive;
	}
	public void setArchive(boolean archive) {
		this.archive = archive;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Race getRace_espece() {
		return race_espece;
	}
	public void setRace_espece(Race race_espece) {
		this.race_espece = race_espece;
	}
	
	
	public Animal() {
		super();
		this.setCodeAnimal(0);
		this.setNomAnimal("");
		this.setSexe("");
		this.setCouleur("");
		this.setTatouage("");
		this.setAntecedent("");
		this.setArchive(false);
		this.setClient(new Client());
		this.setRace_espece(new Race());
		
	}
	
	
	public Animal(String nomAnimal, String sexe, String couleur, Race race_espece, Client client, String tatouage, String antecedent, boolean archive) {
		this();
		this.setNomAnimal(nomAnimal);
		this.setSexe(sexe);
		this.setCouleur(couleur);
		this.setAntecedent(antecedent);
		this.setTatouage(tatouage);
		this.setArchive(archive);
		this.setClient(client);
		this.setRace_espece(race_espece);
	}
	
	
	public Animal(int codeAnimal, String nomAnimal, String sexe, String couleur, Race race_espece, Client client, String tatouage, String antecedent, boolean archive) {
		this(nomAnimal, sexe, couleur, race_espece, client, tatouage, antecedent, archive);
		this.setCodeAnimal(codeAnimal);
	}
	
	
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Animal [codeAnimal=");
		builder.append(codeAnimal);
		builder.append(", nomAnimal=");
		builder.append(nomAnimal);
		builder.append(", sexe=");
		builder.append(sexe);
		builder.append(", couleur=");
		builder.append(couleur);
		builder.append(", antecedent=");
		builder.append(antecedent);
		builder.append(", tatouage=");
		builder.append(tatouage);
		builder.append(", archive=");
		builder.append(archive);
		builder.append(", client=");
		builder.append(client);
		builder.append(", race_espece=");
		builder.append(race_espece);
		builder.append("]");
		return builder.toString();
	}
	

}
