package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;

import model.Binaries;
import utils.Computer;
import utils.Converter;
import exception.CannotParseException;
import exception.CannotSimplifyException;
import exception.InvalidMinTermException;

public class Panel extends Frame implements ActionListener,// Pour evenements bouton
		WindowListener,// Pour evenements fenetre
		KeyListener// Pour appui touche
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6501846863042230657L;
	private JTextField input;
	private JButton button;
	private JTextField output;

	public Panel(int m) {
		setMargin(m);
		addWindowListener(this);

		setMinimumSize(new Dimension(16 * 35, 16 * 8));
		setLocationRelativeTo(null);

		GridBagLayout grid = new GridBagLayout();
		setLayout(grid);
		GridBagConstraints C = new GridBagConstraints();

		// Placement du premier
		C.gridx = 0;
		C.gridy = 0;
		C.gridwidth = 1;
		C.gridheight = 1;
		C.weightx = 1;
		C.weighty = 1;
		C.ipady = 4;

		input = new JTextField(35);
		grid.setConstraints(input, C);
		add(input);
		input.addKeyListener(this);

		// Placement du deuxieme
		C.gridy++;

		button = new JButton("Tester !");
		grid.setConstraints(button, C);
		add(button);
		button.addActionListener(this);

		// Placement du troisieme
		C.gridy++;

		output = new JTextField(35);
		grid.setConstraints(output, C);
		output.setEditable(false);
		add(output);
		output.addKeyListener(this);
				
		// Afficher
		pack();
		setVisible(true);

	}
	
	/**
	 * 
	 * @param test
	 * @return
	 * @throws CannotParseException 
	 * @throws InvalidMinTermException 
	 */
	public static String McCluskeySimplifyStrings(String test) throws CannotParseException, InvalidMinTermException {
		String[] binaryWords = test.split("\\+");
		
		int dimension = Converter.countLetters(binaryWords);
		Binaries bins = Converter.stringToBinaries(binaryWords,dimension);
		
		Binaries simplifiedMinTerms = McCluskeySimplify(bins,dimension);
		
		String result = Converter.binariesToString(simplifiedMinTerms);
		
		return result;
	}
	
	/**
	 * 
	 * @param bins
	 * @param dimension
	 * @return
	 */
	public static Binaries McCluskeySimplify(Binaries bins,int dimension) {
		List<Binaries> sortedBins = Computer.sortMinTerms(bins,dimension);
		Binaries simplifiedMinTerms = bins;
		boolean stop = false;
		
		do {
			try {
				simplifiedMinTerms = Computer.simplifyMinTerms(sortedBins,dimension);
				sortedBins = Computer.sortMinTerms(simplifiedMinTerms,dimension);
			} catch (CannotSimplifyException e) {
				stop = true;
			}		
		} while(!stop);
		
		return simplifiedMinTerms;
	}

	public void windowActivated(WindowEvent arg0) {
	}

	public void windowClosed(WindowEvent arg0) {
	}

	public void windowClosing(WindowEvent arg0) {
		System.exit(0);
	}

	public void windowDeactivated(WindowEvent arg0) {
	}

	public void windowDeiconified(WindowEvent arg0) {
	}

	public void windowIconified(WindowEvent arg0) {
	}

	public void windowOpened(WindowEvent arg0) {
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button) {
			String text = input.getText();
			String result = null;
			try {
				result = McCluskeySimplifyStrings(text);
				output.setText(result);
			} catch (CannotParseException e1) {
				output.setText(e1.getMessage());
			} catch (InvalidMinTermException e1) {
				output.setText(e1.getMessage());
			}
		}
	}

	public void insertUpdate(DocumentEvent e) {
	}

	public void changedUpdate(DocumentEvent e) {
	}

	public void removeUpdate(DocumentEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			button.doClick();
		}
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

}
