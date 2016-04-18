package com.example.adam.kyn_workshop_2016;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeaconItem {
    private String name;
    private String id;
    private static Map<String, String> beaconMap;

    static {
        beaconMap = new HashMap<>();
        beaconMap.put("33927f15e3b75104", "fridge");
        beaconMap.put("66fc8e73f145e172", "door");
        beaconMap.put("0929888a57bf4fb7", "bag");
        beaconMap.put("3f2ac3b188b1adfc", "bike");
        beaconMap.put("0a296db82040d52d", "chair");
        beaconMap.put("bbe08f1308d5093f", "car");
        beaconMap.put("84b70d36fd22d5a6", "generic");
        beaconMap.put("5085ab2174e6ffff", "bed");
        beaconMap.put("bb6820d988547674", "dog");
        beaconMap.put("389a2f206e537f96", "shoe");
    }


    private BeaconItem(String name, String id){
        this.name = name;
        this.id = id;
    }

    public static ArrayList<BeaconItem> fromList(List<String> idList){
        ArrayList<BeaconItem> beaconList = new ArrayList<>();
        for (String id : idList){
            beaconList.add(new BeaconItem(beaconMap.get(id), id));
        }

        return beaconList;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
