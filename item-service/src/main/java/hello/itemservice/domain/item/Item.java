package hello.itemservice.domain.item;

import lombok.Data;

/**
 * @Data 쓰면 getter, setter, toString 등 만들 필요 없음
 * 하지만 @Data는 핵심 도메인 모델 상황에서 위험함
 * 예측하지 못하게 동작할 수 있기 때문에
 *
 */
@Data
public class Item {

    // int 안 쓰고 Integer 쓰는 이유 : null까지 허용하기 위햐서
    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
