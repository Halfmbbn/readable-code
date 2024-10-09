package cleancode.studycafe.tobe.io.input.studycafe;

import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.pass.StudyCafePass;

import java.util.List;

public interface DataInputHandler {

    List<StudyCafePass> readStudyCafePasses();

    List<StudyCafeLockerPass> readLockerPasses();

}
