package cleancode.studycafe.tobe.model;

import cleancode.studycafe.tobe.model.pass.StudyCafePass;

import java.util.ArrayList;
import java.util.List;

public class StydyCafePasses {

    private final List<StudyCafePass> passes;

    private StydyCafePasses(List<StudyCafePass> passes) {
        this.passes = passes;
    }

    public static StydyCafePasses of(List<StudyCafePass> passes) {
        return new StydyCafePasses(passes);
    }

    public List<StudyCafePass> findAllPassBy(StudyCafePassType passType) {
        List<StudyCafePass> passList = new ArrayList<>(passes);
        return passList.stream()
                .filter(pass -> pass.getPassType() == passType)
                .toList();

    }

}
