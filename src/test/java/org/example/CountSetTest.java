package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CountSetTest {

    private CountSet countSet;
    private CountSet otherSet;

    @BeforeEach
    void setUp() {
        Map<Integer, Integer> initialMap = new HashMap<>();
        initialMap.put(1, 1);
        initialMap.put(2, 2);
        initialMap.put(3, 3);
        countSet = new CountSet(initialMap);

        Map<Integer, Integer> otherMap = new HashMap<>();
        otherMap.put(1, 1);
        otherMap.put(2, 1);
        otherMap.put(4, 4);
        otherSet = new CountSet(otherMap);
    }

    @Test
    @DisplayName("Append를 통해 countSet에 요소를 추가할 수 있다.")
    public void testAppend() {
        CountSet updatedSet = countSet.append(1);
        assertEquals(2, updatedSet.countFor(1));

        updatedSet = countSet.append(4);
        assertEquals(1, updatedSet.countFor(4));
    }

    @Test
    @DisplayName("해당 요소 1개 제거")
    public void testRemove() {
        CountSet updatedSet = countSet.remove(1);
        assertEquals(0, updatedSet.countFor(1)); // 하나 있는 원소를 제거했을 때.

        updatedSet = countSet.remove(2);
        assertEquals(1, updatedSet.countFor(2)); // 두개 있는 원소를 제거했을 때.

        updatedSet = countSet.remove(2);
        updatedSet = updatedSet.remove(2);
        assertEquals(0, updatedSet.countFor(2)); // 한개 남은 원소를 두번 제거했을 때.
    }

    @Test
    @DisplayName("개수")
    public void testCountFor() {
        assertEquals(1, countSet.countFor(1));
        assertEquals(2, countSet.countFor(2));
        assertEquals(3, countSet.countFor(3));
    }

    @Test
    @DisplayName("합집합")
    public void testSum() {
        CountSet summedSet = countSet.sum(otherSet); // {1,2,2,3,3,3} + {1,2,4,4,4,4} + {1,1,2,2,2,3,3,3,4,4,4,4}

        assertEquals(2, summedSet.countFor(1));
        assertEquals(3, summedSet.countFor(2));
        assertEquals(4, summedSet.countFor(4));
    }

    @Test
    @DisplayName("차집합")
    public void testComplement() {
        CountSet complementSet = countSet.complement(otherSet);

        assertEquals(0, complementSet.countFor(1));
        assertEquals(1, complementSet.countFor(2));
        assertEquals(3, complementSet.countFor(3));
    }

    @Test
    @DisplayName("교집합 count는 1")
    public void testIntersect() {
        CountSet intersectSet = countSet.intersect(otherSet);

        assertEquals(1, intersectSet.countFor(1));
        assertEquals(1, intersectSet.countFor(2));
        assertEquals(0, intersectSet.countFor(3));
        assertEquals(0, intersectSet.countFor(4));
    }

    @Test
    public void testResultAll() {
        Map<Integer, Integer> resultMap = countSet.resultAll();
        assertEquals(1, resultMap.get(1));
        assertEquals(2, resultMap.get(2));
        assertEquals(3, resultMap.get(3));
        assertNull(resultMap.get(4));
    }
}
