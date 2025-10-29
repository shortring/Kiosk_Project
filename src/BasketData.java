public class BasketData {
    // 속성
    private MenuItem menuItem;
    private int orderCount;

    // 생성자
    public BasketData(MenuItem menuItem) {
        this.menuItem = menuItem;
        this.orderCount = 1;
    }

    // 기능
    public MenuItem getMenuItem() {
        return menuItem;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void addOrderCount() {
        orderCount++;
    }
}
