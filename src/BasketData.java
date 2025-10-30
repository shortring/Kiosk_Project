public class BasketData {
    // 속성
    private MenuItem menuItem;
    private String menuName;
    private int menuPrice;
    private int orderCount;

    // 생성자
    public BasketData(MenuItem menuItem) {
        this.menuItem = menuItem;
        this.menuName = menuItem.getMenuName();
        this.menuPrice = menuItem.getMenuPrice();
        this.orderCount = 1;
    }

    // 기능
    public MenuItem getMenuItem() {
        return menuItem;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void addOrderCount() {
        orderCount++;
    }

    public void minusOrderCount() {
        orderCount--;
    }
}
