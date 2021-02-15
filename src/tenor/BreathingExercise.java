package tenor;

import javax.swing.JFrame;


public class BreathingExercise {

	// Class to represent a breathing exercise 
	// inTime is the amount of seconds to breathe in
	// holdTime is the amount of seconds to hold 
	// outTime is the amount of seconds to breathe out 
	public int inTime;
	public int holdTime;
	public int outTime;
	
	public BreathingExercise(int in, int hold, int out) {
		inTime = in;
		holdTime = hold;
		outTime = out;
	}
	
	public int getInTime() {
		return this.inTime;
	}
	
	public int getHoldTime() {
		return this.holdTime;
	}
	
	public int getOutTime() { 
		return this.outTime;
	}
	

	
	
}
