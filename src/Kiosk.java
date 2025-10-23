import java.util.ArrayList;
import java.util.InputMismatchException;
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
    List<MenuItem> menuList = new ArrayList<>();

    //생성자
    public Kiosk(List<MenuItem> menuList) {
        this.menuList = menuList;
    }

    //기능
    void start () {

        Scanner scanner = new Scanner(System.in);

        String menuName;
        int menuPrice;
        String menuDescription;

        int menuIndex = 0;
        int enteredNum;

        //0을 입력받기 전까지 반복 처리
        while (true) {
            // 햄버거 메뉴 입력받기
            // 햄버거 메뉴는 햄버거 이름 / 가격 / 설명 으로 이루어져있다.
            // 가격은 원가격에서 1000을 나눈 값으로 명시한다(소수점 한자리까지 표기)
            try{
                System.out.print("메뉴를 입력해주세요 : ");
                menuName = scanner.nextLine();

                System.out.print("가격을 입력해주세요 : ");
                if(scanner.hasNextInt()){
                    menuPrice = scanner.nextInt();
                }else{
                    throw new InputMismatchException("error : 숫자만 입력해주세요");
                }
                scanner.nextLine();
                System.out.print("설명을 입력해주세요 : ");
                menuDescription = scanner.nextLine();
                System.out.println("\n");
            } catch(InputMismatchException e){
                System.out.println(e.getMessage());
                break;
            }

            //입력받은 메뉴 저장하기
            menuList.add(new MenuItem(menuName, menuPrice, menuDescription));

            //햄버거 메뉴 출력하기
            /**
             * [ SHAKESHACK MENU ]
             * 인덱스(메뉴 번호). 메뉴 이름   | W 햄버거 가격 | 설명
             * 0. 종료   | 종료
             * 의 형태로 출력한다.
             * 만약 0을 입력받을 시 프로그램을 종료합니다 문구가 뜨며 종료된다.
             */
            System.out.println("[ SHAKESHACK MENU ]");
            menuIndex = 0;
            for(MenuItem menu : menuList){
                menuIndex++;
                menu.printMenu(menuIndex);
            }
            System.out.println("0. 종료   | 종료");

            //번호 입력받기(0이 입력되면 종료)
            while (true) {
                System.out.print("선택할 메뉴의 번호를 입력해주세요 : ");
                if (scanner.hasNextInt()) {
                    enteredNum = scanner.nextInt();
                } else {
                    System.out.println("error : 메뉴 번호를 입력해주세요");
                    scanner.nextLine();
                    continue;
                }
                scanner.nextLine();
                if (enteredNum < 0 || enteredNum > menuList.size()) {        //재입력
                    System.out.println("error : 메뉴 번호를 입력해주세요");
                    continue;
                } else if (enteredNum == 0){                                 //프로그램 종료
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                } else{                                                      //다음 입력으로 넘어감
                    System.out.println("\n ----------------------------------------------------\n");
                    scanner.nextLine();
                    break;
                }
            }
        }
        scanner.close();
    }
}
