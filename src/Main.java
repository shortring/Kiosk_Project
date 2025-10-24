import java.util.ArrayList;
import java.util.List;

/**
 * Main
 * 1. Menu 객체 생성
 * 2. Kiosk 객체 생성 및 호출
 *
 * Menu
 * 1. 메뉴 별 카테고리 필드를 가질 것
 * 2. List<MenuItem>를 관리할 것
 *
 * Kiosk
 * 1. start 함수에서 키오스크의 입출력을 담당할 것
 * 2. 0을 입력받기 전까지 반복 처리
 * 3. 상위 카테고리 메뉴를 출력하고 번호를 입력받을 것
 * 4. 번호를 입력받고 해당 번호의 카테고리 메뉴를 출력할 것
 * 5. 메뉴 번호를 입력받고 해당 메뉴를 출력할 것
 *
 * MenuItem
 * 1. 카테고리?, 메뉴 이름, 금액, 설명 필드를 가진다.
 */

public class Main {

    /**
     * 1. 카테고리별로 List<MenuItem> 생성
     * 2. 카테고리별로 Menu 객체 생성
     * 3. Kiosk 객체 생성 및 호출
     * 4. Kiosk.start()함수로 프로그램 실행
     */

    public static void main(String[] args) {

        // 1. 카테고리별로 List<MenuItem> 생성
        List<MenuItem> hamburgerList = new ArrayList<>();
        List<MenuItem> drinksList = new ArrayList<>();
        List<MenuItem> dessertList = new ArrayList<>();

        hamburgerList.add(new MenuItem("ShackBurger", 6900, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        hamburgerList.add(new MenuItem("SmokeShack", 8900, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        hamburgerList.add(new MenuItem("Cheeseburger", 6900, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        hamburgerList.add(new MenuItem("Hamburger", 5400, "비프패티를 기반으로 야채가 들어간 기본버거"));

        drinksList.add(new MenuItem("Cola", 2500, "콜라"));
        drinksList.add(new MenuItem("Zero Cola", 3500, "제로콜라"));
        drinksList.add(new MenuItem("Sidar", 2800, "사이다"));
        drinksList.add(new MenuItem("Hwanta", 3000, "환타"));

        dessertList.add(new MenuItem("Chocolate", 4900, "초콜렛 세트"));
        dessertList.add(new MenuItem("Cake", 8900, "조각 케이크"));
        dessertList.add(new MenuItem("Pondue", 12000, "퐁듀"));
        dessertList.add(new MenuItem("Rice cake", 5400, "떡"));

        // 2. Menu 객체 생성
        Menu hamberMenu = new Menu(hamburgerList, Category.BURGERS);
        Menu drinkMenu = new Menu(drinksList, Category.DRINKS);
        Menu dessertMenu = new Menu(dessertList, Category.DESSERTS);

        List<Menu> menuList = new ArrayList<>();
        menuList.add(hamberMenu);
        menuList.add(drinkMenu);
        menuList.add(dessertMenu);

        //3. Kiosk 객체 생성 및 호출
        Kiosk kiosk = new Kiosk(menuList);

        // 4. Kiosk.start()함수로 프로그램 실행
        kiosk.start();
    }
}