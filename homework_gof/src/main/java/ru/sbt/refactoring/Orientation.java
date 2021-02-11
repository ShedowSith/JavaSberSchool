package ru.sbt.refactoring;

public enum Orientation {
    NORTH{
        public Orientation turn(){
            return EAST;
        }
        public Position move(Position p){
            return p.changeY(1);
        }
    }, WEST{
        public Orientation turn() {
            return NORTH;
        }
        public Position move(Position p) {
            return p.changeX(-1);
        }
    }, SOUTH{
        public Orientation turn() {
            return WEST;
        }
        public Position move(Position p) {
            return p.changeY(-1);
        }
    }, EAST{
        public Orientation turn() {
            return SOUTH;
        }
        public Position move(Position p) {
            return p.changeX(1);
        }
    };

    public abstract Position move(Position position);

    public abstract Orientation turn();
}
