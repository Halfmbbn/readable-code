package cleancode.minesweeper.tobe.cell;

public interface Cell {

    void open();

    void flag();

    boolean isOpened();

    boolean isLandMine();

    boolean isChecked();

    boolean hasLandMineCount();

    CellSnapshot getSnapshot();

}