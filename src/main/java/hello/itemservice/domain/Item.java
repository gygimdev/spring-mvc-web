package hello.itemservice.domain;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Item {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public Item() {

    }
}
