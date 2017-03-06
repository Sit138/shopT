package dao;

import dto.DialogDTO;
import entity.Dialog;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BooleanType;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DialogDAOImpl implements DialogDAO {

    private SessionFactory sessionFactory;

    public DialogDAOImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(Dialog dialog) {
        getSession().save(dialog);
    }

    @Override
    public Integer getIdBy(int senderId, int recepientId) {
        return (Integer) getSession()
                .createSQLQuery("SELECT b1.dialog_id " +
                        "FROM buyer_dialog b1, buyer_dialog b2 " +
                        "WHERE b1.dialog_id = b2.dialog_id " +
                        "AND b1.buyer_id = :senderId " +
                        "AND b2.buyer_id = :recepientId")
                .setParameter("senderId", senderId)
                .setParameter("recepientId", recepientId)
                .uniqueResult();
    }

    @Override
    public Dialog getById(int id) {
        return getSession().get(Dialog.class, id);
    }

    @Override
    public List<DialogDTO> listDTOBy(int buyerId) {
        return getSession()
                .createSQLQuery("SELECT lastMessage.dialogId AS dialogId, " +
                        "       lastMessage.lastMessage AS lastMessageId, " +
                        "       lastMessage.senderId AS lastSenderId, " +
                        "       lastMessage.senderName AS lastSenderName, " +
                        "       lastMessage.text AS lastText, " +
                        "       lastMessage.messageDate AS messageDate, " +
                        "       b_d.enabled AS enabled, " +
                        "       b_d.readall AS readAll, " +
                        "       b_d.buyer_id AS currentUserId, " +
                        "       (SELECT b.name FROM buyer b " +
                        "         RIGHT OUTER JOIN " +
                        "         (SELECT bd.buyer_id FROM dialog d " +
                        "           LEFT OUTER JOIN buyer_dialog bd ON d.id = bd.dialog_id " +
                        "         WHERE d.id = lastMessage.dialogId AND bd.buyer_id != b_d.buyer_id) recId " +
                        "           ON b.id = recId.buyer_id) AS interlocutorName " +
                        "FROM buyer_dialog b_d " +
                        "  LEFT OUTER JOIN " +
                        "  (SELECT  lastMessage.dId AS dialogId, " +
                        "           lastMessage.lastMessage AS lastMessage, " +
                        "           lastMessage.senderId AS senderId, " +
                        "           lastMessage.text AS text, " +
                        "           lastMessage.messageDate AS messageDate, " +
                        "           b.name AS senderName " +
                        "   FROM buyer b " +
                        "     RIGHT OUTER JOIN " +
                        "     (SELECT lastMes.dialogId AS dId, " +
                        "             lastMes.lastMessage AS lastMessage, " +
                        "             mes.text AS text, " +
                        "             mes.sender_id AS senderId, " +
                        "             mes.date AS messageDate " +
                        "      FROM message mes " +
                        "        RIGHT OUTER JOIN " +
                        "        (SELECT m.dialog_id AS dialogId, " +
                        "                max(m.id) AS lastMessage " +
                        "         FROM message m " +
                        "         GROUP BY dialogId) lastMes ON lastMes.dialogId = mes.dialog_id " +
                        "      WHERE mes.dialog_id = lastMes.dialogId AND mes.id = lastMes.lastMessage) lastMessage ON lastMessage.senderId = b.id " +
                        "   WHERE b.id = lastMessage.senderId) lastMessage ON b_d.dialog_id = lastMessage.dialogId " +
                        "WHERE b_d.buyer_id = :buyerId")
                .addScalar("dialogId", IntegerType.INSTANCE)
                .addScalar("lastMessageId", IntegerType.INSTANCE)
                .addScalar("lastSenderId", IntegerType.INSTANCE)
                .addScalar("lastSenderName", StringType.INSTANCE)
                .addScalar("lastText", StringType.INSTANCE)
                .addScalar("messageDate", DateType.INSTANCE)
                .addScalar("enabled", BooleanType.INSTANCE)
                .addScalar("readAll", BooleanType.INSTANCE)
                .addScalar("currentUserId", IntegerType.INSTANCE)
                .addScalar("interlocutorName", StringType.INSTANCE)
                .setParameter("buyerId", buyerId)
                .setResultTransformer(Transformers.aliasToBean(DialogDTO.class))
                .list();
    }
}
