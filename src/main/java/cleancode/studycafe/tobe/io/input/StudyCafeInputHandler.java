package cleancode.studycafe.tobe.io.input;

import cleancode.studycafe.tobe.io.input.studycafe.DataInputHandler;
import cleancode.studycafe.tobe.io.input.studycafe.FileInputHandler;
import cleancode.studycafe.tobe.io.input.user.ConsoleUserInputHandler;
import cleancode.studycafe.tobe.io.input.user.UserInputHandler;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.List;

public class StudyCafeInputHandler {

    private static final DataInputHandler studyCafeDataHandler = new FileInputHandler();
    private static final UserInputHandler userInputHandler = new ConsoleUserInputHandler();

    public StudyCafePassType getPassTypeSelectingUserAction() {
        return userInputHandler.getPassTypeSelectingUserAction();
    }

    public StudyCafePass getSelectPass(List<StudyCafePass> passes) {
        return userInputHandler.getSelectPass(passes);
    }

    public boolean getLockerSelection() {
        return userInputHandler.getLockerSelection();
    }

    public List<StudyCafePass> readStudyCafePasses() {
        return studyCafeDataHandler.readStudyCafePasses();
    }

    public List<StudyCafeLockerPass> readLockerPasses() {
        return studyCafeDataHandler.readLockerPasses();
    }

}
