package cn.lollipop.designpattern.composite;

import java.util.ArrayList;

class Branch extends Corp {
    private final ArrayList<Corp> subordinates = new ArrayList<>();

    public Branch(String name, String position, int salary) {
        super(name, position, salary);
    }

    public void addSubordinate(Corp corp) {
        subordinates.add(corp);
    }

    public ArrayList<Corp> getSubordinates() {
        return subordinates;
    }
}