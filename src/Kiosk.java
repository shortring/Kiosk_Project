import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * 프로그램의 메뉴를 관리하고 사용자 입력을 처리하는 클래스
 * MenuItem을 관리하는 리스트 필요
 * 입력 반복 로직은 start함수에서 관리
 * menuList는 Kiosk클래스 생성자를 통해 값을 할당
 * 0을 입력하면 뒤로가기 하거나 프로그램이 종료
 */
public class Kiosk {
    // 속성
    private final Scanner scanner = new Scanner(System.in);
    private final MenuManager menuManager = new MenuManager();
    private final ShoppingBasket shoppingBasket = new ShoppingBasket();

    private Menu selectedMenu;
    private MenuItem selectedMenuItem;

    //생성자

    //기능
    //키오스크 실행
    public void start () {

        Integer selectedNumber;
        boolean haveOrderData = shoppingBasket.checkHaveOrders();
        boolean isCheckingOrder = false;

        //0을 입력받기 전까지 반복 처리
        MAINLABEL:
        while (true) {
            selectedNumber = null;
            // 1. 메뉴 리스트 출력하기(메인 메뉴 출력)
            System.out.println("\n\n[ MAIN MENU ]");
            int index = 0;
            for (Menu menu : menuManager.getMenuList()) {
                index++;
                System.out.printf("%d. %s\n", index, menu.getCategory());
            }
            System.out.println("0. 종료      | 종료");

            //장바구니에 데이터가 있는지 확인
            haveOrderData = shoppingBasket.checkHaveOrders();

            // 장바구니에 데이터가 있으면 데이터 확인 여부 파트 출력
            if (haveOrderData) {
                // 장바구니 입력
                System.out.println("\n[ ORDER MENU ]");
                System.out.println("4. Orders       | 장바구니를 확인 후 주문합니다.");
                System.out.println("5. Cancel       | 진행중인 주문을 취소합니다");
            }

            // 장바구니 확인 여부 입력
            while (haveOrderData) {
                selectedNumber = selectMenuNumber();

                // 장바구니 주문 확인 메세지 출력
                if (selectedNumber == 4) {
                    System.out.println("아래와 같이 주문 하시겠습니까?");
                    System.out.println("[ Orders ]");
                    // 장바구니에 담긴 상품 리스트 출력
                    shoppingBasket.getBasketDataList().stream()
                            .forEach(basketData -> {
                                System.out.printf("X%d %s | W %.1f | %s\n", basketData.getOrderCount(), basketData.getMenuItem().getMenuName(), (double) basketData.getMenuItem().getMenuPrice() / 1000, basketData.getMenuItem().getMenuDescription());
                            });

                    System.out.println("\n[ Total ]");
                    System.out.println("W " + shoppingBasket.getTotalPrice() / 1000);
                    System.out.println("1. 주문      2. 메뉴판");
                    isCheckingOrder = true;
                    break;
                } else if (selectedNumber == 5) {   // 장바구니 삭제 후 메인메뉴로 이동
                    System.out.println("장바구니에서 지울 상품의 이름을 입력해주세요");
                    shoppingBasket.getBasketDataList().stream()
                            .forEach(basketData -> {
                                System.out.printf("X%d %s | W %.1f | %s\n", basketData.getOrderCount(), basketData.getMenuItem().getMenuName(), (double) basketData.getMenuItem().getMenuPrice() / 1000, basketData.getMenuItem().getMenuDescription());
                            });
                    scanner.nextLine();
                    String removeMenuName;
                    while (true) {
                        try {
                            removeMenuName = scanner.nextLine();
                            shoppingBasket.removeBasketData(removeMenuName);
                            System.out.println(removeMenuName + "이 삭제되었습니다.");
                            continue MAINLABEL;
                        } catch (Exception e) {
                            System.out.println("error : 메뉴이름을 정확히 입력해주세요");
                        }
                    }
                } else if (selectedNumber >= 0 && selectedNumber < 4 ) {
                    // 메뉴 입력 파트로 이동
                    isCheckingOrder = false;
                    break;
                } else {
                    System.out.println("error : 번호를 정확히 입력해주세요");
                    continue;
                }
            }

            // 장바구니에 담은 상품 주문 여부 입력
            while (isCheckingOrder) {
                selectedNumber = selectMenuNumber();

                if (selectedNumber == 1) {  // 주문 완료, 장바구니 초기화 후 메인 메뉴로 이동
                    // 할인 정보 출력
                    System.out.println("할인 정보를 입력해주세요.");
                    System.out.println("1. 국가유공자 :\t" + (int)(100 * Discount.NATIONAL_MERIT.getDiscountValue()) + "%");
                    System.out.println("2. 군인 \t:\t" + (int)(100 * Discount.SOLDIER.getDiscountValue()) + "%");
                    System.out.println("3. 학생 \t:\t" + (int)(100 * Discount.STUDENT.getDiscountValue()) + "%");
                    System.out.println("4. 일반 \t:\t" + (int)(100 * Discount.NORMAL.getDiscountValue()) + "%");

                    // 할인 정보 입력
                    double discountPrice = selectAndDiscountPrice();
                    System.out.printf("주문이 완료되었습니다. 금액은 W %.2f 입니다.\n", discountPrice / 1000);
                    shoppingBasket.clearBasket();
                    isCheckingOrder = false;
                    continue MAINLABEL;
                } else if (selectedNumber == 2) {   //메인 메뉴로 이동
                    continue MAINLABEL;
                } else {    // 잘못된 입력, 재입력 처리
                    System.out.println("error : 번호를 정확히 입력해주세요");
                    continue;
                }
            }

            // 메뉴 입력받고 메뉴 아이템 리스트 출력
            while (true) {
                // 아무것도 입력받지않았다면 메뉴 입력받기
                if (selectedNumber == null) {
                    selectedNumber = selectMenuNumber();
                }

                if (selectedNumber == 0) {
                    // 프로그램 종료
                    System.exit(0);
                    break;
                } else if (selectedNumber > 0 && selectedNumber <= menuManager.getMenuList().size()) {
                    // 메뉴 아이템 리스트 출력
                    selectedMenu = menuManager.getMenuList()
                                                .get(selectedNumber - 1);
                    //printMenuItemList(selectedMenu);
                    System.out.println("\n\n[ SHAKESHACK MENU ]");

                    AtomicInteger menuIndex = new AtomicInteger(1);
                    selectedMenu.getMenuItemList()
                                .stream()
                                .forEach(menuItem -> {
                                    int mIndex = menuIndex.getAndIncrement();
                                    System.out.printf("%d. %s   | W %.1f | %s\n", mIndex, menuItem.getMenuName(), (double)menuItem.getMenuPrice() / 1000, menuItem.getMenuDescription());
                                });
                    System.out.println("0. 뒤로가기");
                    break;
                } else {
                    // 오류, 재입력
                    System.out.println("error : 번호를 정확히 입력해주세요");
                    selectedNumber = null;
                    continue;
                }
            }

            while (true) {
                // 메뉴 아이템 입력받기
                selectedNumber = selectMenuNumber();
                if (selectedNumber > 0 && selectedNumber <= selectedMenu.getMenuItemList().size()) {
                    // 선택한 메뉴 아이템 출력 후 장바구니 추가 여부 입력 파트로 넘어가기
                    selectedMenuItem = selectedMenu.getMenuItem(selectedNumber - 1);
                    System.out.printf("선택한 메뉴: %s | W %.1f | %s\n", selectedMenuItem.getMenuName(), (double)selectedMenuItem.getMenuPrice() / 1000, selectedMenuItem.getMenuDescription());
                    break;
                }
                if (selectedNumber == 0) {
                    // 뒤로가기
                    continue MAINLABEL;
                } else {
                    // 에러 출력 -> 재입력
                    System.out.println("error : 번호를 정확히 입력해주세요");
                    continue ;
                }
            }

            // 장바구니 추가 여부 질문 출력
            System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
            System.out.println("1. 확인\t\t2. 취소");

            // 장바구니 추가 여부 입력
            while (true) {
                selectedNumber = selectMenuNumber();

                if (selectedNumber == 1) {
                    // 장바구니에 상품 추가 후 메인 메뉴로 이동
                    shoppingBasket.addMenuItem(selectedMenuItem);

                    System.out.printf("%s 이 장바구니에 추가되었습니다.\n", selectedMenuItem.getMenuName());

                    break;
                } else if (selectedNumber == 2) {
                    // 메인메뉴로 이동
                    continue MAINLABEL;
                } else {
                    // 에러 출력 -> 재입력
                    System.out.println("error : 번호를 정확히 입력해주세요");
                    continue;
                }
            }
        }
    }

