package ru.sbt.refactoring;

public class Main {
    public static void main(String[] args) {
        Tractor tractor = new Tractor();
        for(int i = 0; i<5; i++)
            tractor.move(Command.FORWARDS);
        tractor.printPosition();
        tractor.move(Command.TURN);
        tractor.printPosition();

    }
}
