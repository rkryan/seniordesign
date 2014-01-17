import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class transmit {
    
    public static void main(String[] args) throws InterruptedException {

	// create gpio controller
	GpioController gpio = GpioFactory.getInstance();
        
	// receive control signal is in GPIO_05, or GPIO 25 on the board
	// transmit control signal is in GPIO_06, or GPIO 24 on the board
	GpioPinDigitalOutput receivePin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "MyPin", PinState.LOW);
	GpioPinDigitalOutput transmitPin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "MyPin", PinState.LOW);

	// disable receiving and enable transmitting
	receivePin.low();
	transmitPin.high();

    }
}
