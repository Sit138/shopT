package dao;

import dto.MessageDTO;
import entity.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "messageDAO")
public class MessageDAOImpl extends GeneralDAOImpl<Message> implements MessageDAO {

    /*private SessionFactory sessionFactory;

    public MessageDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }*/


    @Override
    public List<MessageDTO> listDTOBy(int dialogId) {
        return getSession()
            .createQuery("select m.id as id, " +
                                "m.senderId as senderId, " +
                                "m.text as text, " +
                                "m.date as date " +
                        "from Message m where m.dialog.id = :dialogId")
            .setParameter("dialogId", dialogId)
            .setResultTransformer(Transformers.aliasToBean(MessageDTO.class))
            .list();
    }
}
