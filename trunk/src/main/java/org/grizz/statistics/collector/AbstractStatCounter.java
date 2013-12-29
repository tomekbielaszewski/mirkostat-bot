package org.grizz.statistics.collector;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractStatCounter implements StatCollector {
	private static Map.Entry<Object, Integer> EMPTY_ENTRY;
	
	static {
		Map<Object, Integer> mapWithEmptyEntry = new HashMap<>();
		mapWithEmptyEntry.put("", 0);
		for (Map.Entry<Object, Integer> entry : mapWithEmptyEntry.entrySet()) {
			EMPTY_ENTRY = entry;
		}
	}
	
	private Map<Object, Integer> counter = new HashMap<>();

	@Override
	public void reset() {
		counter.clear();
	}
	
	protected void addToCounter(Object obj, int amount) {
		updateMap(obj, amount, counter);
	}
	
	protected Map.Entry<Object, Integer> getEntryWithHighestCount() {
		Map.Entry<Object, Integer> entryWithHighestRateSoFar = null;
		
		for (Map.Entry<Object, Integer> entryCount : counter.entrySet()) {
			if(entryWithHighestRateSoFar == null ||
				entryCount.getValue() > entryWithHighestRateSoFar.getValue()) {
				entryWithHighestRateSoFar = entryCount;
			}
		}
		
		if(entryWithHighestRateSoFar == null) {
			entryWithHighestRateSoFar = EMPTY_ENTRY;
		}
		
		return entryWithHighestRateSoFar;
	}

	private void updateMap(Object obj, Integer value, Map<Object, Integer> map){
		if(map.containsKey(obj)) {
			Integer i = map.get(obj);
			i += value;
			map.put(obj, i);
		} else {
			map.put(obj, value);
		}
	}
}
