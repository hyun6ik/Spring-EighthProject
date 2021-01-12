package springeighthproject.spring_jpa.service;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import springeighthproject.spring_jpa.domain.Address;
import springeighthproject.spring_jpa.domain.Member;
import springeighthproject.spring_jpa.domain.Order;
import springeighthproject.spring_jpa.domain.OrderStatus;
import springeighthproject.spring_jpa.domain.exception.NotEnoughStockException;
import springeighthproject.spring_jpa.domain.item.Book;
import springeighthproject.spring_jpa.domain.item.Item;
import springeighthproject.spring_jpa.repository.OrderRepository;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired EntityManager em;
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception {
        //given
        Member member = createMember();

        Item book = createBook("시골 JPA", 10, 10000);

        int orderCount = 2;
        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        //then
        Order getOrder = orderRepository.findOne(orderId);
        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, getOrder.getStatus());
        assertEquals("주문한 상품 종류 수가 정확해야 한다.", 1, getOrder.getOrderItems().size());
        assertEquals("주문 가격은 가격 X 수량이다.", 10000, getOrder.getTotalPrice());
        assertEquals("주문 수량 만큼 재고가 줄어야 한다.", 8, book.getStockQuantity());

    }

    private Item createBook(String name, int stockQuantity, int price) {
        Item book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울","양천구","07949"));
        em.persist(member);
        return member;
    }

    @Test(expected = NotEnoughStockException.class)
    public void 상품주문_재고수량초과() throws Exception {
        //given
        Member member = createMember();
        Item item = createBook("시골 JPA", 10, 10000);

        int orderCount =11;
        //when
        orderService.order(member.getId(), item.getId(), orderCount);
        //then
        fail("재고 수량 부족 예외가 발생해야 한다.");
    }
    
    @Test
    public void 주문취소() throws Exception {
        //given
        Member member = createMember();
        Item book = createBook("시골 JPA", 10, 10000);
        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        //when
        orderService.cancelOrder(orderId);
        //then
        Order getOrder = orderRepository.findOne(orderId);
        assertEquals("주문 취소시 상태는 CANCEL 이어야 한다.", OrderStatus.CANCEL, getOrder.getStatus());
        assertEquals("주문이 취소된 상품은 그만큼 재고가 증가해야 한다.", 10, book.getStockQuantity());
    }


}