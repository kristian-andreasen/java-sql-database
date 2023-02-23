
package demo;

import demo.models.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerRepository extends CRUDRepository<Customer, Integer> {
    // add any additional methods specific to the Customer repository
    Customer findCustomerByName(String name);

    List<Customer> findCustomerPage(int offset, int limit);

    String countryWithMostCustomers();

    Customer biggestSpender();

    String[] mostPopularGenre(Customer customer);


}
