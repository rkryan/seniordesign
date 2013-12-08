package bluetooth;

/** Bluetooth server running on the Raspberry Pi.  Reads an int command from an input stream
 *  and calls a python script to write that int (converted to a byte) to a UART serial port.
 */
public class RemoteBluetoothServer {
    
    public static void main(String[] args) {
		Thread waitThread = new Thread(new WaitThread());
		waitThread.start();
    }
    
}
