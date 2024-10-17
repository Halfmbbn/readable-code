package cleancode.studycafe.tobe.model.pass.locker;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StudyCafeLockerPassesTest {

    @DisplayName("고정석 이용권과 유형, 기간이 동일한 사물함 이용권이 존재하면 해당 사물함 이용권을 반환한다.")
    @Test
    void findLockerPassBy() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 12, 10000, 0.1);
        StudyCafeLockerPasses lockerPasses = StudyCafeLockerPasses.of(List.of(
                StudyCafeLockerPass.of(StudyCafePassType.FIXED, 4, 5000),
                StudyCafeLockerPass.of(StudyCafePassType.FIXED, 12, 10000)
        ));

        // when
        Optional<StudyCafeLockerPass> optionalLockerPass = lockerPasses.findLockerPassBy(seatPass);

        // then
        assertThat(optionalLockerPass).isPresent();
    }

    @DisplayName("고정석 이용권과 유형, 기간이 동일한 사물함 이용권이 존재하지않으면 사물함 이용권을 찾을 수 없다.")
    @Test
    void notFoundLockerPassBy() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 1, 10000, 0.1);
        StudyCafeLockerPasses lockerPasses = StudyCafeLockerPasses.of(List.of(
                StudyCafeLockerPass.of(StudyCafePassType.FIXED, 4, 5000),
                StudyCafeLockerPass.of(StudyCafePassType.FIXED, 12, 10000)
        ));

        // when
        Optional<StudyCafeLockerPass> optionalLockerPass = lockerPasses.findLockerPassBy(seatPass);

        // then
        assertThat(optionalLockerPass).isEmpty();
    }
}