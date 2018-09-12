package fr.eni.clinique.ihm.login;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.*;

import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.ihm.AppliTestIHM;
import fr.eni.clinique.ihm.MainFrame;


public class EcranLogin extends JFrame {


	private static final long serialVersionUID = 1L;

	private JPanel panelConnexion;

	private JLabel labelTitle;
	private JLabel imageLogo;

	private JLabel lblLogin;
	private JTextField txtLogin;

	private JLabel lblMotPasse;
	private JTextField txtMotPasse;

	private JButton valider;

	private LoginController loginControll;
	
	URL iconURL;
	ImageIcon icon;

	public EcranLogin() {
	    this.loginControll = LoginController.getInstance();

		setSize(400, 350);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(600, 250);

        this.iconURL = MainFrame.class.getResource("ressources/ico_veto.png");
        this.icon = new ImageIcon(iconURL);

		setContentPane(getLoginForm());
		setIconImage(icon.getImage());
		setResizable(false);
		setTitle("Connexion");
	}
	


	private JPanel getLoginForm() {

		if (panelConnexion == null) {
			panelConnexion = new JPanel(new GridBagLayout());

			addComponentTo(getLabelTitle(), panelConnexion, 0,0,1,1,0.2);
			addComponentTo(getImageLogo(), panelConnexion, 1, 0, 1, 1, 0.8);

			addComponentTo(getLblLogin(), panelConnexion, 0, 2, 1, 1, 0.1);
			addComponentTo(getTxtLogin(), panelConnexion, 1, 2, 1, 1, 0.9);
			addComponentTo(getLblMotPasse(), panelConnexion, 0, 3, 1, 1, 0.1);
			addComponentTo(getTxtMotPasse(), panelConnexion, 1, 3, 1, 1, 0.9);

			addComponentTo(getValider(), panelConnexion, 0, 6, 2, 2, 1);
		}

		return panelConnexion;

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

    public JLabel getLabelTitle() {
	    if(this.labelTitle == null){
	        this.labelTitle = new JLabel("Ani'Form");
        }
        return labelTitle;
    }

    public JLabel getLblLogin() {
		if(lblLogin == null) {
			lblLogin = new JLabel("Login: ");
			lblLogin.setHorizontalAlignment(SwingConstants.LEFT);
		}

		return lblLogin;
	}


	public JTextField getTxtLogin() {
		if(txtLogin == null) {
			txtLogin = new JTextField();
		}
		return txtLogin;
	}


	public JLabel getLblMotPasse() {
		if(lblMotPasse == null) {
			lblMotPasse = new JLabel("Mot de passe: ");
			lblMotPasse.setHorizontalAlignment(SwingConstants.LEFT);
		}

		return lblMotPasse;
	}


	public JTextField getTxtMotPasse() {
		if(txtMotPasse == null) {
			txtMotPasse = new JTextField();
		}
		return txtMotPasse;
	}

	public JButton getValider() {
		if(valider == null) {
			valider = new JButton("Connexion");
			valider.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					//notifyObservers(connexion_on);
					LoginController logCon = LoginController.getInstance();
					logCon.connexion(getTxtLogin().getText(), getTxtMotPasse().getText()); 
					}
			});
		}
		return valider;
	}

    public JLabel getImageLogo() {
	    if(this.imageLogo == null){
            ImageIcon icon = new ImageIcon(MainFrame.class.getResource("ressources/fond_frame.jpg"));
	        this.imageLogo = new JLabel(icon);
        }
        return imageLogo;
    }

    public void reset(){
	    this.txtLogin.setText("");
	    this.txtMotPasse.setText("");
	    this.loginControll.setCurrentPersonnel(new Personnel());
        AppliTestIHM.mainFrame.setVisible(false);
        this.setVisible(true);
        AppliTestIHM.mainFrame.getLabelConnectedPersonnelName().setText("");
    }
}
