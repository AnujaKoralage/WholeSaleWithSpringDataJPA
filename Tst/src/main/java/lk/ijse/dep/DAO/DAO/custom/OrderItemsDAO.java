package lk.ijse.dep.DAO.DAO.custom;

import lk.ijse.dep.Entities.OrderItems;
import lk.ijse.dep.Entities.OrderItemsPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsDAO extends JpaRepository<OrderItems,OrderItemsPK> {


}
