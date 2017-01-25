package dao;

import dto.CommentDTO;
import entity.Comment;
import util.Pagination;
import java.util.List;

public interface CommentDAO {

    void save(Comment comment);

    List<CommentDTO> list(int productId, Pagination pagination);

    int countComment(int productId);

}
