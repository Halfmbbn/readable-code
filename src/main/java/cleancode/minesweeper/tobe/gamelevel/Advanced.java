package cleancode.minesweeper.tobe.gamelevel;

public class Advanced implements GameLevel {

    @Override
    public int getColSize() {
        return 24;
    }

    @Override
    public int getLandMineCount() {
        return 99;
    }

    @Override
    public int getRowSize() {
        return 20;
    }

}
