package demo;

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


        customerRepositoryImpl.findAll();
    }
}

        //customerRepositoryImpl.findAll();
    }
}

