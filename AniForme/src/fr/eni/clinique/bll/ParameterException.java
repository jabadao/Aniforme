package fr.eni.clinique.bll;

public class ParameterException extends Exception {

	public ParameterException(String param, String message) {
		super(param + " : " + message);
	}
}
