package springeighthproject.spring_jpa.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import springeighthproject.spring_jpa.domain.Order;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order){
        em.persist(order);
    }

    public Order findOne(Long id){
        return em.find(Order.class, id);
    }

//    public List<Order> findAll(OrderSearch orderSearch){
//
//    }
}
