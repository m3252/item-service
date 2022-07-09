package hello.itemservice.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clear();
    }

    @Test
    void save() {
        //given
        Item item = new Item("test", 1, 2);

        //when
        Item saved = itemRepository.save(item);

        //then
        Item find = itemRepository.findById(saved.getId());

        assertThat(find).isEqualTo(saved);
    }

    @Test
    void findAll() {
        //given
        Item itemA = new Item("testA", 1, 2);
        Item itemB = new Item("testB", 2, 3);

        itemRepository.save(itemA);
        itemRepository.save(itemB);

        //when
        List<Item> all = itemRepository.findAll();

        //then
        assertThat(all.size()).isEqualTo(2);
        assertThat(all).contains(itemA, itemB);
    }

    @Test
    void updateItem() {
        //given
        Item itemA = new Item("testA", 1, 2);
        Item saved = itemRepository.save(itemA);
        Long id = saved.getId();

        //when
        Item updateItem = new Item("수정!", 2000, 10);
        itemRepository.updateItem(id, updateItem);

        //then
        Item findItem = itemRepository.findById(id);
        assertThat(findItem.getItemName()).isEqualTo(updateItem.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateItem.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateItem.getQuantity());


    }
}