package edu.ncsu.csc440.poketrader;

import edu.ncsu.csc440.poketrader.config.Roles.UserRoles;
import edu.ncsu.csc440.poketrader.entity.Card;
import edu.ncsu.csc440.poketrader.entity.CardCollection;
import edu.ncsu.csc440.poketrader.entity.User;
import edu.ncsu.csc440.poketrader.repository.CardCollectionRepository;
import edu.ncsu.csc440.poketrader.repository.CardRepository;
import edu.ncsu.csc440.poketrader.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Creates demo accounts and Base Set card catalog on startup if they don't already exist.
 *
 * Demo credentials:
 *   Admin — username: admin    password: admin1234
 *   User  — username: user     password: user1234
 */
@Component
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CardCollectionRepository collectionRepository;
    private final PasswordEncoder passwordEncoder;
    private final CardRepository cardRepository;

    public DataSeeder(UserRepository userRepository,
                      CardCollectionRepository collectionRepository,
                      PasswordEncoder passwordEncoder,
                      CardRepository cardRepository) {
        this.userRepository = userRepository;
        this.collectionRepository = collectionRepository;
        this.passwordEncoder = passwordEncoder;
        this.cardRepository = cardRepository;
    }

    @Override
    public void run(String... args) {
        seedCards();
        seedUser("admin", "admin1234", "Admin", "User", UserRoles.Admin);
        seedUser("user",  "user1234",  "Demo",  "User", UserRoles.User);
    }

    private void seedCards() {
        if (cardRepository.count() > 0) return;

        List<Card> cards = List.of(
            // -- Pokémon (Holofoil Rares) --
            card("Alakazam",          "Psychic",   80),
            card("Blastoise",         "Water",    100),
            card("Chansey",           "Colorless",120),
            card("Charizard",         "Fire",     120),
            card("Clefairy",          "Colorless", 40),
            card("Gyarados",          "Water",    100),
            card("Hitmonchan",        "Fighting",  70),
            card("Machamp",           "Fighting", 100),
            card("Magneton",          "Lightning", 60),
            card("Mewtwo",            "Psychic",   60),
            card("Nidoking",          "Grass",     90),
            card("Ninetales",         "Fire",      80),
            card("Poliwrath",         "Water",     90),
            card("Raichu",            "Lightning", 80),
            card("Venusaur",          "Grass",    100),
            card("Zapdos",            "Lightning", 90),
            // -- Pokémon (Non-Holo Rares) --
            card("Beedrill",          "Grass",     80),
            card("Dragonair",         "Colorless", 80),
            card("Dugtrio",           "Fighting",  70),
            card("Electabuzz",        "Lightning", 70),
            card("Electrode",         "Lightning", 80),
            card("Pidgeotto",         "Colorless", 60),
            card("Arcanine",          "Fire",     100),
            card("Charmeleon",        "Fire",      80),
            card("Dewgong",           "Water",     80),
            card("Dratini",           "Colorless", 40),
            card("Farfetch'd",        "Colorless", 50),
            card("Growlithe",         "Fire",      60),
            card("Haunter",           "Psychic",   60),
            card("Ivysaur",           "Grass",     60),
            card("Jynx",              "Psychic",   70),
            card("Kadabra",           "Psychic",   60),
            card("Kakuna",            "Grass",     80),
            card("Machoke",           "Fighting",  80),
            card("Magikarp",          "Water",     30),
            card("Magmar",            "Fire",      50),
            card("Nidorino",          "Grass",     60),
            card("Poliwhirl",         "Water",     60),
            card("Porygon",           "Colorless", 30),
            card("Raticate",          "Colorless", 60),
            card("Seel",              "Water",     60),
            card("Wartortle",         "Water",     70),
            // -- Pokémon (Commons) --
            card("Abra",              "Psychic",   30),
            card("Bulbasaur",         "Grass",     40),
            card("Caterpie",          "Grass",     40),
            card("Charmander",        "Fire",      50),
            card("Diglett",           "Fighting",  30),
            card("Doduo",             "Colorless", 50),
            card("Drowzee",           "Psychic",   50),
            card("Gastly",            "Psychic",   30),
            card("Koffing",           "Grass",     50),
            card("Machop",            "Fighting",  50),
            card("Magnemite",         "Lightning", 40),
            card("Metapod",           "Grass",     70),
            card("Nidoran M",         "Grass",     40),
            card("Onix",              "Fighting",  90),
            card("Pidgey",            "Colorless", 40),
            card("Pikachu",           "Lightning", 40),
            card("Poliwag",           "Water",     40),
            card("Ponyta",            "Fire",      40),
            card("Rattata",           "Colorless", 30),
            card("Sandshrew",         "Fighting",  40),
            card("Squirtle",          "Water",     40),
            card("Starmie",           "Water",     60),
            card("Staryu",            "Water",     40),
            card("Tangela",           "Grass",     50),
            card("Voltorb",           "Lightning", 40),
            card("Vulpix",            "Fire",      50),
            card("Weedle",            "Grass",     40),
            // -- Trainers --
            cardNoType("Clefairy Doll",          10),
            cardNoType("Computer Search",         null),
            cardNoType("Devolution Spray",        null),
            cardNoType("Impostor Professor Oak",  null),
            cardNoType("Item Finder",             null),
            cardNoType("Lass",                    null),
            cardNoType("Pokemon Breeder",         null),
            cardNoType("Pokemon Trader",          null),
            cardNoType("Scoop Up",                null),
            cardNoType("Super Energy Removal",    null),
            cardNoType("Defender",                null),
            cardNoType("Energy Retrieval",        null),
            cardNoType("Full Heal",               null),
            cardNoType("Maintenance",             null),
            cardNoType("PlusPower",               null),
            cardNoType("Pokemon Center",          null),
            cardNoType("Pokemon Flute",           null),
            cardNoType("Pokedex",                 null),
            cardNoType("Professor Oak",           null),
            cardNoType("Revive",                  null),
            cardNoType("Super Potion",            null),
            cardNoType("Bill",                    null),
            cardNoType("Energy Removal",          null),
            cardNoType("Gust of Wind",            null),
            cardNoType("Potion",                  null),
            cardNoType("Switch",                  null),
            // -- Energy --
            cardNoType("Double Colorless Energy", null),
            cardNoType("Fighting Energy",         null),
            cardNoType("Fire Energy",             null),
            cardNoType("Grass Energy",            null),
            cardNoType("Lightning Energy",        null),
            cardNoType("Psychic Energy",          null),
            cardNoType("Water Energy",            null)
        );

        cardRepository.saveAll(cards);
        System.out.printf("[DataSeeder] Seeded %d Base Set cards.%n", cards.size());
    }

    private Card card(String name, String primaryType, Integer hp) {
        Card c = new Card();
        c.setName(name);
        c.setPrimaryType(primaryType);
        c.setSecondaryType(null);
        c.setGrade(null);
        c.setHp(hp);
        c.setCardSet("Base");
        c.setMoves(List.of());
        return c;
    }

    private Card cardNoType(String name, Integer hp) {
        Card c = new Card();
        c.setName(name);
        c.setPrimaryType(null);
        c.setSecondaryType(null);
        c.setGrade(null);
        c.setHp(hp);
        c.setCardSet("Base");
        c.setMoves(List.of());
        return c;
    }

    private void seedUser(String username, String password,
                          String firstName, String lastName, UserRoles role) {
        if (userRepository.existsByUsername(username)) return;

        User u = new User();
        u.setUsername(username);
        u.setPasswordHash(passwordEncoder.encode(password));
        u.setPasswordSalt(null);
        u.setFirstName(firstName);
        u.setLastName(lastName);
        u.setRole(role);
        User saved = userRepository.save(u);

        CardCollection col = new CardCollection();
        col.setUser(saved);
        col.setName("My Collection");
        col.setDescription("Default collection");
        collectionRepository.save(col);

        System.out.printf("[DataSeeder] Created %s account: username='%s' password='%s'%n",
                role.name(), username, password);
    }
}
