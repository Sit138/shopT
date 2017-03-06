package service;

import dto.DialogDTO;
import dto.MessageDTO;

import java.util.List;

public interface MessageService {

    void save(MessageDTO messageDTO, int recepientId);

    List<DialogDTO> listDialogDTOBy(int buyerId);

    List<MessageDTO> listMessageDTOBy(int dialogId);

}
