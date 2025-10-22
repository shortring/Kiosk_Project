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
        Scanner scanner = new Scanner(System.in);

        String menuName = "";
        int menuPrice = 0;
        String menuDescription = "";

        List<String> menuList = new ArrayList<>();

        int menuIndex = 0;
        int enteredNum;

        //0을 입력받기 전까지 반복 처리
        while(true){
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
                    throw new IllegalArgumentException();
                }
                scanner.nextLine();
                System.out.print("설명을 입력해주세요 : ");
                menuDescription = scanner.nextLine();
                System.out.println("\n");
            } catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
                break;
            }

            //입력받은 메뉴 저장하기
            menuList.add(String.format("%s   | W %.1f | %s", menuName, (double)menuPrice / 1000, menuDescription));

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
            for(String menu : menuList){
                menuIndex++;
                System.out.printf("%d. %s\n", menuIndex, menu);
            }
            System.out.println("0. 종료   | 종료");

            //번호 입력받기(0이 입력되면 종료)
            while(true){
                System.out.print("선택할 메뉴의 번호를 입력해주세요 : ");
                if(scanner.hasNextInt()){
                    enteredNum = scanner.nextInt();
                }else{
                    System.out.println("error : 메뉴 번호를 입력해주세요");
                    continue;
                }

                if(enteredNum < 0 || enteredNum > menuList.size()) {        //재입력
                    System.out.println("error : 메뉴 번호를 입력해주세요");
                    continue;
                }else if(enteredNum == 0){                                  //프로그램 종료
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                }else{                                                      //다음 입력으로 넘어감
                    System.out.println("\n ----------------------------------------------------\n");
                    scanner.nextLine();
                    break;
                }
            }
        }
    }
}