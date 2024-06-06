package mindmeal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.Font;

public class KPP extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public KPP() {
		getContentPane().setBackground(new Color(0, 128, 255));
        setTitle("Korisnički plan prehrane");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        
        JButton btnPDF = new JButton("Dijabetes tip 1 (PDF)");
        btnPDF.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnPDF.setBackground(new Color(255, 255, 255));
        btnPDF.setBounds(85, 115, 224, 30);
        getContentPane().add(btnPDF);
        
        JLabel lblKPP = new JLabel("Korisnički plan prehrane");
        lblKPP.setForeground(new Color(255, 255, 255));
        lblKPP.setHorizontalAlignment(SwingConstants.CENTER);
        lblKPP.setFont(new Font("SansSerif", Font.BOLD, 15));
        lblKPP.setBounds(85, 68, 224, 13);
        getContentPane().add(lblKPP);
        
        JLabel lblNewLabel = new JLabel("⸙");
        lblNewLabel.setForeground(new Color(128, 255, 0));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 33));
        lblNewLabel.setBounds(50, 100, 25, 44);
        getContentPane().add(lblNewLabel);
        
        btnPDF.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String filePath = "C:\\Users\\Karen\\eclipse-workspace\\MindMeal\\Korisnički plan prehrane.pdf";
                    
                    if (Desktop.isDesktopSupported()) {
                        Desktop.getDesktop().open(new File(filePath));
                    } else {
                        JOptionPane.showMessageDialog(null, "Radna površina nije podržana za otvaranje PDF datoteka.");
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Nije moguće otvoriti PDF datoteku: " + ex.getMessage());
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                KPP kpp = new KPP();
                kpp.setVisible(true);
            }
        });
    }
}
