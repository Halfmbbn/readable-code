package cleancode.studycafe.tobe.model.pass;

import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StudyCafeSeatPassTest {

    @DisplayName("이용권 유형이 다르면 서로 다른 이용권이다.")
    @Test
    void doesNotMatchPassType() {
        // given
        StudyCafePassType passType = StudyCafePassType.WEEKLY;
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 4, 1000, 0.0);

        // when
        // then
        assertThat(seatPass.isSamePassType(passType)).isFalse();
    }

    @DisplayName("고정석 이용권이면 사물함을 이용할 수 있다.")
    @Test
    void canUseLocker() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 12, 10000, 0.0);

        // when
        // then
        assertThat(seatPass.cannotUseLocker()).isFalse();
    }

    @DisplayName("고정석 이용권이 아니면 사물함을 이용할 수 없다.")
    @Test
    void cannotUseLocker() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 12, 10000, 0.0);

        // when
        // then
        assertThat(seatPass.cannotUseLocker()).isTrue();
    }

    @DisplayName("고정석 이용권이 사물함 이용권의 유형과 기간이 동일하다면 사물함을 이용할 수 있다.")
    @Test
    void isSameDurationType() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 12, 10000, 0.0);
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 12, 10000);

        // when
        // then
        assertThat(seatPass.isSameDurationType(lockerPass)).isTrue();
    }

    @DisplayName("고정석 이용권이 사물함 이용권의 유형과 기간이 같지 않다면 사물함을 이용할 수 없다.")
    @Test
    void doesNotMatchDurationType() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 12, 10000, 0.0);
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 4, 10000);

        // when
        // then
        assertThat(seatPass.isSameDurationType(lockerPass)).isFalse();
    }

    @DisplayName("할인율이 존재하면 할인 가격을 계산한다.")
    @Test
    void getDiscountPrice() {
        // given
        int price = 10000;
        double discountRate = 0.1;
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 12, price, discountRate);

        // when
        int discountPrice = seatPass.getDiscountPrice();

        // then
        assertThat(discountPrice).isEqualTo(1000);
    }
}