    // 메뉴 입력 받기 (에러 발생시 재입력)
    private int selectMenuNumber() {
        int selectedNum;

        while(true){
            try {
                selectedNum = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("error : 메뉴 번호를 정확히 입력해주세요");
            }
            scanner.nextLine(); //남은 버퍼 처리
        }
        return selectedNum;
    }

    // 메뉴 아이템 리스트 출력
    void printMenuItemList(Menu selectedMenu) {
        int menuIndex = 0;

        System.out.println("\n\n[ SHAKESHACK MENU ]");

        for (MenuItem menuItem : selectedMenu.getMenuItemList()) {
            menuIndex++;
            System.out.printf("%d. %s   | W %.1f | %s\n", menuIndex, menuItem.getMenuName(), (double)menuItem.getMenuPrice() / 1000, menuItem.getMenuDescription());
        }

        System.out.println("0. 뒤로가기");
    }

    // 할인 선택 및 할인된 금액 반환
    private double selectAndDiscountPrice() {
        double discountPrice = 0;
        Integer num = null;

        while (num == null) {
            num = selectMenuNumber();

            switch (num) {
                case 1:
                    discountPrice = shoppingBasket.getTotalPrice() * (1 - Discount.NATIONAL_MERIT.getDiscountValue());
                    break;
                case 2:
                    discountPrice = shoppingBasket.getTotalPrice() * (1 - Discount.SOLDIER.getDiscountValue());
                    break;
                case 3:
                    discountPrice = shoppingBasket.getTotalPrice() * (1 - Discount.STUDENT.getDiscountValue());
                    break;
                case 4:
                    discountPrice = shoppingBasket.getTotalPrice() * (1 - Discount.NORMAL.getDiscountValue());
                    break;
                default:
                    System.out.println("error : 메뉴 번호를 정확히 입력해주세요");
                    num = null;
                    break;
            }
        }

        return discountPrice;
    }
}