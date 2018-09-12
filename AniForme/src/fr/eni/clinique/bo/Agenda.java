package fr.eni.clinique.bo;

import java.util.Date;

public class Agenda {
	
	private Animal animal;
	private Personnel personnel;
	
	private Date dateRdv;

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public Personnel getPersonnel() {
		return personnel;
	}

	public void setPersonnel(Personnel personnel) {
		this.personnel = personnel;
	}

	public Date getDateRdv() {
		return dateRdv;
	}

	public void setDateRdv(Date dateRdv) {
		this.dateRdv = dateRdv;
	}
	
	
	
	public Agenda() {
		super();
		this.setAnimal(new Animal());
		this.setPersonnel(new Personnel());
		this.setDateRdv(null);
	}
	
	public Agenda(Animal animal, Personnel personnel, Date dateRdv) {
		this();
		this.setAnimal(animal);
		this.setPersonnel(personnel);
		this.setDateRdv(dateRdv);
	}

	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Agenda [animal=");
		builder.append(animal);
		builder.append(", personnel=");
		builder.append(personnel);
		builder.append(", dateRdv=");
		builder.append(dateRdv);
		builder.append("]");
		return builder.toString();
	}
		

}
