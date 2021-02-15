package tenor;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class BreathingWindow extends JFrame {

	BreathingExercise exercise;
	JFrame parent;
	
	JLabel inLabel, holdLabel, outLabel;
	
	public BreathingWindow(JFrame parent, BreathingExercise b) {
		
		exercise = b;
		this.parent = parent;
		
		
	}
	
	public void populate() {
		
		inLabel = new JLabel(Integer.toString(exercise.getInTime()));
		holdLabel = new JLabel(Integer.toString(exercise.getHoldTime()));
		outLabel = new JLabel(Integer.toString(exercise.getOutTime()));
	
	}
	
}
