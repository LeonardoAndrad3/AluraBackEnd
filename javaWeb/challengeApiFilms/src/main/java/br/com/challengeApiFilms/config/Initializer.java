package br.com.challengeApiFilms.config;

import br.com.challengeApiFilms.repository.FilmRepository;
import br.com.challengeApiFilms.service.RequestManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.*;
import java.net.URI;
import java.sql.*;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

@Configuration
@PropertySource("classpath:application.properties")
public class Initializer implements CommandLineRunner {

    @Autowired
    private FilmRepository rep;

    @Value("${userDB}")
    private String username;
    @Value("${password}")
    private String password;
    @Value("${url}")
    private String url;

    private RequestManager request = new RequestManager("https://gist.githubusercontent.com/jacqueline-oliveira/169494892c52ca4d7cd4c6caecd799d8/raw/5af5d7bf9522989eb03bbb8c76c05f0218e8f1b0/script.txt");

    @Override
    public void run(String... args) throws Exception {

        rep.deleteAll();

        var data =request.requesting("");
        Scanner sc = new Scanner(data);

        Connection con = DriverManager.getConnection(url, username, password);
        PreparedStatement ps;

        while(sc.hasNextLine()){
            ps =  con.prepareStatement(sc.nextLine());
            ps.executeUpdate();
        }
    }
}
