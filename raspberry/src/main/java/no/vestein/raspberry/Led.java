package no.vestein.raspberry;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;

public class Led {
	
	private GpioController gpio = GpioFactory.getInstance();
	private GpioPinDigitalOutput led;
	
	public Led(Pin pin, PinState defaultPinState) {
		led = gpio.provisionDigitalOutputPin(pin, defaultPinState);
		led.setShutdownOptions(true, defaultPinState);
	}
	
	public void low() {
		led.blink(0);
		led.low();
	}
	
	public void high() {
		led.blink(0);
		led.high();
	}
	
	public void blink(int millis) {
		led.blink(millis);
	}

}
