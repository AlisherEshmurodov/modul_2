package pdp.uz.onlineCinema.com.model;

import java.util.Objects;

public class Cinema {
    private int id;
    private String name;
    private String address;
    private int numberOfSeats;
    static int currentId = 1;
    {
        currentId++;
    }

    public Cinema() {
    }

    public Cinema(int id, String name, String address, int numberOfSeats) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.numberOfSeats = numberOfSeats;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cinema cinema = (Cinema) o;
        return id == cinema.id && numberOfSeats == cinema.numberOfSeats && Objects.equals(name, cinema.name) && Objects.equals(address, cinema.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, numberOfSeats);
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                '}';
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public static int getCurrentId() {
        return currentId;
    }

    public static void setCurrentId(int currentId) {
        Cinema.currentId = currentId;
    }
}
