package cleancode.minesweeper.tobe;

public enum GameStatus {

    IN_PROGRESS("진핼중"),
    WIN("승리"),
    LOSE("패배")
    ;

    private final String description;

    GameStatus(String description) {
        this.description = description;
    }
}
