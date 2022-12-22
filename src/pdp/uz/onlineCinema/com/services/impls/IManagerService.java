package pdp.uz.onlineCinema.com.services.impls;

import pdp.uz.onlineCinema.com.Storage;
import pdp.uz.onlineCinema.com.model.*;
import pdp.uz.onlineCinema.com.services.interfaces.MainMenu;
import pdp.uz.onlineCinema.com.services.interfaces.ManagerService;

import java.util.Scanner;

public class IManagerService implements ManagerService {

    private static ManagerService managerService;

    public static ManagerService getInstance(){
        if (managerService == null){
            managerService = new IManagerService();
        }
        return managerService;
    }

    @Override
    public void openManagerConsole(User currentManager) {
        System.out.println("1-Add Movies,  2-Show Tickets List,  0-Back");
        Scanner scanner = new Scanner(System.in);
        String com = scanner.nextLine();
        if (com.equals("1")){
            addMovie(currentManager);
        }
        else if(com.equals("2")){
            ticketList(currentManager);
        }
        else if(com.equals("0")){
            IMainMenu.getInstance().start();
        }
        else {
            System.out.println("Wrong Command!");
            openManagerConsole(currentManager);
        }
    }

    @Override
    public void addMovie(User currentManager) {
        System.out.println("********  Cinemas List  ********");
        CinemaManager takeCurrentManager = Storage.cinemaManagers.stream()
                .filter(t -> t.getUser().equals(currentManager))
                .findFirst().orElse(null);
        for (int i = 0; i < Storage.cinemaManagers.size(); i++) {
            if (Storage.cinemaManagers.get(i).getUser().equals(currentManager)){
                System.out.println(Storage.cinemaManagers.get(i).getCinema());
            }
        }

        System.out.print("Select Cinema ID:  ");
        Scanner scanner = new Scanner(System.in);
        int selectedCinemaID = scanner.nextInt();
        Cinema checkCinema = Storage.cinemas.stream()
                .filter(c -> c.getId() == selectedCinemaID)
                .findFirst().orElse(null);

        System.out.print("Enter Movie Name:  ");
        scanner = new Scanner(System.in);
        String  enteredMovieName = scanner.nextLine();
        System.out.print("Enter Movie Date:  ");
        scanner = new Scanner(System.in);
        String  enteredMovieDate = scanner.nextLine();
        System.out.print("Enter Movie Description:  ");
        scanner = new Scanner(System.in);
        String enteredMovieDescription = scanner.nextLine();
        System.out.print("Enter Price for Per Seats:  ");
        scanner = new Scanner(System.in);
        int enteredPricePerSaets = scanner.nextInt();

        Movie movie = new Movie(Movie.getCurrentId(), enteredMovieName,
                enteredMovieDate, enteredMovieDescription, checkCinema, enteredPricePerSaets);
        Storage.movies.add(movie);
        System.out.println("Movie Successfully added");

        System.out.println("////////////////////////////////////////////////////////");

        for (int i = 0; i < checkCinema.getNumberOfSeats(); i++) {
            Ticket ticket = new Ticket(Ticket.getCurrentId(), movie, enteredPricePerSaets, (i+1), Status.AVAILABLE);
            Storage.tickets.add(ticket);
        }


        ////////////////////////////////////////////////////////////////////////////////////
        Storage.movies.forEach(System.out::println);
        System.out.println("////////////////////////////////////////////////////////");
        Storage.tickets.forEach(System.out::println);
        ////////////////////////////////////////////////////////////////////////////////////
        openManagerConsole(currentManager);
    }

    @Override
    public void ticketList(User currentManager) {
        if (Storage.tickets.size() > 0) {
            System.out.println("****************  Tickets List  ****************");
            CinemaManager checkCinemaMan = Storage.cinemaManagers.stream()
                    .filter(b -> b.getUser().equals(currentManager))
                    .findFirst().orElse(null);
            if (checkCinemaMan != null) {
                        for (int i = 0; i < Storage.tickets.size(); i++) {
                            System.out.print("Name of Cinema:  " + Storage.tickets.get(i).getMovie().getCinema().getName() +
                                    "  |   Name Of Movie:  " + Storage.tickets.get(i).getMovie().getName() +
                                    "   |   Seat Number:  " + Storage.tickets.get(i).getSeatNumber() +
                                    "   |   Price Of Per Seat:  " + Storage.tickets.get(i).getPrice() + " $" +
                                    "   |   " + Storage.tickets.get(i).getStatus());
                            System.out.println("\n--------------------------------------------------" +
                                    "-------------------------------------------------------");
                        }
                openManagerConsole(currentManager);
            }
        }
        else {
            System.out.println("No Tickets");
            openManagerConsole(currentManager);
        }
    }
}
