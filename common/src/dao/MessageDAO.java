package dao;

import dto.MessageDTO;
import entity.Message;

import java.util.List;

public interface MessageDAO extends GeneralDAO<Message> {

    List<MessageDTO> listDTOBy(int dialogId);

}
