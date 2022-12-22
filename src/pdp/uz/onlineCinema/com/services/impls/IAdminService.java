package pdp.uz.onlineCinema.com.services.impls;

import pdp.uz.onlineCinema.com.Storage;
import pdp.uz.onlineCinema.com.model.Cinema;
import pdp.uz.onlineCinema.com.model.CinemaManager;
import pdp.uz.onlineCinema.com.model.Role;
import pdp.uz.onlineCinema.com.model.User;
import pdp.uz.onlineCinema.com.services.interfaces.AdminService;
import pdp.uz.onlineCinema.com.services.interfaces.MainMenu;

import java.util.Scanner;

public class IAdminService implements AdminService {

    private static AdminService adminService;

    public static AdminService getInstance(){
        if (adminService == null){
            adminService = new IAdminService();
        }
        return adminService;
    }

    @Override
    public void openAdminConsole() {
        System.out.println("1-Add Cinema,  2-Add Manager,  3-Add Manager To Cinema,  0-Back");
        Scanner scanner = new Scanner(System.in);
        String com = scanner.nextLine();
        if (com.equals("1")){
            addCinema();
        }
        else if(com.equals("2")){
            addManager();
        }
        else if(com.equals("3")){
            addManagerToCinema();
        }
        else if(com.equals("0")){
            IMainMenu.getInstance().start();
        }
        else {
            System.out.println("Wrong Command!");
            openAdminConsole();
        }
    }

    @Override
    public void addCinema() {
        System.out.print("Enter Cinema name:  ");
        Scanner scanner = new Scanner(System.in);
        String enteredCinema = scanner.nextLine();

        Cinema checkCinema = Storage.cinemas.stream()
                .filter(c -> c.getName().equals(enteredCinema))
                .findFirst().orElse(null);
        if (checkCinema == null){
            System.out.print("Enter Cinema address:  ");
            scanner = new Scanner(System.in);
            String enteredAddress = scanner.nextLine();
            System.out.print("Enter Number of Seats:  ");
            scanner = new Scanner(System.in);
            int enteredNumberOfSeats = scanner.nextInt();
            Cinema cinema = new Cinema(Cinema.getCurrentId(), enteredCinema, enteredAddress, enteredNumberOfSeats);
            Storage.cinemas.add(cinema);
            System.out.println("Cinema Successfully Added");
            openAdminConsole();
        }
        else {
            System.out.println("Already added");
            openAdminConsole();
        }

    }

    @Override
    public void addManager() {
        System.out.print("Enter Manager Login:  ");
        Scanner scanner = new Scanner(System.in);
        String enteredManLogin = scanner.nextLine();

        User checkuser = Storage.users.stream()
                .filter(c -> c.getLogin().equals(enteredManLogin))
                .findFirst().orElse(null);
        if (checkuser == null){
            System.out.print("Enter Password:  ");
            scanner = new Scanner(System.in);
            String enteredPassword = scanner.nextLine();
            System.out.print("Enter Name:  ");
            scanner = new Scanner(System.in);
            String enteredName = scanner.nextLine();
            User user = new User(User.getCurrentId(), enteredName, enteredManLogin, enteredPassword, Role.MANAGER, 0);
            Storage.users.add(user);
            System.out.println("Manager Successfully added");
            openAdminConsole();
        }
        else {
            System.out.println("Wrong Login");
            openAdminConsole();
        }
    }

    @Override
    public void addManagerToCinema() {
        if (Storage.users.size()>1){
        System.out.println("************  Manager List  ************");

        for (int i = 0; i < Storage.users.size(); i++) {
            if (Storage.users.get(i).getRole().equals(Role.MANAGER)){
                System.out.println(Storage.users.get(i));
            }
        }
            System.out.println("************************************************");
            System.out.print("Select Manager ID:  ");
            Scanner scanner = new Scanner(System.in);
            int selectedManId = scanner.nextInt();
            User selectMan = Storage.users.stream()
                    .filter(m -> m.getId() == selectedManId)
                    .findFirst().orElse(null);


            Storage.cinemas.forEach(System.out::println);
            System.out.println("************************");
            System.out.print("Select Cinema ID:  ");
            scanner = new Scanner(System.in);
            int selectedCinemaId = scanner.nextInt();
            Cinema checkCinema = Storage.cinemas.stream()
                    .filter(c -> c.getId() == selectedCinemaId)
                    .findFirst().orElse(null);
            if (checkCinema != null) {
                CinemaManager cinemaManager = new CinemaManager(CinemaManager.getCurrentId(), checkCinema, selectMan);
                Storage.cinemaManagers.add(cinemaManager);
                ////////////////////////////////////////////////////////////////////////////////////
                Storage.cinemaManagers.forEach(System.out::println);    //////////////////////////////////////////
                ////////////////////////////////////////////////////////////////////////////////////
                System.out.println("Manager to Cinema Successfully added");
                openAdminConsole();
            }
        }
        else {
            System.out.println("No Managers!!!");
            openAdminConsole();
        }
    }
}
