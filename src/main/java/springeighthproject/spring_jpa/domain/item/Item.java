package springeighthproject.spring_jpa.domain.item;

import lombok.Getter;
import lombok.Setter;
import springeighthproject.spring_jpa.domain.Category;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Item {
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    private List<Category> categories = new ArrayList<>();
}
