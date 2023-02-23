
package demo.repository;

import demo.models.Customer;
import demo.models.CustomerCountry;
import demo.models.CustomerGenre;
import demo.models.CustomerSpender;

import java.util.List;

public interface CustomerRepository extends CRUDRepository<Customer, Integer> {
    // add any additional methods specific to the Customer repository
    Customer findCustomerByName(String name);

    List<Customer> findCustomerPage(int offset, int limit);

    CustomerCountry countryWithMostCustomers();

    CustomerSpender biggestSpender();


    CustomerGenre mostPopularGenre(Customer customer);


}
