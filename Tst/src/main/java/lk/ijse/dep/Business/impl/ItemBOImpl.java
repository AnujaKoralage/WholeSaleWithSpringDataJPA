package lk.ijse.dep.Business.impl;

import lk.ijse.dep.Business.custom.ItemBO;
import lk.ijse.dep.DAO.DAO.custom.ItemDao;
import lk.ijse.dep.DAO.DAO.custom.OrderItemsDAO;
import lk.ijse.dep.DTO.ItemDTO;
import lk.ijse.dep.Entities.Item;
import lk.ijse.dep.Entities.OrderItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
@Component
@Transactional
public class ItemBOImpl implements ItemBO {
    @Autowired
    private ItemDao itemDAO;
    @Autowired
    private OrderItemsDAO orderDetailsDAO;

    @PersistenceContext
private EntityManager session;

    public List<ItemDTO> getAllItems() throws Exception {

        return itemDAO.findAll().stream().map(new Function<Item, ItemDTO>() {
            @Override
            public ItemDTO apply(Item item) {
                return new ItemDTO(item.getCode(),item.getDescription(),item.getQty(),item.getPrice());
            }
        }).collect(Collectors.toList());

        /*List<Item> items = itemDAO.findAll();
        ArrayList<ItemDTO> list = new ArrayList<>();

        for (Item item:items) {
            list.add(new ItemDTO(item.getCode(),item.getDescription(),item.getQty(),item.getPrice()));
        }

        return list;*/
    }

    public void saveItem(ItemDTO itemDTO) throws Exception {
        itemDAO.save(new Item(itemDTO.getCode(), itemDTO.getDescription(), itemDTO.getQty(), itemDTO.getPrice()));

    }

    public void deleteItem(String id) throws Exception {
        itemDAO.deleteById(id);

    }

    public boolean itemExistsinOrder(String id) throws Exception {
        List<OrderItems> orderItems = orderDetailsDAO.findAll();

        for (OrderItems orderitem:orderItems) {
            if (orderitem.getOrderItemsPK().getItemcode().equals(id)){
                return true;
            }
        }
        return false;
    }

    public void updateItem(ItemDTO item) throws Exception {
        itemDAO.save(new Item(item.getCode(), item.getDescription(), item.getQty(), item.getPrice()));
    }

}
