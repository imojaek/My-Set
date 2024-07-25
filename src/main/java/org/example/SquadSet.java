package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SquadSet {

    private final List<Integer> myList;

    public SquadSet(Integer[] input) {
        List<Integer> tempList = new ArrayList<>();
        addOnlyUnique(tempList, input);
        myList = Collections.unmodifiableList(tempList);
    }
    private void addOnlyUnique(List<Integer> list, Integer[] target) {
        for (Integer num : target) {
            if (!list.contains(num))
                list.add(num);
        }
    }

    public Integer[] sum(SquadSet other) {
        return sum(other.resultAll());
    }


    private Integer[] sum(Integer[] other) {
        List<Integer> result = new ArrayList<>(myList);
        addOnlyUnique(result, other);
        return result.toArray(Integer[]::new);
    }

    public Integer[] resultAll() {
        return myList.toArray(Integer[]::new);
    }
}
