package controller;

import dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.BuyerService;
import service.MessageService;
import util.CurrentUser;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String users(Model model){
        model.addAttribute("buyerList", buyerService.list());
        return "users";
    }

    @RequestMapping(value = "/newMessage", method = RequestMethod.POST)
    public String newMessage(Model model,
                             @RequestParam("recipientId") int recipientId){
        model.addAttribute("buyerDTO", buyerService.getById(recipientId));
        model.addAttribute("messageDTO", new MessageDTO());
        return "newMessage";
    }

    @RequestMapping(name = "/sendMessage", method = RequestMethod.POST)
    public String sendMessage(Model model,
                              @Valid @ModelAttribute MessageDTO messageDTO,
                              BindingResult bindingResult,
                              @RequestParam("recipientId") int recipientId){
        if(bindingResult.hasErrors()){
            model.addAttribute("buyerDTO", buyerService.getById(recipientId));
            return "newMessage";
        }

        messageDTO.setSenderId(buyerService.getDTOByName(CurrentUser.getCurrentUserName()).getId());

        messageService.save(messageDTO, recipientId);

        return "redirect:/profile";
    }

    @RequestMapping(value = "/dialogs", method = RequestMethod.GET)
    public String dialogs(Model model){
        model.addAttribute("dialogs", messageService.listDialogDTOBy(
                buyerService.getDTOByName(CurrentUser.getCurrentUserName()).getId()));
        return "dialogs";
    }

    @RequestMapping(value = "/message")
    public String message(Model model,
                          @RequestParam("interlocutorName") String interlocutorName,
                          @RequestParam("dialogId") int dialogId){
        model.addAttribute("interlocutorName", interlocutorName);
        model.addAttribute("dialogId", dialogId);
        model.addAttribute("messages", messageService.listMessageDTOBy(dialogId));
        return "message";
    }
}
