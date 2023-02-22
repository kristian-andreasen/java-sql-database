package demo;

import demo.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SuperheroesDbApplication implements ApplicationRunner {
    //contains all objects
    @Autowired
    ChinookDAO dao;
    CustomerRepositoryImpl customerRepositoryImpl;

    public static void main(String[] args) {
        SpringApplication.run(SuperheroesDbApplication.class, args); //this should be the only code in the main scope

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        dao.test();
        CustomerRepositoryImpl customerRepositoryImpl  = new CustomerRepositoryImpl();


        // customer requirements 1)
        //System.out.println(customerRepositoryImpl.findAll());

        // customer requirements 2)
        //System.out.println(customerRepositoryImpl.findById(2));

        //customer requirements 3)
        //System.out.println(customerRepositoryImpl.findCustomerByName("Bjørn"));

        //customer requirements 4)
        //System.out.println(customerRepositoryImpl.findCustomerPage(10,20));

        //customer requirements 5)
        //Customer customer = new Customer("Lasse","Henriksen","2450","nej","nej","Denmark");
        //System.out.println(customerRepositoryImpl.insert(customer));

        //customer requirements 6)
        //Customer customer = new Customer(1,"Lasse","Henriksen","2450","nej","nej","Denmark");
        //System.out.println(customerRepositoryImpl.update(customer));

        //customer requirements 7)
        System.out.println(customerRepositoryImpl.countryWithMostCustomers());

        //customer requirements 8)
        //System.out.println(customerRepositoryImpl.biggestSpender());

        //customer requirements 9)


    }
}
