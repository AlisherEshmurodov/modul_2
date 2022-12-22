package pdp.uz.onlineCinema.com;

import pdp.uz.onlineCinema.com.model.Role;
import pdp.uz.onlineCinema.com.model.User;
import pdp.uz.onlineCinema.com.services.impls.IMainMenu;

public class Application {

    public static void main(String[] args) {

        User user = new User(User.getCurrentId(), "admin", "admin", "1", Role.ADMIN, 0);
        Storage.users.add(user);
        IMainMenu.getInstance().start();


    }
}
