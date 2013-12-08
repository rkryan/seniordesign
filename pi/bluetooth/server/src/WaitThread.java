package bluetooth;

import java.io.IOException;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;

/** Thread that waits for a Stream Connection, waits for a device to connect,
 *  and calls a class that receives and interprets the commands.
 */
public class WaitThread implements Runnable {
    
    public WaitThread() {
    }
    
    public void run() {
		waitForConnection();
    }
    
    private void waitForConnection() {
		LocalDevice local = null;
		
		StreamConnectionNotifier notifier;
		StreamConnection connection = null;
	
		try {
			local = LocalDevice.getLocalDevice();
			local.setDiscoverable(DiscoveryAgent.GIAC);
			UUID uuid = new UUID(80087355);
			String url = "btspp://localhost:" + uuid.toString() + ";name=RemoteBluetooth";
			notifier = (StreamConnectionNotifier)Connector.open(url);
		} catch(Exception ex) {
			ex.printStackTrace();
			return;
		}
	
		while(true) {
			try {
				System.out.println("Waiting for connection...");
				connection = notifier.acceptAndOpen();
				Thread processThread = new Thread(new ProcessConnectionThread(connection));
				processThread.start();
			} catch (Exception ex) {
				ex.printStackTrace();
				return;
			}
		}
    }
}
