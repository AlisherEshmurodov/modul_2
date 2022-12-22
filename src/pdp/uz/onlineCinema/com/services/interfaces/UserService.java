package pdp.uz.onlineCinema.com.services.interfaces;

import pdp.uz.onlineCinema.com.model.User;

public interface UserService {
    void openUserConsole(User checkUser);
    void buyTicket(User checkUser);
    void boughtTicketsList(User checkUser);
}
