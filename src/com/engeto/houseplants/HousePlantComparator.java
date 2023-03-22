package com.engeto.houseplants;
import java.util.Comparator;

public class HousePlantComparator implements Comparator<HousePlant> {
    @Override
    public int compare(HousePlant o1, HousePlant o2) {
        return o1.getLastWatering().compareTo(o2.getLastWatering());
    }
}
