/**
 * 햄버거 메뉴는 MenuItem과 리스트로 관리한다
 * 1. 햄버거 메뉴는 이름 , 가격, 설명을 가진다
 */
import java.util.List;

public class MenuItem {
    // 속성
    String menuName;
    int menuPrice;
    String menuDescription;

    // 생성자
    MenuItem(String menuName, int menuPrice, String menuDescription) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuDescription = menuDescription;
    }

    // 기능
    void printMenu(int menuIndex) {
        System.out.printf("%d. %s   | W %.1f | %s\n", menuIndex, menuName, (double)menuPrice / 1000, menuDescription);
    }
}
