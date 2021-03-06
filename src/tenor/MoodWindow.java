package tenor;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class MoodWindow extends JFrame{
	
	JLabel mainMessage;
	JButton breathingButton;
	JButton quoteButton;
	
	JLabel authorLabel;
	JTextArea quoteArea;
	
	Random rand = new Random();
	
	List<String> quoteList;
	
	JFrame parentFrame;
	GridBagLayout gbl = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();
	
	public MoodWindow(JFrame parent, String message, int mood) {
		
		String filePath = "";
		
		// the correct file is determined by the 'mood' parameter
		switch(mood) {
	
			case 0: {filePath = "quotes/veryBadQuotes.txt"; break;}
			case 1: {filePath = "quotes/badQuotes.txt"; break;}
			case 2: {filePath = "quotes/okayQuotes.txt"; break;}
			case 3: {filePath = "quotes/goodQuotes.txt"; break;}
			case 4: {filePath = "quotes/veryGoodQuotes.txt"; break;}
		}
		
		this.setSize(400, 400);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.populate(message);
		this.setTitle("Tenor");
		this.setLayout(gbl);
		
		// keep a reference to the main frame, so we can return to it 
		parentFrame = parent;
	
		// open the correct file, and read the contents into a list
		try {
			quoteList = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		
		// FINISH 
		breathingButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				displayBreathingOptions();
				
			}
		});
		
		quoteButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {	
				
				// choose a random number from 0 -> quoteList.size() / 2, and multiply it by 2 to find quote index
				// author index will be the quote index + 1
				int index = (int) (2 * Math.floor(rand.nextInt(quoteList.size() / 2)));
				quoteArea.setText("\"" + quoteList.get(index) + "\"");
				authorLabel.setText(" - " + quoteList.get(index+1));
			}

		});
		
		// mood windows are disposed of when closed to save memory, and the parent frame is set to visible again
		// so user can select another mood if they like. 
		this.addWindowListener(new WindowAdapter() {	
			@Override
			public void windowClosing(WindowEvent e) {
				parentFrame.setVisible(true);
				e.getWindow().dispose();
			}		
		});
		
	}

	public void populate(String message) {
		
		/* populate the frame with components 
		 * 	- message
		 * 	- button to print a quote
		 * 	- button to select a breathing exercise 
		 */
		
		mainMessage = new JLabel(message);
		quoteButton = new JButton();
		breathingButton = new JButton();
		
		quoteButton.setText("Quote");
		breathingButton.setText("Breathe");
		
		quoteArea = new JTextArea();
		quoteArea.setOpaque(false);
		quoteArea.setSize(200, 100);
		quoteArea.setLineWrap(true);
		quoteArea.setWrapStyleWord(true);
		quoteArea.setEditable(false);
		quoteArea.setBorder(new EmptyBorder(30, 10, 20, 10));
		
		authorLabel = new JLabel();
		
		this.addObject(this, quoteButton, gbl, gbc, 1, 0, 1, 1);
		this.addObject(this, breathingButton, gbl, gbc, 1, 1, 1, 1);
		this.addObject(this, mainMessage, gbl, gbc, 0, 0, 2, 1);
		this.addObject(this, quoteArea, gbl, gbc, 2, 0, 2, 1);
		this.addObject(this, authorLabel, gbl, gbc, 4, 0, 2, 1);
		
		
	}
	
	// addObject function adds components to the MoodWindow 
	public void addObject(JFrame frame, Component component, GridBagLayout layout, GridBagConstraints gbc, int gridy, int gridx, int gridwidth, int gridheight){

        gbc.gridx = gridx;
        gbc.gridy = gridy;

        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;

        layout.setConstraints(component, gbc);
        frame.add(component);
        
    }
	
	public void displayBreathingOptions() {
		
		// make this into a method ?
		JFrame breathingSetup = new JFrame("Tenor");
		breathingSetup.setSize(300, 300);
		
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		
		breathingSetup.setLayout(gbl);
		
		JTextField inTime, inHoldTime, outTime, outHoldTime;
		inTime = new JTextField(10);
		inHoldTime = new JTextField(10);
		outTime = new JTextField(10);
		outHoldTime = new JTextField(10);
		
		
		JButton breathe = new JButton("Breathe");
		breathe.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				int in = Integer.parseInt(inTime.getText());
				int inHold = Integer.parseInt(inHoldTime.getText());
				int out = Integer.parseInt(outTime.getText());
				int outHold = Integer.parseInt(outHoldTime.getText());
				
				BreathingExercise b = new BreathingExercise(in, inHold, out, outHold);
				
			}
			
		});
		
		
		JButton suggestedButton = new JButton("Suggested");
		suggestedButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	
		addObject(breathingSetup, suggestedButton, gbl, gbc, 0, 0, 1, 1);
		addObject(breathingSetup, inTime, gbl, gbc, 1, 0, 1, 1);
		addObject(breathingSetup, inHoldTime, gbl, gbc, 2, 0, 1, 1);
		addObject(breathingSetup, outTime, gbl, gbc, 3, 0, 1, 1);
		addObject(breathingSetup, outHoldTime, gbl, gbc, 4, 0, 1, 1);
		addObject(breathingSetup, breathe, gbl, gbc, 5, 0, 1, 1);
	
		
		breathingSetup.setVisible(true);
		
	}
	
	

}
