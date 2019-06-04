package lk.ijse.dep.Business.impl;

import lk.ijse.dep.Business.custom.CustomerBO;
import lk.ijse.dep.DAO.DAO.custom.CustomerDAO;
import lk.ijse.dep.DAO.DAO.custom.OrderDetailsDAO;
import lk.ijse.dep.DTO.CustomerDTO;
import lk.ijse.dep.Entities.Customer;
import lk.ijse.dep.Entities.OrderDetails;
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
public class CustomerBOImpl implements CustomerBO {

    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    OrderDetailsDAO od;
    @PersistenceContext
    private EntityManager session;

    public List<CustomerDTO> allCustomers() throws Exception {
        return customerDAO.findAll().stream().map(new Function<Customer, CustomerDTO>() {
            @Override
            public CustomerDTO apply(Customer customer) {
                return new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress());
            }
        }).collect(Collectors.toList());


        /*ArrayList<CustomerDTO> customerlist = new ArrayList<>();
        for (Customer customer:customerDAO.findAll()) {
            customerlist.add(new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress()));
        }
        return customerlist;*/
    }

    public void saveCustomer(CustomerDTO customer) throws Exception {
        Customer cusentity = new Customer(customer.getId(),customer.getName(),customer.getAddress());

        customerDAO.save(cusentity);
        
    }

    public void deleteCustomer(String id) throws Exception {
        customerDAO.deleteById(id);
    }

    public boolean customerExistsinOrder(String id) throws Exception {



        /*Stream<OrderDetails> orderDetailsStream = od.findAll().stream().filter(new Predicate<OrderDetails>() {
            @Override
            public boolean test(OrderDetails orderDetails) {
                if (orderDetails.getCusid().equals(id)) {
                    return true;
                }
                return false;
            }
        });*/

        List<OrderDetails> orderDetails = od.findAll();

        for (OrderDetails order:orderDetails) {
            if (order.getCusid().equals(id)){
                return true;
            }
        }
        return false;
    }

    public void updateCustomer(CustomerDTO customer) throws Exception {
        customerDAO.save(new Customer(customer.getId(), customer.getName(), customer.getAddress()));
    }

}
