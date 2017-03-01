package service;

import dao.BuyerDAO;
import dao.DialogDAO;
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
@Transactional
public class MessageServiceImpl implements MessageService {

    @Autowired
    private DialogDAO dialogDAO;

    @Autowired
    private BuyerDAO buyerDAO;

    @Override
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
    @Transactional(readOnly = true)
    public List<DialogDTO> listBy(int buyerId) {
        return dialogDAO.listBy(buyerId);
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
        buyerDialogForRecepient.setDialog(dialog);
        buyerDialogForRecepient.setBuyer(recepient);
        sender.getBuyerDialogs().add(buyerDialogForSender);
        recepient.getBuyerDialogs().add(buyerDialogForRecepient);
        dialog.getBuyerDialogs().add(buyerDialogForSender);
        dialog.getBuyerDialogs().add(buyerDialogForRecepient);
        return dialog;
    }
}
