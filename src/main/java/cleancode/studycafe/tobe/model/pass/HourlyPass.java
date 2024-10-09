package cleancode.studycafe.tobe.model.pass;

import cleancode.studycafe.tobe.model.StudyCafePassType;

public class HourlyPass implements Pass {

    private final StudyCafePassType passType;
    private final int duration;
    private final int price;
    private final double discountRate;

    private HourlyPass(StudyCafePassType passType, int duration, int price, double discountRate) {
        this.passType = passType;
        this.duration = duration;
        this.price = price;
        this.discountRate = discountRate;
    }

    public static HourlyPass of(StudyCafePassType passType, int duration, int price, double discountRate) {
        return new HourlyPass(passType, duration, price, discountRate);
    }

    public StudyCafePassType getPassType() {
        return passType;
    }

    public int getDuration() {
        return duration;
    }

    public int getPrice() {
        return price;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    // TODO: move to output handler
    public String display() {
        if (passType == StudyCafePassType.HOURLY) {
            return String.format("%s시간권 - %d원", duration, price);
        }
        if (passType == StudyCafePassType.WEEKLY) {
            return String.format("%s주권 - %d원", duration, price);
        }
        if (passType == StudyCafePassType.FIXED) {
            return String.format("%s주권 - %d원", duration, price);
        }
        return "";
    }

}
