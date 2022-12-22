package pdp.uz.onlineCinema.com.model;

import java.util.Objects;

public class Order {
    private int id;
    private User user;
    private Ticket ticket;
    private int sum;

    static int currentId = 1;
    {
        currentId++;
    }

    public Order() {
    }

    public Order(int id, User user, Ticket ticket, int sum) {
        this.id = id;
        this.user = user;
        this.ticket = ticket;
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", ticket=" + ticket +
                ", sum=" + sum +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && sum == order.sum && Objects.equals(user, order.user) && Objects.equals(ticket, order.ticket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, ticket, sum);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public static int getCurrentId() {
        return currentId;
    }

    public static void setCurrentId(int currentId) {
        Order.currentId = currentId;
    }
}
