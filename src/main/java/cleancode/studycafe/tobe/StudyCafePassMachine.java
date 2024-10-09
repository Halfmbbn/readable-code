package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.io.input.studycafe.DataInputHandler;
import cleancode.studycafe.tobe.io.input.studycafe.FileInputHandler;
import cleancode.studycafe.tobe.io.input.user.ConsoleUserInputHandler;
import cleancode.studycafe.tobe.io.input.user.UserInputHandler;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePassType;
import cleancode.studycafe.tobe.model.StydyCafePasses;
import cleancode.studycafe.tobe.model.pass.StudyCafePass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudyCafePassMachine {
    // 화면을 출력하고, 입력을 받아서 결과를 보여주는 역할
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

        if (doesUserChooseHourly(studyCafePassType)) {

            List<StudyCafePass> studyCafePasses = dataInputHandler.readStudyCafePasses();

            List<StudyCafePass> hourlyPasses = studyCafePasses.stream()
                    .filter(studyCafePass -> studyCafePass.getPassType() == StudyCafePassType.HOURLY)
                    .toList();

            outputHandler.showPassListForSelection(hourlyPasses);

            StudyCafePass selectedPass = userInputHandler.getSelectPass(hourlyPasses);

            outputHandler.showPassOrderSummary(selectedPass, null);

        }
        if (doesUserChooseWeekly(studyCafePassType)) {
            List<StudyCafePass> studyCafePasses = dataInputHandler.readStudyCafePasses();
            List<StudyCafePass> weeklyPasses = studyCafePasses.stream()
                    .filter(studyCafePass -> studyCafePass.getPassType() == StudyCafePassType.WEEKLY)
                    .toList();
            outputHandler.showPassListForSelection(weeklyPasses);
            StudyCafePass selectedPass = userInputHandler.getSelectPass(weeklyPasses);
            outputHandler.showPassOrderSummary(selectedPass, null);
        }
        if (doesUserChooseFixed(studyCafePassType)) {
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

//        throw new AppException("잘못된 번호를 선택하셨습니다.");
    }

    private static boolean doesUserChooseFixed(StudyCafePassType studyCafePassType) {
        return studyCafePassType == StudyCafePassType.FIXED;
    }

    private static boolean doesUserChooseWeekly(StudyCafePassType studyCafePassType) {
        return studyCafePassType == StudyCafePassType.WEEKLY;
    }

    private static boolean doesUserChooseHourly(StudyCafePassType studyCafePassType) {
        return studyCafePassType == StudyCafePassType.HOURLY;
    }

}
