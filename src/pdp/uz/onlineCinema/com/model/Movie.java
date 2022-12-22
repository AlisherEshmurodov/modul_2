package pdp.uz.onlineCinema.com.model;

import java.util.Objects;

public class Movie {
    private int id;
    private String name;
    private String date;
    private String description;
    private Cinema cinema;
    private int priceForPerSeat;

    static int currentId = 1;
    {
        currentId++;
    }

    public Movie() {
    }

    public Movie(int id, String name, String date, String description, Cinema cinema, int priceForPerSeat) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.description = description;
        this.cinema = cinema;
        this.priceForPerSeat = priceForPerSeat;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                ", cinema=" + cinema +
                ", priceForPerSeat=" + priceForPerSeat +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id && priceForPerSeat == movie.priceForPerSeat && Objects.equals(name, movie.name) && Objects.equals(date, movie.date) && Objects.equals(description, movie.description) && Objects.equals(cinema, movie.cinema);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, date, description, cinema, priceForPerSeat);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public int getPriceForPerSeat() {
        return priceForPerSeat;
    }

    public void setPriceForPerSeat(int priceForPerSeat) {
        this.priceForPerSeat = priceForPerSeat;
    }

    public static int getCurrentId() {
        return currentId;
    }

    public static void setCurrentId(int currentId) {
        Movie.currentId = currentId;
    }
}
