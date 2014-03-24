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
			
			processCommand(command);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    // Calls a python script from the shell to send the command over UART
    private void processCommand(int command) {
	try {
	    String cmd = "";
	    String commandString = Integer.toString(command);
	    String target = commandString.substring(0, 1);
	    String value = commandString.substring(1, commandString.length());
	    System.out.println("Command: " + command);
	    System.out.println("Target: " + target);
	    System.out.println("Value: " + value);
	    cmd = "/home/pi/git/seniordesign/pi/serial/send_string.py " + target;
	    Runtime.getRuntime().exec(cmd);
	    cmd = "/home/pi/git/seniordesign/pi/serial/send_string.py " + value;
	    Runtime.getRuntime().exec(cmd);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
