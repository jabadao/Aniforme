package fr.eni.clinique.ihm.gestionPersonnel;

import fr.eni.clinique.ihm.MainFrame;
import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bo.Observable.Observer;
import fr.eni.clinique.bo.Personnel;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class EcranGestionPersonnel extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTable tablePersonnel;
	private PersonnelTableModel model;

	private JScrollPane scrollPane;
	private BoutonMenuPersonnel panelButtons;
	
	private JPanel buttonForm;
	private JPanel panelPrincipal;

	URL iconURL;
	ImageIcon icon;

	public EcranGestionPersonnel() {
		setSize(600, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(530, 150);
		setContentPane(getPanelPrincipal());

		this.iconURL = MainFrame.class.getResource("ressources/ico_veto.png");
		this.icon = new ImageIcon(iconURL);

		setIconImage(icon.getImage());
		setTitle("Gestion du personnel");
		setResizable(false);
	

		try {
			PersonnelController.getInstance().registerToCurrentPersonnel(this);
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}
	
	private JPanel getPanelPrincipal() {
		if (panelPrincipal == null) {
			panelPrincipal = new JPanel(new BorderLayout());
			
			panelPrincipal.add(getButtonForm(), BorderLayout.NORTH);
			panelPrincipal.add(getScrollPane(), BorderLayout.SOUTH);
		}
		
		return panelPrincipal;
	}

	private JScrollPane getScrollPane() {
		if(scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTablePersonnel());
		}
		return scrollPane;
	}


	private JTable getTablePersonnel() {
		if(tablePersonnel == null) {
			try {
				model = new PersonnelTableModel();
				tablePersonnel = new JTable(model);
				tablePersonnel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				tablePersonnel.setRowHeight(30);
				tablePersonnel.getSelectionModel().addListSelectionListener(new ListSelectionListener() {


					@Override
					public void valueChanged(ListSelectionEvent arg0) {
						int index = tablePersonnel.getSelectedRow();
						try {
							PersonnelController.getInstance().setPersonnel(index);
						} catch (PersonnelNotFoundException e) {
							e.printStackTrace();
						} catch (BLLException e) {
							e.printStackTrace();
						}
					}
				});
			} catch (BLLException e) {
				JOptionPane.showMessageDialog(this, "Erreur lors du chargement des données", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}
		return tablePersonnel;
	}
	
	private JPanel getButtonForm() {
		if (buttonForm == null) {
			buttonForm = new JPanel(new GridBagLayout());
			
			addComponentTo(getPanelButtons(), buttonForm, 0, 0, 2, 1, 1);
			
		}
		
		return buttonForm;
	}
	
	
	private BoutonMenuPersonnel getPanelButtons() {
		if (panelButtons == null) {
			panelButtons = new BoutonMenuPersonnel();
		}
		
		return panelButtons;
		
	}
	
	
	
	private void addComponentTo(JComponent component, JPanel panel,
			int x, int y, int width, int height,
			double weightX) {
		addComponentTo(component, panel, x, y, width, height, weightX, true);
	}

	private void addComponentTo(JComponent component, JPanel panel,
			int x, int y, int width, int height,
			double weightX, boolean fillHorizontal) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = width;
		gbc.gridheight = height;
		gbc.weightx = weightX;
		if(fillHorizontal) {
			gbc.fill = GridBagConstraints.HORIZONTAL;
		}
		gbc.insets = new Insets(7, 10, 5, 10);
		panel.add(component, gbc);
	}


	@Override
	public void onChanged(Object value) {
		int index = model.getIndexOf((Personnel)value);
		tablePersonnel.setRowSelectionInterval(index, index);


	}
	
	
	public void onCreate() {
		
	}
}
