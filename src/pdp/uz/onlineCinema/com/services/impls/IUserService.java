package pdp.uz.onlineCinema.com.services.impls;

import pdp.uz.onlineCinema.com.Storage;
import pdp.uz.onlineCinema.com.model.Order;
import pdp.uz.onlineCinema.com.model.Status;
import pdp.uz.onlineCinema.com.model.Ticket;
import pdp.uz.onlineCinema.com.model.User;
import pdp.uz.onlineCinema.com.services.interfaces.MainMenu;
import pdp.uz.onlineCinema.com.services.interfaces.UserService;

import java.util.Scanner;

public class IUserService implements UserService {

    private static UserService userService;

    public static UserService getInstance(){
        if (userService == null){
            userService = new IUserService();
        }
        return userService;
    }

    @Override
    public void openUserConsole(User currentUser) {
        System.out.println("1-Buy Ticket,  2-Show Bought Tickets List,  0-Back");
        Scanner scanner = new Scanner(System.in);
        String com = scanner.nextLine();
        if (com.equals("1")){
            buyTicket(currentUser);
        }
        else if(com.equals("2")){
            boughtTicketsList(currentUser);
        }
        else if(com.equals("0")){
            IMainMenu.getInstance().start();
        }
        else {
            System.out.println("Wrong Command!");
            openUserConsole(currentUser);
        }
    }

    @Override
    public void buyTicket(User currentUser) {
        System.out.println("******************  Cinemas List  ******************");
        for (int i = 0; i < Storage.cinemas.size(); i++) {
                System.out.println(Storage.cinemas.get(i));
        }
        System.out.print("Select Cinema Id:  ");
        Scanner scanner = new Scanner(System.in);
        int selectedCinemaId = scanner.nextInt();
        Ticket selectedMovie = Storage.tickets.stream()
                .filter(t -> t.getMovie().getCinema().getId() == selectedCinemaId)
                .findFirst().orElse(null);

        for (int i = 0; i < Storage.movies.size(); i++) {
            if (Storage.movies.get(i).getCinema().getId() == selectedCinemaId){
                System.out.println(Storage.movies.get(i));
            }
        }



        System.out.print("Select Movie Id:  ");
        scanner = new Scanner(System.in);
        int selectedMovieId = scanner.nextInt();
        Ticket selectedTicket = Storage.tickets.stream()
                .filter(u -> u.getMovie().equals(selectedMovieId))
                .findFirst().orElse(null);

        for (int i = 0; i < Storage.tickets.size(); i++) {
            if (Storage.tickets.get(i).getMovie().equals(selectedTicket)){
                if (Storage.tickets.get(i).getStatus().equals(Status.AVAILABLE)){
                    System.out.println(Storage.tickets.get(i));
                }
            }
        }

        System.out.print("Select Ticket Id:  ");
        scanner = new Scanner(System.in);
        int selectedTicketId = scanner.nextInt();
        Ticket ticketId = Storage.tickets.stream()
                .filter(t -> t.getId() == selectedTicketId)
                .findFirst().orElse(null);

        int sum = ticketId.getPrice();



        Order order = new Order(Order.getCurrentId(), currentUser, ticketId, sum);
        Storage.orders.add(order);
        System.out.println("Order Successfully added!");

        ////////////////////////////////////////////////////////////////////////////////////
        Storage.tickets.forEach(System.out::println);
        ////////////////////////////////////////////////////////////////////////////////////

        openUserConsole(currentUser);
    }



    @Override
    public void boughtTicketsList(User currentUser) {
        System.out.println("*****************  Bought Tickets List *****************");
        Storage.orders.forEach(System.out::println);
    }
}
