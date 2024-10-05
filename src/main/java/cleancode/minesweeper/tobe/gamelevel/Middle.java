package cleancode.minesweeper.tobe.gamelevel;

public class Middle implements GameLevel {

    @Override
    public int getColSize() {
        return 18;
    }

    @Override
    public int getLandMineCount() {
        return 40;
    }

    @Override
    public int getRowSize() {
        return 14;
    }

}
