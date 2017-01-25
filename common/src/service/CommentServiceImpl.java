package service;

import dao.CommentDAO;
import dto.CommentDTO;
import entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.Pagination;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDAO commentDAO;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(CommentDTO commentDTO) {
        Comment comment = new Comment(commentDTO);
        commentDAO.save(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDTO> list(int productId, Pagination pagination) {
        return commentDAO.list(productId, pagination);
    }

    @Override
    @Transactional(readOnly = true)
    public int countComment(int productId) {
        return commentDAO.countComment(productId);
    }
}
