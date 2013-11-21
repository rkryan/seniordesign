import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class blink {
    
    public static void main(String[] args) throws InterruptedException {
	
	// create gpio controller
	GpioController gpio = GpioFactory.getInstance();
        
	// provision gpio pin #01 as an output pin and turn off
	GpioPinDigitalOutput outputPin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "MyLED", PinState.LOW);
	while(true) {
	    // turn output to LOW/OFF state
	    outputPin.low();
	    
	    Thread.sleep(1000);
	    // turn output to HIGH/ON state
	    outputPin.high();
	    Thread.sleep(1000);
	}
    }
}

