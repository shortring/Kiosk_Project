public enum Discount {
    NATIONAL_MERIT(0.1),
    SOLDIER(0.05),
    STUDENT(0.03),
    NORMAL(0);

    // 속성
    final double discountValue;

    // 생성자
    Discount(double discountValue) {
        this.discountValue = discountValue;
    }
    // 기능
    double getDiscountValue() {
        return discountValue;
    }
}
