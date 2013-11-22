package bluetooth;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.InputStream;

import javax.microedition.io.StreamConnection;

public class ProcessConnectionThread implements Runnable{

    private StreamConnection mConnection;

    // Constant that indicate command from devices
    private static final int EXIT_CMD = -1;
    private static final int KEY_RIGHT = 1;
    private static final int KEY_LEFT = 2;

    public ProcessConnectionThread(StreamConnection connection)
    {
	mConnection = connection;
    }

        @Override
	    public void run() {
	    try {

		// prepare to receive data
		InputStream inputStream = mConnection.openInputStream();

		System.out.println("waiting for input");

                while (true) {
		    int command = inputStream.read();

		    if (command == EXIT_CMD)
                        {
			    System.out.println("finish process");
			    break;
                        }

		    processCommand(command);
                }
	    } catch (Exception e) {
                e.printStackTrace();
	    }
        }

    /**
     * Process the command from client
     * @param command the command code
     */
    private void processCommand(int command) {
	String cmd = "";
	try {
	    System.out.println("Command");
	    switch (command) {
	    case KEY_RIGHT:
		System.out.println("Right");
		cmd = "/home/pi/git/seniordesign/pi/serial/send_string.py 1";
		Runtime.getRuntime().exec(cmd);
		break;
	    case KEY_LEFT:
		System.out.println("Left");
		cmd = "/home/pi/git/seniordesign/pi/serial/send_string.py 0";
                Runtime.getRuntime().exec(cmd);
		break;
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}