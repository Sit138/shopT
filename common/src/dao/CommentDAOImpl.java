package dao;

import dto.CommentDTO;
import entity.Comment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import util.Pagination;

import java.util.List;

@Repository(value = "commentDAO")
public class CommentDAOImpl extends GeneralDAOImpl<Comment> implements CommentDAO {

    /*private SessionFactory sessionFactory;

    public CommentDAOImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(Comment comment) {
        getSession().save(comment);
    }*/

    @Override
    public List<CommentDTO> list(int productId, Pagination pagination) {
        return getSession()
                .createQuery("select c.id as id, c.buyerName as buyerName, c.productId as productId, " +
                        "c.creationDate as creationDate, c.message as message from Comment c where c.productId = :productId")
                .setParameter("productId", productId)
                .setResultTransformer(Transformers.aliasToBean(CommentDTO.class))
                .setFirstResult(pagination.getNumberFirstSamplingElement())
                .setMaxResults(pagination.getNumberRowsOnPage())
                .list();
    }

    @Override
    public int countComment(int productId) {
        return Math.toIntExact((Long) getSession()
                .createCriteria(Comment.class)
                .add(Restrictions.eq("productId", productId))
                .setProjection(Projections.rowCount())
                .uniqueResult());
    }
}
