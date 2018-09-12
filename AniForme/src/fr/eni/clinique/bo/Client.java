package fr.eni.clinique.bo;

public class Client {

	private int codeClient;
	private String nomClient;
	private String prenomClient;
	private String adresse1;
	private String adresse2;
	private String codePostal;
	private String ville;
	private String numTel;
	private String assurance;
	private String email;
	private String remarque;
	private boolean archive;
	public int getCodeClient() {
		return codeClient;
	}
	public void setCodeClient(int codeClient) {
		this.codeClient = codeClient;
	}
	public String getNomClient() {
		return nomClient;
	}
	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}
	public String getPrenomClient() {
		return prenomClient;
	}
	public void setPrenomClient(String prenomClient) {
		this.prenomClient = prenomClient;
	}
	public String getAdresse1() {
		return adresse1;
	}
	public void setAdresse1(String adresse1) {
		this.adresse1 = adresse1;
	}
	public String getAdresse2() {
		return adresse2;
	}
	public void setAdresse2(String adresse2) {
		this.adresse2 = adresse2;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getNumTel() {
		return numTel;
	}
	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}
	public String getAssurance() {
		return assurance;
	}
	public void setAssurance(String assurance) {
		this.assurance = assurance;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRemarque() {
		return remarque;
	}
	public void setRemarque(String remarque) {
		this.remarque = remarque;
	}
	public boolean isArchive() {
		return archive;
	}
	public void setArchive(boolean archive) {
		this.archive = archive;
	}
	
	
	public Client() {
		super();
		this.setCodeClient(0);
		this.setNomClient("");
		this.setPrenomClient("");
		this.setAdresse1("");
		this.setAdresse2("");
		this.setCodePostal("");
		this.setVille("");
		this.setNumTel("");
		this.setAssurance("");
		this.setEmail("");
		this.setRemarque("");
		this.setArchive(false);
		
	}
	
	public Client(String nomClient, String prenomClient, String adresse1, String adresse2,
			String codePostal, String ville, String numTel, String assurance, String email, String remarque,
			boolean archive) {
		this();
		this.setNomClient(nomClient);
		this.setPrenomClient(prenomClient);
		this.setAdresse1(adresse1);
		this.setAdresse2(adresse2);
		this.setCodePostal(codePostal);
		this.setVille(ville);
		this.setNumTel(numTel);
		this.setAssurance(assurance);
		this.setEmail(email);
		this.setRemarque(remarque);
		this.setArchive(archive);
	}
	
	
	public Client(int codeClient, String nomClient, String prenomClient, String adresse1, String adresse2,
			String codePostal, String ville, String numTel, String assurance, String email, String remarque,
			boolean archive) {
		this(nomClient, prenomClient, adresse1, adresse2, codePostal, ville, numTel, assurance, email, remarque, archive);
		this.setCodeClient(codeClient);
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Client [codeClient=");
		builder.append(codeClient);
		builder.append(", nomClient=");
		builder.append(nomClient);
		builder.append(", prenomClient=");
		builder.append(prenomClient);
		builder.append(", adresse1=");
		builder.append(adresse1);
		builder.append(", adresse2=");
		builder.append(adresse2);
		builder.append(", codePostal=");
		builder.append(codePostal);
		builder.append(", ville=");
		builder.append(ville);
		builder.append(", numTel=");
		builder.append(numTel);
		builder.append(", assurance=");
		builder.append(assurance);
		builder.append(", email=");
		builder.append(email);
		builder.append(", remarque=");
		builder.append(remarque);
		builder.append(", archive=");
		builder.append(archive);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	
	
}
