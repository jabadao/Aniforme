package fr.eni.clinique.ihm;

import fr.eni.clinique.ihm.login.EcranLogin;
import fr.eni.clinique.ihm.login.LoginController;

import javax.swing.*;

public class AppliTestIHM {
    public static MainFrame mainFrame;
    public static EcranLogin loginFrame;

    public static JOptionPane dialog;


    public static void main (String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                mainFrame = new MainFrame();
                loginFrame = new EcranLogin();
                dialog = new JOptionPane();

                LoginController loginControl = LoginController.getInstance();

                mainFrame.setVisible(false);
                if(loginControl.getCurrentPersonnel().getCodePers() < 0) {
                    loginFrame.setVisible(true);
                }
                else {
                    loginFrame.setVisible(false);
                }
            }
            
        });
    }

    public static void showError(String title, String message) {
        dialog.showMessageDialog(mainFrame, message, title, JOptionPane.ERROR_MESSAGE);
    }
}
