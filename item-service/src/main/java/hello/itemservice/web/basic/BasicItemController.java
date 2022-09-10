package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * RequiredArgumentConstructor : final의 Constructor를 만들어줌
 */
@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    // 같은 URL 이지만 매서드로 기능 구분함
    //@PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam Integer quantity,
                       Model model) {

        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item", item);

        return "basic/item";
    }

    // ModelAttribute 쓰면 알아서 모델 다 만들어줌
    //@PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item) {

        itemRepository.save(item);
        // 자동 추가되므로 생략 가능함
        // model.addAttribute("item", item);

        return "basic/item";
    }

    /**
     * modelAttribute에 파라미터 생략하면,
     * 클래스명 첫글자를 소문자로 바꿈
     * 여기서, 클래스명이 Item이니까 item이 ModelAttribute에 담김
     */
    //@PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item, Model model) {

        itemRepository.save(item);
        // 자동 추가되므로 생략 가능함
        // model.addAttribute("item", item);

        return "basic/item";
    }

    // 파라미터가 객체면, @ModelAttribute도 생략 가능함
    //@PostMapping("/add")
    public String addItemV4(Item item) {
        itemRepository.save(item);
        return "basic/item";
    }

    // 새로고침 > 요청 중복 > PRG 패턴 적용
    //@PostMapping("/add")
    public String addItemV5(Item item) {
        itemRepository.save(item);
        return "redirect:/basic/items/" + item.getId();
    }

    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        // return 경로에서 itemId가 redirectAttributes에 넣은 값으로 치환돼서 들어감
        // 그러면 status는? > ?query 파라미터로 들어가게 됨
        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "/basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }
}
