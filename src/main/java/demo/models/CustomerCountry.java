package demo.models;

public class CustomerCountry {
    private String country;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "CustomerCountry{" +
                "country='" + country + '\'' +
                '}';
    }
}
