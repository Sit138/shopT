package service;

import dto.CommentDTO;
import util.Pagination;

import java.util.List;

public interface CommentService {

    void save(CommentDTO commentDTO);

    List<CommentDTO> list(int productId, Pagination pagination);

    int countComment(int productId);

}
