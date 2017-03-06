package service;

import dao.BuyerDAO;
import dao.DialogDAO;
import dao.MessageDAO;
import dto.DialogDTO;
import dto.MessageDTO;
import entity.Buyer;
import entity.BuyerDialog;
import entity.Dialog;
import entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class MessageServiceImpl implements MessageService {

    @Autowired
    private DialogDAO dialogDAO;

    @Autowired
    private MessageDAO messageDAO;

    @Autowired
    private BuyerDAO buyerDAO;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(MessageDTO messageDTO, int recepientId) {
        final int senderId = messageDTO.getSenderId();
        Dialog dialog;
        Integer dialogId = dialogDAO.getIdBy(senderId, recepientId);

        if (dialogId != null){
            dialog = dialogDAO.getById(dialogId);
        } else {
            dialog = createDialog(senderId, recepientId);
        }
        Message message = new Message(messageDTO);
        dialog.addMessage(message);
        dialogDAO.save(dialog);
    }

    @Override
    public List<DialogDTO> listDialogDTOBy(int buyerId) {
        return dialogDAO.listDTOBy(buyerId);
    }

    @Override
    public List<MessageDTO> listMessageDTOBy(int dialogId) {
        return messageDAO.listDTOBy(dialogId);
    }

    private Dialog createDialog(int senderId, int recepientId){
        Dialog dialog = new Dialog();
        dialog.setDate(new Date());
        Buyer sender = buyerDAO.getById(senderId);
        Buyer recepient = buyerDAO.getById(recepientId);
        BuyerDialog buyerDialogForSender = new BuyerDialog();
        BuyerDialog buyerDialogForRecepient = new BuyerDialog();
        buyerDialogForSender.setDialog(dialog);
        buyerDialogForSender.setBuyer(sender);
        buyerDialogForSender.setEnabled(true);
        buyerDialogForSender.setReadAll(true);
        buyerDialogForRecepient.setDialog(dialog);
        buyerDialogForRecepient.setBuyer(recepient);
        buyerDialogForRecepient.setEnabled(true);
        buyerDialogForRecepient.setReadAll(true);
        sender.getBuyerDialogs().add(buyerDialogForSender);
        recepient.getBuyerDialogs().add(buyerDialogForRecepient);
        dialog.getBuyerDialogs().add(buyerDialogForSender);
        dialog.getBuyerDialogs().add(buyerDialogForRecepient);
        return dialog;
    }
}
