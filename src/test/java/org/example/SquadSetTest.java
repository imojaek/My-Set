package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class SquadSetTest {

    private SquadSet set1;
    private SquadSet set2;

    @BeforeEach
    void setUp() {
        this.set1 = new SquadSet(new Integer[]{1, 2, 3});
        this.set2 = new SquadSet(new Integer[]{1, 3, 4});
    }

    @Test
    @DisplayName("SquadSet 간의 합집합을 반환받을 수 있다.")
    void sumTest() {
        Integer[] result = set1.sum(set2);
        assertArrayEquals(new Integer[]{1, 2, 3, 4}, result);
    }

    @Test
    @DisplayName("SquadSet 간의 차집합을 구할 수 있다.")
    void complementTest() {
        Integer[] result = set1.complement(set2);
        assertArrayEquals(new Integer[]{2}, result);
    }

    @Test
    @DisplayName("SquadSet 간의 교집합을 구할 수 있다.")
    void intersectionTest() {
        Integer[] result = set1.intersect(set2);
        assertArrayEquals(new Integer[]{1, 3}, result);
    }

    @Test
    @DisplayName("SquadSet이 갖고 있는 리스트를 배열형태로 반환받을 수 있다.")
    void resultAllTest() {
        Integer[] result1 = set1.resultAll();
        assertArrayEquals(new Integer[]{1, 2, 3}, result1);
        Integer[] result2 = set2.resultAll();
        assertArrayEquals(new Integer[]{1, 3, 4}, result2);
    }
}