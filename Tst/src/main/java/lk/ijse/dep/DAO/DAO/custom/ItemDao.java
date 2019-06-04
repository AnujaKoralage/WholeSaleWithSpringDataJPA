package lk.ijse.dep.DAO.DAO.custom;

import lk.ijse.dep.Entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemDao extends JpaRepository<Item,String> {
    @Query("select o.qty from Item o where o.code=:#{#code}")
    public int getQtyByCode(@Param("code") String itemcode);
}
