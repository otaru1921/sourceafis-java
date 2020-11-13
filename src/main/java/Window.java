import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.machinezoo.sourceafis.*;

public class Window {

	private JFrame frmLeitorDeDigital;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frmLeitorDeDigital.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public String fileChooser() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(frmLeitorDeDigital);
		if (result == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = fileChooser.getSelectedFile();
		    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
		    return selectedFile.getAbsolutePath();
		}
		else return "a";
	}
	
	public String testFinger(String filepath) throws IOException {
		// Parte de Comparação de Imagem
				byte[] probeImage = Files.readAllBytes(Paths.get(filepath));
				byte[] candidateImage1 = Files.readAllBytes(Paths.get("G:\\FingerPrints\\nivel1.tif"));
				byte[] candidateImage2 = Files.readAllBytes(Paths.get("G:\\FingerPrints\\nivel2.tif"));
				byte[] candidateImage3 = Files.readAllBytes(Paths.get("G:\\FingerPrints\\nivel3.tif"));

				FingerprintTemplate probe = new FingerprintTemplate(
				    new FingerprintImage()
				        .dpi(500)
				        .decode(probeImage));

				FingerprintTemplate candidate1 = new FingerprintTemplate(
				    new FingerprintImage()
				        .dpi(500)
				        .decode(candidateImage1));
				
				double score = new FingerprintMatcher()
					    .index(probe)
					    .match(candidate1);
				
				FingerprintTemplate candidate2 = new FingerprintTemplate(
					    new FingerprintImage()
				        .dpi(500)
				        .decode(candidateImage2));
				
				double score2 = new FingerprintMatcher()
					    .index(probe)
					    .match(candidate2);
				
				FingerprintTemplate candidate3 = new FingerprintTemplate(
					    new FingerprintImage()
				        .dpi(500)
				        .decode(candidateImage3));
				
				double score3 = new FingerprintMatcher()
					    .index(probe)
					    .match(candidate3);
				
				
				double threshold = 40;
				boolean matches = score >= threshold;
				boolean matches2 = score2 >= threshold;
				boolean matches3 = score3 >= threshold;
				System.out.println("Score de nível 1: "+score);
				System.out.println("Autorizado para nível 1: " + matches);
				System.out.println("Score de nível 2: "+score2);
				System.out.println("Autorizado para nível 2: " + matches2);
				System.out.println("Score de nível 3: "+score3);
				System.out.println("Autorizado para nível 3: " + matches3);
				
				if (matches) {
					return "1";
				}
				else if (matches2) {
					return "2";
				}
				else if (matches3) {
					return "3";
				}
				else return "Falha";
				
				// Fim da Comparação
	}
	
	
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLeitorDeDigital = new JFrame();
		frmLeitorDeDigital.setTitle("Leitor de Digital");
		frmLeitorDeDigital.setBounds(100, 100, 429, 119);
		frmLeitorDeDigital.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLeitorDeDigital.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Send");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String resultado = testFinger(textField.getText());
					if (resultado != "Falha") {
						Table janela = new Table(resultado);
						janela.run();
					}
					else {
						System.out.println("IMPRESSÃO DIGITAL NÃO IDENTIFICADA, TENTE NOVAMENTE.");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(126, 42, 89, 23);
		frmLeitorDeDigital.getContentPane().add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(27, 11, 357, 20);
		frmLeitorDeDigital.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Load File");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(fileChooser());
			}
		});
		btnNewButton_1.setBounds(27, 42, 89, 23);
		frmLeitorDeDigital.getContentPane().add(btnNewButton_1);
	}
}
