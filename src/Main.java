/**
 * Main
 * 프로그램 실행 클래스
 * 1. 키오스크 실행
 *
 * Kiosk
 * 키오스크 입출력 담당 클래스
 * 1. start()로 키오스크 실행
 * 2. 0을 입력받기 전까지 반복 처리
 * 3. 상위 카테고리 메뉴를 출력하고 번호를 입력받을 것
 * 4. 번호를 입력받고 해당 번호의 카테고리 메뉴를 출력할 것
 * 5. 메뉴 번호를 입력받고 해당 메뉴를 출력할 것
 *
 * MenuManager
 * Menu를 관리하는 클래스
 * 1. 메뉴를 생성하고 설정할 수 있을 것
 * 2. 선택한 카테고리를 반환
 *
 * Menu
 * 카테고리 별 메뉴 관리 클래스
 * 1. 메뉴 별 카테고리 필드를 가질 것
 * 2. List<MenuItem>를 관리할 것
 *
 * MenuItem
 * 상품 정보 관리 클래스
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

        // 1. Kiosk.start()함수로 프로그램 실행
        Kiosk kiosk = new Kiosk();
        kiosk.start();
    }
}