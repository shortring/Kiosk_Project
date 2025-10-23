import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    /**
     * 1. 햄버거 메뉴 입력받기
     * 2. 입력받은 메뉴를 저장하기
     * 3. 입력받은 메뉴 출력하기
     * 4. (1 ~ 3)을 반복하다 특정 번호를 입력하면 프로그램을 종료한다.(이번엔 0을 입력하는 것으로 지정)
     */

    public static void main(String[] args) {
        List<MenuItem> menuList = new ArrayList<>();
        menuList.add(new MenuItem("ShackBurger", 6900, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        menuList.add(new MenuItem("SmokeShack", 8900, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        menuList.add(new MenuItem("Cheeseburger", 6900, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        menuList.add(new MenuItem("Hamburger", 5400, "비프패티를 기반으로 야채가 들어간 기본버거"));

        Kiosk kiosk = new Kiosk(menuList);
        kiosk.start();
    }
}