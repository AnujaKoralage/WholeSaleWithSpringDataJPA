package lk.ijse.dep.Business.impl;

import javafx.collections.FXCollections;
import lk.ijse.dep.Business.custom.OrderBO;
import lk.ijse.dep.DAO.DAO.custom.CustomerDAO;
import lk.ijse.dep.DAO.DAO.custom.ItemDao;
import lk.ijse.dep.DAO.DAO.custom.OrderDetailsDAO;
import lk.ijse.dep.DAO.DAO.custom.OrderItemsDAO;
import lk.ijse.dep.DTO.ItemDTO;
import lk.ijse.dep.DTO.OrderDetailsDTO;
import lk.ijse.dep.Entities.Customer;
import lk.ijse.dep.Entities.Item;
import lk.ijse.dep.Entities.OrderItems;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Transactional
public class OrderBOImpl implements OrderBO {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    OrderDetailsDAO orderDetailsDAO;
    @Autowired
    CustomerDAO customerDAO;
    @Autowired
    OrderItemsDAO dao;
    @Autowired
    ItemDao itemDAO;

public String qtyGetByCode(String itemcode){

    return String.valueOf(itemDAO.getQtyByCode(itemcode));
}

    public void insertOrderDetails(OrderDetailsDTO orderDetailsDTO) throws Exception {


        entityManager.getTransaction().begin();
        //dao.save(new OrderDetails(orderDetailsDTO.getOrderid(),orderDetailsDTO.getCusid(),orderDetailsDTO.getOrderdate()));
        entityManager.getTransaction().commit();

    }

    public void insertOrderItems(OrderDetailsDTO orderDetailsDTO) throws Exception {
        entityManager.getTransaction().begin();
        dao.save(new OrderItems(orderDetailsDTO.getOrderid(),orderDetailsDTO.getCusid(),orderDetailsDTO.getOrderdate()));
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public void updateItemQty(String qtyOnHand, String itemcode){

        entityManager.getTransaction().begin();
            itemDAO.getOne(itemcode).setQty(qtyOnHand);
        entityManager.getTransaction().commit();

    }

    public List<ItemDTO> allItems() throws Exception {


        return itemDAO.findAll().stream().map(new Function<Item, ItemDTO>() {
            @Override
            public ItemDTO apply(Item item) {
                return new ItemDTO(item.getCode(),item.getDescription(),item.getQty(),item.getPrice());
            }
        }).collect(Collectors.toList());

        /*List<Item> all = dao.findAll();

        List<ItemDTO> list  = new ArrayList<>();
        for (Item item:all) {
            list.add(new ItemDTO(item.getCode(),item.getDescription(),item.getQty(),item.getPrice()));
        }

        return list;*/
    }

    public ObservableList getAllCustomerId(ObservableList list) throws Exception {

        List<Customer> byId = customerDAO.getById();
        List<String> list1 = null;

        for (Customer customer:byId) {
            list1.add(customer.getId());
        }
        ObservableList<String> strings = FXCollections.observableArrayList(list1);

        return strings;
    }


}
