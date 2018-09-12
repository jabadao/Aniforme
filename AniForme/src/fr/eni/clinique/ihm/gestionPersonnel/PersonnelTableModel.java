package fr.eni.clinique.ihm.gestionPersonnel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.PersonnelManager;
import fr.eni.clinique.bo.Personnel;

public class PersonnelTableModel extends AbstractTableModel {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Personnel> personnels = new ArrayList<>();

	private String[] nomColonnes = {"Nom", "Role", "Archive"};

	public PersonnelTableModel() throws BLLException {
		updateData();
	}

	public void updateData() throws BLLException {
		personnels = new PersonnelManager().getPersonnels();
		fireTableDataChanged();
	}

	@Override
	public int getColumnCount() {
		return nomColonnes.length;
	}

	@Override
	public int getRowCount() {
		return personnels.size();
	}

	@Override
	public String getColumnName(int column) {
		return nomColonnes[column];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return getValueAt(0, columnIndex).getClass();
	}

	@Override
	public Object getValueAt(int rowIndex, int colIndex) {
		Object value = null;

		if(rowIndex >= 0 && rowIndex < personnels.size()) {
			Personnel personnel = personnels.get(rowIndex);
			switch(colIndex) {
			case 0:
				value = personnel.getNom();
				break;
			case 1:
				value = personnel.getRole();
				break;
			case 2:
				value = personnel.isArchive();
				break;			
			}
		}

		return value;
	}

	public int getIndexOf(Personnel personnel) {
		System.out.println(personnels);
		return personnels.indexOf(personnel);
	}

}
