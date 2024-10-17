package cleancode.studycafe.tobe.model.pass;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StudyCafePassTypeTest {

    @DisplayName("고정석 유형은 사물함을 이용할 수 있다.")
    @Test
    void isLockerType() {
        // given
        StudyCafePassType passType = StudyCafePassType.FIXED;

        // when
        // then
        assertThat(passType.isLockerType()).isTrue();
    }

    @DisplayName("고정석 유형이 아니면 사물함을 이용할 수 없다.")
    @Test
    void isNotLockerType() {
        // given
        StudyCafePassType passType = StudyCafePassType.WEEKLY;

        // when
        // then
        assertThat(passType.isNotLockerType()).isTrue();
    }

}