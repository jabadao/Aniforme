package fr.eni.clinique.bo;

import java.util.ArrayList;
import java.util.List;


public class Observable<T> {
	
	public interface Observer {
		public void onChanged(Object value);
	}
	
	private T value;
	
	List<Observer> observers = new ArrayList<>();
	
	public T get() {
		return value;
	}
	
	
	public void set(T newValue) {
		this.value = newValue;
		notifyObservers();
	}
	
	
	private void notifyObservers() {
		for (Observer o : observers) {
			o.onChanged(value);
		}
	}
	
	public void registerObserver(Observer observer) {
		observers.add(observer);
	}
	
	public void unregisterObserver(Observer observer) {
		observers.remove(observer);
	}

}
