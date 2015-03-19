package no.vestein.raspberry;

import java.util.HashMap;
import java.util.Map;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;

public class LedMap {
	
	private static LedMap instance;
	private Map<String, Led> map = new HashMap<String, Led>();
	
	public static LedMap getInstance() {
		if (instance == null) instance = new LedMap();
		return instance;
	}
	
	private LedMap() {
		
	}
	
	public void add(String key, Pin pin) {
		if (! map.containsKey(key)) {
			Led led = new Led(pin, PinState.LOW);
			map.put(key, led);
		}
	}
	
	public void remove(String key) {
		map.remove(key);
	}
	
	public Led getLed(String key) {
		return map.get(key);
	}
	
	public Map<String, Led> getMap() {
		return map;
	}

}
