package ru.nk.training;

public class TrappedWaterFinder {
    public int findTrappedWaterUnits(int[] elevationMap) {
        int index = 1, units = 0;
        while (index < elevationMap.length) {
            index = nextHill(elevationMap, index);
            int maxHeight = elevationMap[index - 1];
            Pit pit = countUnitsInPit(elevationMap, index, maxHeight);
            if (pit.rightHillIndex < elevationMap.length) {
                units += pit.units;
            }
            index = pit.rightHillIndex;
        }
        return units;
    }

    private int nextHill(int[] elevationMap, int from) {
        int index = from;
        while (index < elevationMap.length && elevationMap[index] >= elevationMap[index - 1]) {
            index++;
        }
        return index;
    }

    private Pit countUnitsInPit(int[] elevationMap, int from, int maxHeight) {
        int index = from, units = 0;
        while (index < elevationMap.length && elevationMap[index] < maxHeight) {
            units += maxHeight - elevationMap[index];
            index++;
        }
        return new Pit(from, index, units);
    }

    private static class Pit {
        public final int leftHillIndex;
        public final int rightHillIndex;
        public final int units;

        public Pit(int leftHillIndex, int rightHillIndex, int units) {
            this.leftHillIndex = leftHillIndex;
            this.rightHillIndex = rightHillIndex;
            this.units = units;
        }
    }
}
