package week3enr;

// Stephen Russell


// \/ import tools \/
import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;


public class MiniMusicPlayer3 {
	
	// creates JFrame
	static JFrame f = new JFrame("My first music video");
	static MyDrawPanel ml;

	public static void main(String[] args) {
		
		// creates the app
		MiniMusicPlayer3 mini = new MiniMusicPlayer3(); 
		
		// tells the app to run
		mini.go(); 
	}

	public void setUpGui() {
		
		// creates the new MyDrawPanel
		ml = new MyDrawPanel();
		
		 // sets the windows content pane
		f.setContentPane(ml);
		
		f.setBounds(30, 30, 300, 300);
		
		// sets the window to visible
		f.setVisible(true); 
	}

	public void go() {
		
		// this setsup the GUI
		setUpGui(); 

		try { 
			
			// gets the midi sequencer
			Sequencer sequencer = MidiSystem.getSequencer(); 
			
			sequencer.open();

			// sets the controller listener
			sequencer.addControllerEventListener(ml, new int[] { 127 }); 
			
			Sequence seq = new Sequence(Sequence.PPQ, 4);
			
			Track track = seq.createTrack();

			// \/ makes two midi events that has a midi message \/

			int r = 0;
			
			// generate tones
			for (int i = 0; i < 60; i += 4) { 
				
				r = (int) ((Math.random() * 50) + 1);

				track.add(makeEvent(144, 1, r, 100, i));

				track.add(makeEvent(176, 1, 127, 0, i));

				track.add(makeEvent(128, 1, r, 100, i + 2));
				
			}


			sequencer.setSequence(seq);

			sequencer.start();
			
			sequencer.setTempoInBPM(120);
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
			
		}
		
	}

	public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
		
		MidiEvent event = null;
		
		try {
			
			ShortMessage a = new ShortMessage();
			
			// makse the message
			a.setMessage(comd, chan, one, two); 
			
			// makes an event
			event = new MidiEvent(a, tick); 
		} catch (Exception e) {
			
		}

		return event;
		
	}

	class MyDrawPanel extends JPanel implements ControllerEventListener {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		boolean msg = false;

		public void controlChange(ShortMessage event) {
			msg = true;
			
			repaint();
			
		}

		public void paintComponent(Graphics g) {
			
			if (msg) {
				
				@SuppressWarnings("unused")
				Graphics2D g2 = (Graphics2D) g;

				// creates a random color
				int r = (int) (Math.random() * 250);
				int gr = (int) (Math.random() * 250);
				int b = (int) (Math.random() * 250);

				 // sets the color
				g.setColor(new Color(r, gr, b));

				// creates a random width and height
				int ht = (int) ((Math.random() * 120) + 10);
				int width = (int) ((Math.random() * 120) + 10);

				// creates a random position
				int x = (int) ((Math.random() * 40) + 10);
				int y = (int) ((Math.random() * 40) + 10);

				// draws the rectangle
				g.fillRect(x, y, ht, width); 
				msg = false;
				
			} 
		} 
	}
} 

