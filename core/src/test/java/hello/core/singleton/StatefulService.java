package hello.core.singleton;

public class StatefulService {

    // private int price; // 상태를 유지하는 필드 : 10,000원 -> 20,000원으로 바뀌어버림

    // 주문과 관련된 함수
    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        // this.price = price; // 여기가 문제!
        return price;
    }

//    public int getPrice() {
//        // return price;
//    }
}
