package tenor;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class TenorUI {
	
	
	
	public JFrame mainframe, quoteFrame, veryBadFrame, badFrame, okayFrame, goodFrame, veryGoodFrame;
	
	// main frame labels 
	public JLabel quoteLabel, mainLabel, veryBad, bad, okay, good, veryGood;
	
	public void addObject(Component component, Container yourcontainer, GridBagLayout layout, GridBagConstraints gbc, int gridy, int gridx, int gridwidth, int gridheight){

        gbc.gridx = gridx;
        gbc.gridy = gridy;

        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;

        layout.setConstraints(component, gbc);
        yourcontainer.add(component);
    }
	
	public void populateMainFrame() {
		
		mainframe = new JFrame();
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainframe.setSize(700, 500);
		mainframe.setTitle("Tenor");
		
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		
		mainframe.setLayout(gbl);
		
		mainLabel = new JLabel("How are you feeling today?", SwingConstants.CENTER);
		mainLabel.setFont (mainLabel.getFont ().deriveFont (32.0f));
		
		veryBad = new JLabel("veryBad", SwingConstants.CENTER);
		bad = new JLabel("bad", SwingConstants.CENTER);
		okay = new JLabel("okay", SwingConstants.CENTER);
		good = new JLabel("good", SwingConstants.CENTER);
		veryGood = new JLabel("veryGood", SwingConstants.CENTER);
		
		gbc.ipadx = 80;
		gbc.ipady = 50;	
		
		addObject(mainLabel, mainframe, gbl, gbc, 0, 0, 5, 1);
		
		gbc.ipady = 3;
		
		addObject(veryBad, mainframe, gbl, gbc, 1, 0, 1, 1);
		addObject(bad, mainframe, gbl, gbc, 1, 1, 1, 1);
		addObject(okay, mainframe, gbl, gbc, 1, 2, 1, 1);
		addObject(good, mainframe, gbl, gbc, 1, 3, 1, 1);
		addObject(veryGood, mainframe, gbl, gbc, 1, 4, 1, 1);
		
		mainframe.setVisible(true);
		
	}
	
	// again, a lot of repetition here. perhaps try to make a general 'populateWindow' method? Have a number which further
	// populates according to the mood ? 
	
	public void createMouseListeners() {
		
		veryBad.addMouseListener(new MouseAdapter() {	
			public void mouseClicked(MouseEvent e) {
				mainframe.setVisible(false);
				// provide parameters to MoodWindow so it populates it correctly.
				MoodWindow mw = new MoodWindow(mainframe, "Sorry you're not feeling the best.", 0);
				mw.setVisible(true);
			}
		});
		
		bad.addMouseListener(new MouseAdapter() {	
			public void mouseClicked(MouseEvent e) {
				mainframe.setVisible(false);
				
				MoodWindow mw = new MoodWindow(mainframe, "Sorry you're not feeling the best.", 1);
				mw.setVisible(true);
				
			}
		});
		
		okay.addMouseListener(new MouseAdapter() {	
			public void mouseClicked(MouseEvent e) {
				mainframe.setVisible(false);
				
				MoodWindow mw = new MoodWindow(mainframe, "It's okay to have an okay day sometimes.", 2);
				mw.setVisible(true);
			}
		});
		
		good.addMouseListener(new MouseAdapter() {	
			public void mouseClicked(MouseEvent e) {
				mainframe.setVisible(false);
				
				MoodWindow mw = new MoodWindow(mainframe, "Glad you're doing well!", 3);
				mw.setVisible(true);
			}
		});
		
		veryGood.addMouseListener(new MouseAdapter() {	
			public void mouseClicked(MouseEvent e) {
				mainframe.setVisible(false);
				
				MoodWindow mw = new MoodWindow(mainframe, "Glad you're having a great day!", 4);
				mw.setVisible(true);
			}
		});
			
	}
	
	
	
	
	
}
