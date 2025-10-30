import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Menu를 관리하는 클래스
 */
public class MenuManager {
    // 속성
    private final List<Menu> menuList = new ArrayList<>();    //category는 겹치면 안되는데 hashmap으로는 안되려나

    // 생성자
    // 메뉴 미리 생성하여 넣기
    public MenuManager() {
        // field can be converted to a local variable 경고 발생에 대한 수정
        // 모든 곳에서 전역적으로 사용하는 것이 아닌 특정 메서드에서만 사용하는 변수를 필드로 꺼낼 시 메모리를 더 잡아먹을수있다고 함
        // 근데 이런 데이터는 메모리를 좀 먹게한다해도 생성자에 넣으면 안되는거 아닌가 싶기도하고

        Menu hamberMenu = new Menu(Category.BURGERS,
                Arrays.asList(
                        new MenuItem("ShackBurger", 6900, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"),
                        new MenuItem("SmokeShack", 8900, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"),
                        new MenuItem("Cheeseburger", 6900, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"),
                        new MenuItem("Hamburger", 5400, "비프패티를 기반으로 야채가 들어간 기본버거")
                )
        );

        Menu drinkMenu = new Menu(Category.DRINKS,
                Arrays.asList(
                        new MenuItem("Cola", 2500, "콜라"),
                        new MenuItem("Zero Cola", 3500, "제로콜라"),
                        new MenuItem("Sidar", 2800, "사이다"),
                        new MenuItem("Hwanta", 3000, "환타")
                )
        );

        Menu dessertMenu = new Menu(Category.DESSERTS,
                Arrays.asList(
                        new MenuItem("Chocolate", 4900, "초콜렛 세트"),
                        new MenuItem("Cake", 8900, "조각 케이크"),
                        new MenuItem("Pondue", 12000, "퐁듀"),
                        new MenuItem("Rice cake", 5400, "떡")
                )
        );
        menuList.add(hamberMenu);
        menuList.add(drinkMenu);
        menuList.add(dessertMenu);
    }

    // 기능
    // 카테고리 리스트 반환
    public List<Menu> getMenuList() {
        return menuList;
    }

    // 선택한 카테고리의 하위 메뉴 반환
    public Menu getMenu(Category category) {        //네이밍 신경쓰기
        return menuList.stream().filter(menu -> menu.getCategory()
                                                        .equals(category))
                                                        .findFirst()
                                                        .get();
    }
}