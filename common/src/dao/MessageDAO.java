package dao;

import dto.MessageDTO;

import java.util.List;

public interface MessageDAO {

    List<MessageDTO> listDTOBy(int dialogId);

}
