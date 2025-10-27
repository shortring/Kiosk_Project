/**
 * 햄버거 메뉴는 MenuItem과 리스트로 관리한다
 * 1. 햄버거 메뉴는 이름 , 가격, 설명을 가진다
 */

public class MenuItem {
    // 속성
    private final String menuName;
    private final int menuPrice;
    private final String menuDescription;

    // 생성자
    MenuItem(String menuName, int menuPrice, String menuDescription) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuDescription = menuDescription;
    }

    // 기능
    String getMenuName() {
        return menuName;
    }

    int getMenuPrice() {
        return menuPrice;
    }

    String getMenuDescription() {
        return menuDescription;
    }
}
