package entity;

import dto.CommentDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity(name = "Comment")
@Table(name = "comment")
@Getter @Setter
public class Comment {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "message")
    @Size(min = 5, max = 300)
    private String message;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "product_id")
    private int productId;

    @Column(name = "buyer_name")
    private String buyerName;

    public Comment(CommentDTO commentDTO) {
        this.message = commentDTO.getMessage();
        this.creationDate = commentDTO.getCreationDate();
        this.productId = commentDTO.getProductId();
        this.buyerName = commentDTO.getBuyerName();
    }

    public Comment(){}
}
