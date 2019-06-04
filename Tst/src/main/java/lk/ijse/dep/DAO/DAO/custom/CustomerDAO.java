package lk.ijse.dep.DAO.DAO.custom;

import lk.ijse.dep.Entities.Customer;
import javafx.collections.ObservableList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO extends JpaRepository<Customer,String> {
    @Query("SELECT c FROM Customer c")
    public List<Customer> getById() throws Exception;

//    public Customer findCustomerByOrderByAddressDesc();
//
//    public List<Customer> findCustomersByNameLike(String name);
//
//    @Query("SELECT c FROM Customer c WHERE c.name LIKE ?1")
//    public List<Customer> allCustomers(String name);
//
//    public List<Customer> findCustomersByNameLikeAndAddressLikeOrderByAddressDesc(String name,String address);
//
//    @Query("SELECT c from Customer c WHERE c.name LIKE :#{'%'+#name} AND c.address LIKE :#{'%'+#address} ORDER BY c.id DESC")
//    public List<Customer> all1Customers(@Param("name") String name, @Param("address") String address);

}
