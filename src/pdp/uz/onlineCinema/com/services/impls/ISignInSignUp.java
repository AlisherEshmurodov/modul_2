package pdp.uz.onlineCinema.com.services.impls;

import pdp.uz.onlineCinema.com.Storage;
import pdp.uz.onlineCinema.com.model.Role;
import pdp.uz.onlineCinema.com.model.User;
import pdp.uz.onlineCinema.com.services.interfaces.MainMenu;
import pdp.uz.onlineCinema.com.services.interfaces.SignInSignUp;

import java.util.Scanner;

public class ISignInSignUp implements SignInSignUp {

    private static SignInSignUp signInSignUp;

    public static SignInSignUp getInstance(){
        if (signInSignUp == null){
            signInSignUp = new ISignInSignUp();
        }
        return signInSignUp;
    }

    @Override
    public void signIn() {
        System.out.println("Enter Login");
        Scanner scanner = new Scanner(System.in);
        String enteredLogin = scanner.nextLine();
        System.out.println("Enter Password");
        scanner = new Scanner(System.in);
        String enteredPassword = scanner.nextLine();
        User checkUser = Storage.users.stream()
                .filter(u -> u.getLogin().equals(enteredLogin) && u.getPassword().equals(enteredPassword))
                .findFirst().orElse(null);
        if (checkUser != null){
            if (checkUser.getRole().equals(Role.ADMIN)){
                IAdminService.getInstance().openAdminConsole();
            }
            if (checkUser.getRole().equals(Role.MANAGER)){
                IManagerService.getInstance().openManagerConsole(checkUser);
            }
            if (checkUser.getRole().equals(Role.USER)){
                IUserService.getInstance().openUserConsole(checkUser);
            }
        }
        else {
            System.out.println("Not registered!");
        }
    }

    @Override
    public void signUp() {
        System.out.println("Enter Login");
        Scanner scanner = new Scanner(System.in);
        String enteredLogin = scanner.nextLine();
        User checkUser = Storage.users.stream()
                .filter(u -> u.getLogin().equals(enteredLogin))
                .findFirst().orElse(null);
        if (checkUser ==null){
            System.out.println("Enter Password");
            scanner = new Scanner(System.in);
            String enteredPassword = scanner.nextLine();
            System.out.println("Enter Name");
            scanner = new Scanner(System.in);
            String enteredName = scanner.nextLine();
            User user = new User(User.getCurrentId(), enteredName, enteredLogin, enteredPassword, Role.USER, 0);
            Storage.users.add(user);

        }
        else {
            System.out.println("Already registered!");
        }
    }
}
