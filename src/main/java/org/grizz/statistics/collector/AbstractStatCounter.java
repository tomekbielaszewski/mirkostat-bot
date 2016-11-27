package org.grizz.statistics.collector;

import java.util.*;
import java.util.Map.Entry;

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
	
	protected Map.Entry<Object, Integer> getEntryOnPosition(int position) {
		Set<Map.Entry<Object, Integer>> entries = counter.entrySet();
		List<Map.Entry<Object, Integer>> sortedEntries = new ArrayList<>();
		
		Comparator<Map.Entry<Object, Integer>> entryComparator = new Comparator<Map.Entry<Object, Integer>>() {

			@Override
			public int compare(Entry<Object, Integer> o1,
					Entry<Object, Integer> o2) {
				return o2.getValue() - o1.getValue();
			}
		};
		sortedEntries.addAll(entries);
		Collections.sort(sortedEntries, entryComparator);

        if(sortedEntries.size() > position) {
            return sortedEntries.get(position);
        } else {
            HashMap<Object, Integer> map = new HashMap<Object, Integer>();
            map.put("",0);
            return map.entrySet().iterator().next();
        }
	}

    protected Map<Object, Integer> getCounter() {
        return counter;
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
