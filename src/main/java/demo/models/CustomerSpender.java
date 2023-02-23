package demo.models;

public class CustomerSpender {
    Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "CustomerSpender{" +
                "customer=" + customer +
                '}';
    }
}
