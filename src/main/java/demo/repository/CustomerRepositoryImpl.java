package demo.repository;

import demo.models.Customer;

import demo.models.CustomerCountry;
import demo.models.CustomerGenre;
import demo.models.CustomerSpender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.lang.*;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    @Value("${spring.datasource.url}")
    private String url = "jdbc:postgresql://localhost:5432/chinook"; //
    @Value("${spring.datasource.username}")
    private String username = "postgres";
    @Value("${spring.datasource.password}")
    private String password = "admin";

    /**
     *
     * @return a list of customers
     */
    @Override
    public List<Customer> findAll() {
        String sql = "SELECT * FROM customer";
        List<Customer> customers = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, username,password)) {
            // Write statement
            PreparedStatement statement = conn.prepareStatement(sql);
            // Execute statement
            ResultSet result = statement.executeQuery();
            // Handle result
            while(result.next()) {
                Customer customer = new Customer(
                        result.getInt("customer_id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("country"),
                        result.getString("postal_code"),
                        result.getString("phone"),
                        result.getString("email")
                );
                customers.add(customer);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    /**
     *
     * @param id
     * @return
     */

    @Override
    public Customer findById(Integer id) {
        String sql = "SELECT * FROM customer WHERE customer_id = ?";
        //List<Customer> customers = new ArrayList<>();
        Customer customer = new Customer();
        try(Connection conn = DriverManager.getConnection(url, username,password)) {
            // Write statement
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,id);
            // Execute statement
            ResultSet result = statement.executeQuery();
            // Handle result

            while(result.next()) {
                customer.setId(result.getInt("customer_id"));
                customer.setFirstName(result.getString("first_name"));
                customer.setLastName(result.getString("last_name"));
                customer.setCountry(result.getString("country"));
                customer.setPostal_code(result.getString("postal_code"));
                customer.setPhone(result.getString("phone"));
                customer.setEmail(result.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;

    }

    /**
     *
     * @param name
     * @return
     */

    @Override
    public Customer findCustomerByName(String name) {
        String sql = "SELECT * FROM customer WHERE first_name LIKE ?";
        Customer customer = new Customer();
        try(Connection conn = DriverManager.getConnection(url, username,password)) {
            // Write statement
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,name);
            // Execute statement
            ResultSet result = statement.executeQuery();
            // Handle result
            while(result.next()) {
                customer.setId(result.getInt("customer_id"));
                customer.setFirstName(result.getString("first_name"));
                customer.setLastName(result.getString("last_name"));
                customer.setCountry(result.getString("country"));
                customer.setPostal_code(result.getString("postal_code"));
                customer.setPhone(result.getString("phone"));
                customer.setEmail(result.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    /**
     *
     * @param offset
     * @param limit
     * @return
     */

    @Override
    public List<Customer> findCustomerPage(int offset, int limit) {
        String sql = "SELECT * FROM customer ORDER BY customer_id LIMIT ? OFFSET ?";
        List<Customer> customers = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, username,password)) {
            // Write statement
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,limit);
            statement.setInt(2,offset);
            // Execute statement
            ResultSet result = statement.executeQuery();
            // Handle result
            while(result.next()) {
                Customer customer = new Customer(
                        result.getInt("customer_id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("country"),
                        result.getString("postal_code"),
                        result.getString("phone"),
                        result.getString("email")
                );
                customers.add(customer);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    /**
     * Returns a customerCountry object
     *
     * @return CustomerCountry
     */
    @Override
    public CustomerCountry countryWithMostCustomers() {
        String sql = "SELECT MAX(country) AS country FROM customer;";
        CustomerCountry customerCountry = new CustomerCountry();

        try(Connection conn = DriverManager.getConnection(url, username,password)) {
            // Write statement
            PreparedStatement statement = conn.prepareStatement(sql);

            // Execute statement
            ResultSet result = statement.executeQuery();
            result.next();
            customerCountry.setCountry(result.getString("country"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerCountry;
    }

    /**
     * returns a customerSpender object
     *
     * @return customerSpender
     */
    @Override
    public CustomerSpender biggestSpender() {
        String sql = "SELECT SUM(total), customer_id FROM public.invoice group by customer_id ORDER BY 1 DESC;";
        CustomerSpender customerSpender = new CustomerSpender();

        try(Connection conn = DriverManager.getConnection(url, username,password)) {
            // Write statement
            PreparedStatement statement = conn.prepareStatement(sql);
            // Execute statement
            ResultSet result = statement.executeQuery();
            result.next();
            int id = result.getInt("customer_id");
            customerSpender.setCustomer(findById(id));


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerSpender;
    }

    /**
     *
     * @param customer
     * @return
     */
    @Override
    public CustomerGenre mostPopularGenre(Customer customer) {
        String sql = "SELECT count(genre.name) as count, genre.name as genre_name\n" +
                "FROM track join genre\n" +
                "on track.genre_id = genre.genre_id\n" +
                "join invoice_line\n" +
                "on invoice_line.track_id = track.track_id\n" +
                "join invoice\n" +
                "on invoice_line.invoice_id = invoice.invoice_id\n" +
                "WHERE invoice.customer_id = ?\n" +
                "group by genre.name\n" +
                "ORDER BY count desc";

        CustomerGenre customerGenre = new CustomerGenre();
        ArrayList<Integer> listOfCounts = new ArrayList<>();

        try(Connection conn = DriverManager.getConnection(url, username,password)) {
            // Write statement
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,customer.getId());
            // Execute statement
            ResultSet result = statement.executeQuery();


            result.next();
            customerGenre.setMostPopular(result.getString("genre_name"));
            listOfCounts.add(result.getInt("count"));
            result.next();
            customerGenre.setSecondMostPopular(result.getString("genre_name"));
            listOfCounts.add(result.getInt("count"));



            if(listOfCounts.get(0) == listOfCounts.get(1)) {
                customerGenre.setTie(true);
            } else {
                customerGenre.setTie(false);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerGenre;
    }

    /**
     *
     * @param object
     * @return
     */
    @Override
    public int insert(Customer object) {
        String sql = "INSERT INTO customer (first_name,last_name,postal_code,country,phone,email) values (?,?,?,?,?,?)";
        int result = 0;
        try(Connection conn = DriverManager.getConnection(url, username,password)) {
            // Write statement
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,object.getFirstName());
            statement.setString(2,object.getLastName());
            statement.setString(3,object.getPostal_code());
            statement.setString(4,object.getCountry());
            statement.setString(5,object.getPhone());
            statement.setString(6,object.getEmail());
            // Execute statement
            result = statement.executeUpdate();
            // Handle result

        } catch (SQLException e) {
            e.printStackTrace();
        }




        return result;
    }

    /**
     *
     * @param object
     * @return
     */
    @Override
    public int update(Customer object) {
        String sql = "UPDATE customer SET first_name=?, last_name=?, postal_code=?,country=?,phone=?,email=? WHERE customer_id=?";
        int result = 0;
        try(Connection conn = DriverManager.getConnection(url, username,password)) {
            // Write statement
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,object.getFirstName());
            statement.setString(2,object.getLastName());
            statement.setString(3,object.getPostal_code());
            statement.setString(4,object.getCountry());
            statement.setString(5,object.getPhone());
            statement.setString(6,object.getEmail());
            statement.setInt(7,object.getId());
            // Execute statement
            result = statement.executeUpdate();
            // Handle result

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }


    @Override
    public int delete(Customer object) {
        return 0;
    }

    @Override
    public int deleteById(Integer id) {
        return 0;
    }

}
