package lk.ijse.dep.DAO.DAO.custom;

import lk.ijse.dep.Entities.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderDetailsDAO extends JpaRepository<OrderDetails,String> {

}
