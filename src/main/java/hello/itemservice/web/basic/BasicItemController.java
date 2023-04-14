package hello.itemservice.web.basic;

import hello.itemservice.domain.Item;
import hello.itemservice.domain.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor //  <- LomBok 기능을 사용하면 final 로 되어 있는 애들로 생성자를 만들어준다.
public class BasicItemController {

    private final ItemRepository itemRepository;

//    @Autowired // 생성자가 1개일 경우 생략가능하다.
//    public BasicItemController(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }

    /**
     * 상품 목록
     * @param model
     * @return
     */
    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }


    /**
     * 상품 상세
     * @param itemId
     * @param model
     * @return
     */
    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    /**
     * 상품 등록 폼
     * @return
     */
    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

//    @PostMapping("/add")
//    public String saveItemV1(
//            @RequestParam String itemName,
//            @RequestParam int price,
//            @RequestParam int quantity,
//            Model model) {
//
//        Item item = new Item();
//        item.setItemName(itemName);
//        item.setPrice(price);
//        item.setQuantity(quantity);
//
//        itemRepository.save(item);
//
//        model.addAttribute("item", item);
//        return "basic/item";
//    }

//    @PostMapping("/add") // ModelAttribute 사용한방법, 이름을 이렇게 "item" 명시해주면 Model 에 들어간다
//    public String addItemV2(@ModelAttribute("item") Item item, Model model) {
//        itemRepository.save(item);
//        model.addAttribute("item", item); 생략가능, 자동추가
//        return "basic/item";
//    }

//    @PostMapping("/add")
//    public String addItemV3(@ModelAttribute Item item, Model model) {
//        itemRepository.save(item);
////        model.addAttribute("item", item); 생략가능, 자동추가
//        return "basic/item";
//    }

    @PostMapping("/add")
    public String addItemV4(Item item, Model model) {
        itemRepository.save(item);
//        model.addAttribute("item", item); 생략가능, 자동추가
        return "basic/item";
    }


    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model){
        Item findItem = itemRepository.findById(itemId);
        model.addAttribute("item", findItem);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(
            @PathVariable String itemName,
            @PathVariable int price,
            @PathVariable int quantity,
            Model model){
        Item findItem = itemRepository.findById(itemId);
        model.addAttribute("item", findItem);
        return "basic/editForm";
    }

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }

}
