package pdp.uz.onlineCinema.com.services.interfaces;

import pdp.uz.onlineCinema.com.model.User;

public interface ManagerService {
    void openManagerConsole(User checkUser);
    void addMovie(User checkUser);
    void ticketList(User checkUser);
}
