package com.boothj5.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MapUtil {
    public static LinkedHashMap<String, Integer> sortHashMapByValues(Map<String, Integer> originalMap) {
        List<String> sortedKeys = new ArrayList<String>(originalMap.keySet());
        List<Integer> sortedValues = new ArrayList<Integer>(originalMap.values());
        Collections.sort(sortedValues);
        Collections.sort(sortedKeys);
            
        LinkedHashMap<String, Integer> newSortedMap = new LinkedHashMap<String, Integer>();
        
        for (Integer val : sortedValues) {
            for (String key : sortedKeys) {
                Integer valFromOriginalMap = originalMap.get(key);
                Integer valFromSortedValues = val;
                
                if (valFromOriginalMap.equals(valFromSortedValues)) {
                    originalMap.remove(key);
                    sortedKeys.remove(key);
                    newSortedMap.put(key, val);
                    break;
                }
            }
        }
        return newSortedMap;
    }
}
