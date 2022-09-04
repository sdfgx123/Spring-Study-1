package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Repository : @Component의 대상이 됨
 */
@Repository
public class ItemRepository {

    /**
     * 실무에서 멀티스레드로 접근할 때는 HashMap 쓰면 안됨
     * ConcurrentHashMap 써야 함
     * long도 쓰면 안되고 다른 거 써야 함
     */
    private static final Map<Long, Item> store = new HashMap<>();
    private static long sequence = 0L;

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    // HashMap 데이터 다 날림
    public void clearStore() {
        store.clear();
    }
}
