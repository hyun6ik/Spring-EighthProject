package springeighthproject.spring_jpa.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import springeighthproject.spring_jpa.domain.item.Book;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemUpdateTest {

    @Autowired
    EntityManager em;

    @Test
    public void updateTest() throws Exception {
        //given
        Book book = em.find(Book.class, 1L);
        //when
        book.setName("changeNameBook");
        //then
        //변경 감지 == dirty checking

    }
}
