package com.example.adam.kyn_workshop_2016;

import java.util.HashMap;
import java.util.Map;

public class BeerItem {
    public static Map<String, Integer[]> beerMap;

    static {
        beerMap = new HashMap<>();
        beerMap.put("saison", new Integer[]{12,14});
        beerMap.put("apa", new Integer[]{9,11});
        beerMap.put("biale", new Integer[]{4,6});
        beerMap.put("marcowe", new Integer[]{6,8});
        beerMap.put("bock", new Integer[]{9,11});
        beerMap.put("porter", new Integer[]{8,12});
    }

}
