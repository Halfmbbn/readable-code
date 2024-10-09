package cleancode.studycafe.tobe.io.input.user;

import cleancode.studycafe.tobe.model.pass.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.List;

public interface UserInputHandler {

    StudyCafePassType getPassTypeSelectingUserAction();

    StudyCafePass getSelectPass(List<StudyCafePass> passes);

    boolean getLockerSelection();

}