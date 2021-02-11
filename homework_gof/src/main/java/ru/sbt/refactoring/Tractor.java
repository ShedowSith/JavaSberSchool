package ru.sbt.refactoring;

public class Tractor {
    private Position position = new Position(0, 0);
    private Field field= new Field(5,5);
    private Orientation orientation = Orientation.NORTH;

    public void move(Command command) {
        if (command == Command.FORWARDS) {
            moveForwards();
        } else if (command == Command.TURN) {
            turnClockwise();
        }
    }

    private void moveForwards() {
        position = orientation.move(position);
        if (position.getX() > field.getA() || position.getY() > field.getB()) {
            throw new TractorInDitchException();
        }
    }

    private void turnClockwise() {
        orientation = orientation.turn();
    }
    public void printPosition(){
        System.out.println(String.format("Трактор находится в позиции (%d, %d) и повернут в направлении "+ orientation, position.getX(), position.getY()));
    }

}
