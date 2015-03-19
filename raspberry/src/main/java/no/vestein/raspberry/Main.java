package no.vestein.raspberry;

import java.io.Console;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.RaspiPin;

public class Main {

	static final GpioController gpio = GpioFactory.getInstance();
	static LedMap leds = LedMap.getInstance();
	
	public static void main(String[] args) {
		
		leds.add("r", RaspiPin.GPIO_06);
		leds.add("g", RaspiPin.GPIO_05);
		
		Console console = System.console();
		
		while (true) {
			String[] input = console.readLine("Command: ").split(" ");
			
			if (input[0].equals("")) {
				break;
			}
			checkCommand(input);
		}
		
		gpio.shutdown();
		System.exit(0);
	}
	
	public static void checkCommand(String[] input) {
		try {
			Led led = getLed(input[0]);
			try {
				String command = input[1].toLowerCase();
				if (command.equals("low") || command.equals("l")) {
					led.low();
				} else if (command.equals("high") || command.equals("h")) {
					led.high();
				} else if (command.equals("blink") || command.equals("b")) {
					try {
						led.blink(Integer.parseInt(input[2]));
					} catch (ArrayIndexOutOfBoundsException e) {
						System.out.println("Invalid argument");
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Invalid command");
			}
		} catch (NullPointerException e) {
			System.out.println("Unknown LED");
		}
		
	}
	
	public static Led getLed(String key) {
		if (leds.getLed(key) != null) return leds.getLed(key);
		throw new NullPointerException();
	}
	
}
