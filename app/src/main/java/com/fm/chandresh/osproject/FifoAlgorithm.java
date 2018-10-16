package com.fm.chandresh.osproject;

public class FifoAlgorithm {

    private int frameSize;
    private int[] array;
    private int top, hit, fault;

    public FifoAlgorithm(int frameSize) {
        this.frameSize = frameSize;
        array = new int[frameSize];
        top = 0;
        hit = 0;
        fault = 0;

        for (int i = 0; i < frameSize; i++)
            array[i] = -1;
    }

    public int getHit() {
        return hit;
    }

    public int getFault() {
        return fault;
    }

    public String print() {
        StringBuilder builder = new StringBuilder();
        for (int i : array)
            builder.append(i + " ");
        return builder.toString();
    }

    boolean isHit(int n) {
        for (int i : array)
            if (i == n) {
                hit++;
                return true;
            }

        return false;
    }

    public void insert(int n) {
        if (!isHit(n)) {
            array[top] = n;
            top = (top + 1) % frameSize;
            fault++;
        }
    }
}
