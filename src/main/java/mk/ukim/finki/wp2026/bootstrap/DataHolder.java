package mk.ukim.finki.wp2026.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp2026.model.Category;
import mk.ukim.finki.wp2026.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Category> categories = null;
    public static List<User> users = null;

    @PostConstruct
    public void init() {
        categories = new ArrayList<>();
        categories.add(new Category("Movies", "Movies Category"));
        categories.add(new Category("Books", "Books Category"));

        users = new ArrayList<>();
        users.add(new User("elena.atanasoska", "ea", "Elena", "Atanasoska"));
        users.add(new User("darko.sasanski", "ds", "Darsko", "Sasanski"));
        users.add(new User("bojana.marojevikj", "bm", "Bojana", "Marojevikj"));
    }
}
