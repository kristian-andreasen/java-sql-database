package demo;

import demo.models.Customer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.lang.*;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    @Value("${spring.datasource.url}")
    private String url = "jdbc:postgresql://localhost:5432/test1"; //
    @Value("${spring.datasource.username}")
    private String username = "postgres";
    @Value("${spring.datasource.password}")
    private String password = "admin";


    @Override
    public List<Customer> findAllCustomers() {
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

    @Override
    public List<Customer> findAll() {
        return null;
    }

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

            /*while(result.next()) {
                Customer customer = new Customer();
                customers.add(customer);
            }*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;

    }

    @Override
    public int insert(Customer object) {
        return 0;
    }

    @Override
    public int update(Customer object) {
        return 0;
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
