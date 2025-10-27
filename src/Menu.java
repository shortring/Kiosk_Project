import java.util.List;

/**
 * MenuItem 클래스를 관리하는 클래스
 * 1. 메뉴 별 카테고리 필드를 가질 것
 * 2. List<MenuItem>를 관리할 것
 */
public class Menu {
    // 속성
    private final List<MenuItem> menuItemList;
    private final Category category;

    // 생성자
    public Menu(Category category, List<MenuItem> menuItemList) {
        this.menuItemList = menuItemList;
        this.category = category;
    }

    // 기능
    // 카테고리 Getter
    Category getCategory() {
        return category;
    }

    // MenuItem리스트 Getter
    List<MenuItem> getMenuItemList() {
        return menuItemList;
    }

    // 입력 받은 메뉴를 출력
    // 선택한 메뉴: 메뉴 이름 | W 금액 | 설명 의 형태로 출력
    MenuItem getMenuItem(int index) {
        return menuItemList.get(index);
    }
}