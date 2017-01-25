package dto;

import entity.Buyer;
import entity.Product;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
public class CommentDTO {

    private int id;

    private String message;

    private Date creationDate;

    private int productId;

    private String buyerName;

}
