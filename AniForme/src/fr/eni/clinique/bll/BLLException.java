package fr.eni.clinique.bll;

import java.util.ArrayList;
import java.util.List;

public class BLLException extends Exception {

	private List<ParameterException> exceptions;
	
	public BLLException() {
		exceptions = new ArrayList<>();
	}
	
	public BLLException(String message) {
		super(message);
		exceptions = new ArrayList<>();
	}
	
	public void ajouterException(ParameterException e) {
		exceptions.add(e);
	}
	
	public int size() {
		return exceptions.size();
	}

	@Override
	public String getMessage() {
		String message = super.getMessage();
		if(exceptions.size() > 0) {
			message += "\nErreurs :";
			for(ParameterException e : exceptions) {
				message +="\n\t" + e.getMessage();
			}
		}
		return message;
	}
	
	
}
