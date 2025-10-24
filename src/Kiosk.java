import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 프로그램의 메뉴를 관리하고 사용자 입력을 처리하는 클래스
 * MenuItem을 관리하는 리스트 필요
 * 입력 반복 로직은 start함수에서 관리
 * menuList는 Kiosk클래스 생성자를 통해 값을 할당?
 * 0을 입력하면 뒤로가기 하거나 프로그램이 종료
 */
public class Kiosk {
    // 속성
    List<Menu> menuList;

    Menu selectedMenu;
    MenuItem selectedMenuItem;

    //생성자
    Kiosk(List<Menu> menuList) {
        this.menuList = new ArrayList<>();
        this.menuList = menuList;
    }

    //기능
    void start () {

        Scanner scanner = new Scanner(System.in);

        int menuIndex = 0;

        //0을 입력받기 전까지 반복 처리
        while (true) {
            /**
             * 1. 카테고리 리스트 출력하기
             * 2. 상위 카테고리 번호 입력받기
             * 3. 선택받은 상위 카테고리에 해당하는 하위 메뉴를 출력
             * 4. 하위메뉴를 선택하면
             *    선택한 메뉴: 메뉴 이름 | W 금액 | 설명
             *    의 형태로 출력된다.
             */

            // 1. 카테고리 리스트 출력하기
            System.out.println("\n\n[ MAIN MENU ]");
            int index = 0;
            for (Category category : Category.values()) {
                index++;
                System.out.printf("%d. %s\n", index, category.getCategory());
            }
            System.out.println("0. 종료      | 종료");

            // 2. 상위 카테고리 번호 입력받기
            while(true){
                System.out.print("메뉴를 입력해주세요 : ");

                if (scanner.hasNextInt()) {
                    menuIndex = scanner.nextInt();

                    if (menuIndex < 0 || menuIndex > menuList.size()) {
                        System.out.println("error : 메뉴를 입력해 주세요");
                        scanner.nextLine(); //남은 버퍼 처리
                        continue;
                    }else if (menuIndex == 0) {
                        System.out.println("프로그램을 종료합니다.");
                        System.exit(0);
                        break;
                    }
                }else{
                    System.out.println("error : 메뉴를 입력해 주세요");
                    scanner.nextLine(); //남은 버퍼 처리
                    continue;
                }

                scanner.nextLine(); //남은 버퍼 처리
                break;
            }

            // 3. 선택받은 상위 카테고리에 해당하는 하위 메뉴를 출력
            for (Menu menu : menuList) {
                if (menu.getCategory().equals(Category.values()[menuIndex - 1])) {
                    selectedMenu = menu;
                }
            }
            selectedMenu.printMenuList();

            // 4. 하위메뉴를 선택
            //    메뉴를 선택하면
            //    선택한 메뉴: 메뉴 이름 | W 금액 | 설명
            //    의 형태로 출력된다.
            int selectedNum = 0;

            while (true) {
                System.out.print("메뉴를 선택해주세요 : ");

                if (scanner.hasNextInt()) {
                    selectedNum = scanner.nextInt();

                    if (selectedNum < 0 || selectedNum > menuList.size()) {
                        System.out.println("error : 메뉴 번호를 입력해 주세요");
                        scanner.nextLine(); //남은 버퍼 처리
                        continue;
                    }
                }else{
                    System.out.println("error : 메뉴 번호를 입력해 주세요");
                    continue;
                }
                break;
            }

            // 0을 입력하면 뒤로가기
            // 메뉴 번호를 입력하면 선택한 메뉴를 출력하기
            if(selectedNum == 0) {
                scanner.nextLine(); //남은 버퍼 처리
                continue;
            }else {
                selectedMenuItem = selectedMenu.getMenuItem(selectedNum - 1);
                System.out.printf("선택한 메뉴: %s | W %f | %s\n", selectedMenuItem.getMenuName(), (double)selectedMenuItem.getMenuPrice() / 1000, selectedMenuItem.getMenuDescription());
            }
        }
    }
}
