package fr.eni.clinique.bo;

public class Personnel {
	
	private int codePers;
	private String nom;
	private String motPasse;
	private String role;
	private boolean archive;
	
	
	public int getCodePers() {
		return codePers;
	}
	public void setCodePers(int codePers) {
		this.codePers = codePers;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getMotPasse() {
		return motPasse;
	}
	public void setMotPasse(String motPasse) {
		this.motPasse = motPasse;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isArchive() {
		return archive;
	}
	public void setArchive(boolean archive) {
		this.archive = archive;
	}

	
	
	
	public Personnel() {
		super();
		this.setCodePers(-1);
		this.setNom("");
		this.setMotPasse("");
		this.setRole("");
		this.setArchive(false);
	}
		
	
	public Personnel(String login, String motPasse, String role, boolean archive) {
		this();
		this.setNom(login);
		this.setMotPasse(motPasse);
		this.setRole(role);
		this.setArchive(archive);
	}
	
	
	public Personnel(int codePers, String login, String motPasse, String role, boolean archive) {
		this(login, motPasse, role, archive);
		this.setCodePers(codePers);
	}
	
	
	
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Personnel [codePers=");
		builder.append(codePers);
		builder.append(", nom=");
		builder.append(nom);
		builder.append(", motPasse=");
		builder.append(motPasse);
		builder.append(", role=");
		builder.append(role);
		builder.append(", archive=");
		builder.append(archive);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	

}
