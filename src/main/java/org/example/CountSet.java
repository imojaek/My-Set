package org.example;

import java.util.*;

public class CountSet {

    private final List<Integer> value;
    private final List<Integer> count;

    public CountSet(Map<Integer, Integer> input) {
        value = List.copyOf(input.keySet());
        count = List.copyOf(input.values());
    }

    public CountSet(List<Integer> value, List<Integer> count) {
        this.value = value;
        this.count = count;
    }

    public CountSet append(Integer element) {
        List<Integer> newValue = new ArrayList<>(value);
        List<Integer> newCount = new ArrayList<>(count);

        if(newValue.contains(element)) {
            int index = newValue.indexOf(element);
            newCount.set(index, newCount.get(index) + 1);
        }

        return new CountSet(Collections.unmodifiableList(newValue), Collections.unmodifiableList(newCount));
    }

    public CountSet remove(Integer element) {
        List<Integer> newValue = new ArrayList<>(value);
        List<Integer> newCount = new ArrayList<>(count);

        if(newValue.contains(element)) {
            int index = newValue.indexOf(element);
            newCount.set(index, newCount.get(index) - 1);
            if (newCount.get(index) - 1 <= 0) {
                newValue.remove(index);
                newCount.remove(index);
            }
        }

        return new CountSet(Collections.unmodifiableList(newValue), Collections.unmodifiableList(newCount));
    }

    public int countFor(Integer element) {
        if (value.contains(element)) {
            int index = value.indexOf(element);
            return count.get(index);
        }
        return 0;
    }

    public CountSet sum(CountSet other) {
        List<Integer> newValue = new ArrayList<>(value);
        List<Integer> newCount = new ArrayList<>(count);

        for (int i = 0; i < other.value.size(); i++) {
            if (newValue.contains(other.value.get(i))) {
                int index = newValue.indexOf(other.value.get(i));
                newCount.set(index, newCount.get(index) + other.count.get(i));
            } else {
                newValue.add(other.value.get(i));
                newCount.add(other.count.get(i));
            }
        }

        return new CountSet(Collections.unmodifiableList(newValue), Collections.unmodifiableList(newCount));
    }

    public CountSet complement(CountSet other) {
        List<Integer> newValue = new ArrayList<>();
        List<Integer> newCount = new ArrayList<>();

        for (int i = 0; i < other.value.size(); i++) {
            if (value.contains(other.value.get(i))) {
                int index = value.indexOf(other.value.get(i));
                int tmp = count.get(index) - other.count.get(i);
                if (tmp > 0) {
                    newValue.add(value.get(index));
                    newCount.add(tmp);
                }
            }
        }

        return new CountSet(Collections.unmodifiableList(newValue), Collections.unmodifiableList(newCount));
    }

    public CountSet intersect(CountSet other) {
        List<Integer> newValue = new ArrayList<>();
        List<Integer> newCount = new ArrayList<>();

        for (int i = 0; i < value.size(); i++) {
            if (other.value.contains(value.get(i))) {
                newValue.add(value.get(i));
                newCount.add(1);
            }
        }

        return new CountSet(Collections.unmodifiableList(newValue), Collections.unmodifiableList(newCount));
    }

    public Map<Integer, Integer> resultAll() {
        Map<Integer, Integer> result = new HashMap<>();
        for (int i = 0; i < value.size(); i++) {
            result.put(value.get(i), count.get(i));
        }
        return result;
    }
}
