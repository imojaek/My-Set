package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SquadSet {

    private final List<Integer> myList;

    public SquadSet(Integer[] input) {
        myList = Collections.unmodifiableList(Arrays.stream(input)
                .distinct()
                .collect(Collectors.toList()));
    }
    private void addOnlyUnique(List<Integer> list, Integer[] target) {
        for (Integer num : target) {
            if (!list.contains(num))
                list.add(num);
        }
    }

    public Integer[] sum(SquadSet other) {
        return Stream.concat(myList.stream(), other.myList.stream()) // 동일한 타입의 스트림을 합친다.
                .distinct()
                .toArray(Integer[]::new);
    }

    public Integer[] complement(SquadSet other) {
        return myList.stream()
                .filter(num -> !other.myList.contains(num))
                .toArray(Integer[]::new);
    }

    public Integer[] intersect(SquadSet other) {
        return myList.stream()
                .filter(num -> other.myList.contains(num)) // (other.myList::contains) 로 가능한데 이건 왜이리 안읽히는지..
                .toArray(Integer[]::new);
    }

    public Integer[] resultAll() {
        return myList.toArray(Integer[]::new);
    }
}
