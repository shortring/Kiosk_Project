import java.util.List;

/**
 * MenuItem 클래스를 관리하는 클래스
 * 1. 메뉴 별 카테고리 필드를 가질 것
 * 2. List<MenuItem>를 관리할 것
 */
public class Menu {
    // 속성
    List<MenuItem> menuItemList;
    Category category;

    // 생성자
    public Menu(List<MenuItem> menuList, Category category) {
        this.menuItemList = menuList;
        this.category = category;
    }

    // 기능
    // 카테고리 하위 메뉴 출력
    void printMenuList() {
        int menuIndex = 0;

        System.out.println("\n\n[ SHAKESHACK MENU ]");

        for (MenuItem menuItem : menuItemList) {
            menuIndex++;
            System.out.printf("%d. %s   | W %.1f | %s\n", menuIndex, menuItem.getMenuName(), (double)menuItem.getMenuPrice() / 1000, menuItem.getMenuDescription());
        }

        System.out.println("0. 뒤로가기");
    }

    // 카테고리 Getter
    Category getCategory() {
        return category;
    }

    // 입력 받은 메뉴를 출력
    // 선택한 메뉴: 메뉴 이름 | W 금액 | 설명 의 형태로 출력
    MenuItem getMenuItem(int index) {
        return menuItemList.get(index);
    }
}