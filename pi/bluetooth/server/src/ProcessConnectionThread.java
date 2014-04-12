package bluetooth;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.lang.Integer;

import javax.microedition.io.StreamConnection;

/** Receives command and interprets it.  Currently receives one of two commands and
 *  interprets them as either a high or low for the light.
 */
public class ProcessConnectionThread implements Runnable {
    
    private StreamConnection mConnection;
    
    // Constant that indicate command from devices
    private static final int EXIT_CMD = -1;
    private static final int KEY_RIGHT = 1;
    private static final int KEY_LEFT = 2;
    
    public ProcessConnectionThread(StreamConnection connection) {
	mConnection = connection;
    }
    
    @Override
	public void run() {
	try {
	    
	    InputStream inputStream = mConnection.openInputStream();
	    
	    System.out.println("waiting for input");
	    
	    while (true) {
		int command = inputStream.read();
		
		if (command == EXIT_CMD) {
		    System.out.println("Exiting process...");
		    break;
		}
		
		processCommand(command, inputStream);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    // Calls a python script from the shell to send the command over UART
    private void processCommand(int command, InputStream inputStream) {
	try {
	    String cmd = "";
	    int target = command;
	    int value = inputStream.read() * -1 + 200;
	    System.out.println("Target: " + target);
	    System.out.println("Value: " + value);
	    //cmd = "sudo /home/pi/git/seniordesign/pi/433Utils/RPi_utils/codesend " + target;
	    //Runtime.getRuntime().exec(cmd);
	    cmd = "sudo /home/pi/git/seniordesign/pi/433Utils/RPi_utils/codesend " + value;
	    Runtime.getRuntime().exec(cmd);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
