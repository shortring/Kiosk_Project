import java.util.ArrayList;
import java.util.List;

public class ShoppingBasket {
    // 속성
    private List<BasketData> basketDataList = new ArrayList<>();
    boolean haveOrderData;

    // 생성자

    // 기능
    // 장바구니 비었는지 체크
    public boolean checkHaveOrders() {
        haveOrderData = !basketDataList.isEmpty();

        return haveOrderData;
    }

    // 장바구니에서 BaksetData 리스트 조회
    public List<BasketData> getBasketDataList() {
        return basketDataList;
    }

    // 장바구니에 저장된 물건 총 금액 조회
    public double getTotalPrice() {
        double totalPrice = 0;

        for (BasketData basketData : basketDataList) {
            int basketDataPrice = basketData.getMenuItem()
                                            .getMenuPrice();
            totalPrice += basketDataPrice * basketData.getOrderCount();
        }

        return totalPrice;
    }

    // 장바구니 비우기
    public void clearBasket() {
        basketDataList.clear();
    }

    // 장바구니 상품 선택 삭제
    public void removeBasketData(String menuName) {
        // 장바구니에서 지울 상품 선택
        BasketData basketData = basketDataList.stream()
                                            .filter(data -> data.getMenuName()
                                                                        .equalsIgnoreCase(menuName))
                                            .findFirst()
                                            .get();
        // 상품 삭제
        if(basketData.getOrderCount() > 1) {
            basketData.minusOrderCount();
        } else {
            basketDataList.remove(basketData);
        }
    }

    // 장바구니에 물건 추가하기
    public void addMenuItem(MenuItem menuItem) {
        boolean dataExist = false;

        //기존에 추가했던 물건이면 물건 갯수를 추가
        for (BasketData basketData : basketDataList) {
            String basketMenuName = basketData.getMenuItem()
                                            .getMenuName();

            if (menuItem.getMenuName().equals(basketMenuName)) {
                basketData.addOrderCount();
                dataExist = true;
                break;
            }
        }

        //기존에 추가한 적 없는 물건이면 물건 추가
        if (!dataExist) {
            this.basketDataList.add(new BasketData(menuItem));
        }
    }
}
