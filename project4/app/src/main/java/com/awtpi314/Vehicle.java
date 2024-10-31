package com.awtpi314;

public class Vehicle {
    private String name;
    private String heading;
    private int x;
    private int y;
    private int size;

    public Vehicle(String name, String heading, int size, int x, int y) {
        this.name = name;
        this.heading = heading;
        this.size = size;
        this.x = x;
        this.y = y;
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    public String getHeading() {
        return heading;
    }

    public int getSize() {
        return size;
    }
}
