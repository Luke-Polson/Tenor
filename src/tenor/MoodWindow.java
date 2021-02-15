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
		
		// here, choose what file to use based on the type of window being created 
		try {
			quoteList = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		
		// FINISH 
		breathingButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BreathingExercise b = new BreathingExercise(4, 4, 4);
				// make a window that asks to put in a time ? 	
			}
		});
		
		quoteButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {	
				
				int index = (int) (2 * Math.floor(rand.nextInt(quoteList.size() / 2)));
				quoteArea.setText("\"" + quoteList.get(index) + "\"");
				authorLabel.setText(" - " + quoteList.get(index+1));
			}

		});
		
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
		
		this.addObject(quoteButton, gbl, gbc, 1, 0, 1, 1);
		this.addObject(breathingButton, gbl, gbc, 1, 1, 1, 1);
		this.addObject(mainMessage, gbl, gbc, 0, 0, 2, 1);
		this.addObject(quoteArea, gbl, gbc, 2, 0, 2, 1);
		this.addObject(authorLabel, gbl, gbc, 4, 0, 2, 1);
		
		
	}
	
	public void addObject(Component component, GridBagLayout layout, GridBagConstraints gbc, int gridy, int gridx, int gridwidth, int gridheight){

        gbc.gridx = gridx;
        gbc.gridy = gridy;

        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;

        layout.setConstraints(component, gbc);
        this.add(component);
        
    }
	
	

}
