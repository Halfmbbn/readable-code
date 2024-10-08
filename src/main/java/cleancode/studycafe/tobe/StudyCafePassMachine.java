package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.io.input.studycafe.DataInputHandler;
import cleancode.studycafe.tobe.io.input.studycafe.FileInputHandler;
import cleancode.studycafe.tobe.io.input.user.ConsoleUserInputHandler;
import cleancode.studycafe.tobe.io.input.user.UserInputHandler;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.List;

public class StudyCafePassMachine {

    private final DataInputHandler dataInputHandler = new FileInputHandler();
    private final UserInputHandler userInputHandler = new ConsoleUserInputHandler();
    private final OutputHandler outputHandler = new OutputHandler();

    public void run() {
        try {
            outputHandler.showWelcomeMessage();
            outputHandler.showAnnouncement();

            outputHandler.askPassTypeSelection();
            StudyCafePassType studyCafePassType = userInputHandler.getPassTypeSelectingUserAction();

            calculatePassOrder(studyCafePassType);
        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private void calculatePassOrder(StudyCafePassType studyCafePassType) {
        if (studyCafePassType == StudyCafePassType.HOURLY) {

            List<StudyCafePass> studyCafePasses = dataInputHandler.readStudyCafePasses();

            List<StudyCafePass> hourlyPasses = studyCafePasses.stream()
                    .filter(studyCafePass -> studyCafePass.getPassType() == StudyCafePassType.HOURLY)
                    .toList();

            outputHandler.showPassListForSelection(hourlyPasses);

            StudyCafePass selectedPass = userInputHandler.getSelectPass(hourlyPasses);

            outputHandler.showPassOrderSummary(selectedPass, null);

        } else if (studyCafePassType == StudyCafePassType.WEEKLY) {
            List<StudyCafePass> studyCafePasses = dataInputHandler.readStudyCafePasses();
            List<StudyCafePass> weeklyPasses = studyCafePasses.stream()
                    .filter(studyCafePass -> studyCafePass.getPassType() == StudyCafePassType.WEEKLY)
                    .toList();
            outputHandler.showPassListForSelection(weeklyPasses);
            StudyCafePass selectedPass = userInputHandler.getSelectPass(weeklyPasses);
            outputHandler.showPassOrderSummary(selectedPass, null);
        } else if (studyCafePassType == StudyCafePassType.FIXED) {
            List<StudyCafePass> studyCafePasses = dataInputHandler.readStudyCafePasses();
            List<StudyCafePass> fixedPasses = studyCafePasses.stream()
                    .filter(studyCafePass -> studyCafePass.getPassType() == StudyCafePassType.FIXED)
                    .toList();
            outputHandler.showPassListForSelection(fixedPasses);
            StudyCafePass selectedPass = userInputHandler.getSelectPass(fixedPasses);

            List<StudyCafeLockerPass> lockerPasses = dataInputHandler.readLockerPasses();
            StudyCafeLockerPass lockerPass = lockerPasses.stream()
                    .filter(option ->
                            option.getPassType() == selectedPass.getPassType()
                                    && option.getDuration() == selectedPass.getDuration()
                    )
                    .findFirst()
                    .orElse(null);

            boolean lockerSelection = false;
            if (lockerPass != null) {
                outputHandler.askLockerPass(lockerPass);
                lockerSelection = userInputHandler.getLockerSelection();
            }

            if (lockerSelection) {
                outputHandler.showPassOrderSummary(selectedPass, lockerPass);
            } else {
                outputHandler.showPassOrderSummary(selectedPass, null);
            }
        }
    }

}
