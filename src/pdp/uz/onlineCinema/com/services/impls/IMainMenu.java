package pdp.uz.onlineCinema.com.services.impls;

import pdp.uz.onlineCinema.com.services.interfaces.MainMenu;

import java.util.Scanner;

public class IMainMenu implements MainMenu {

    private static MainMenu mainMenu;

    public static MainMenu getInstance(){
        if (mainMenu == null){
            mainMenu = new IMainMenu();
        }
        return mainMenu;
    }

    @Override
    public void start() {
        System.out.println("Xush kelibsiz!");
        while (true){
            System.out.println("1-Enter,  2-Sign Up,  0-Finish");
            Scanner scanner = new Scanner(System.in);
            String com = scanner.nextLine();
            if (com.equals("1")){
                ISignInSignUp.getInstance().signIn();
            }
            else if(com.equals("2")){
                ISignInSignUp.getInstance().signUp();
            }
            else if(com.equals("0")){
                System.exit(0);
            }
            else {
                System.out.println("Wrong Command!");
            }
        }
    }
}
