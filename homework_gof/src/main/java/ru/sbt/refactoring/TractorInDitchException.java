package ru.sbt.refactoring;

public class TractorInDitchException extends RuntimeException{
    public TractorInDitchException() {
        super("Трактор вне поля.");
    }
}
