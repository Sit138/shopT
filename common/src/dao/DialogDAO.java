package dao;

import dto.DialogDTO;
import entity.Dialog;

import java.util.List;

public interface DialogDAO {

    void save(Dialog dialog);

    Integer getIdBy(int senderId, int recepientId);

    Dialog getById(int id);

    List<DialogDTO> listBy(int buyerId);

}
