package ru.netology.manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.data.Tickets;
import ru.netology.repository.TicketsRepository;


public class TicketsManagerTest {
    TicketsRepository repo = new TicketsRepository();
    TicketsManager manager = new TicketsManager(repo);

    Tickets ticket1 = new Tickets(35, 2390, "MOW", "LED", 100);
    Tickets ticket2 = new Tickets(29, 3320, "MOW", "TJM", 170);
    Tickets ticket3 = new Tickets(44, 3320, "MOW", "TJM", 200);
    Tickets ticket4 = new Tickets(61, 1600, "LED", "KZN", 170);
    Tickets ticket5 = new Tickets(58, 3320, "MOW", "TJM", 150);
    Tickets ticket6 = new Tickets(26, 1600, "KZN", "LED", 170);
    Tickets ticket7 = new Tickets(14, 2400, "MOW", "LED", 120);
    Tickets ticket8 = new Tickets(24, 1600, "LED", "KZN", 160);

    @Test
    public void shouldNotFindTicketsFromToRequestInEmptyRepo() {
        Tickets[] expected = new Tickets[]{ };
        Tickets[] actual = manager.findAll("KZN", "LED");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindAllTicketsFromToRequest() {
        manager.save(ticket2);
        manager.save(ticket3);

        Tickets[] expected = new Tickets[]{ticket2, ticket3};
        Tickets[] actual = manager.findAll("MOW", "TJM");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindSeveralTicketsFromToRequest() {
        manager.save(ticket1);
        manager.save(ticket2);
        manager.save(ticket3);
        manager.save(ticket4);
        manager.save(ticket5);
        manager.save(ticket6);
        manager.save(ticket7);
        manager.save(ticket8);

        Tickets[] expected = new Tickets[]{
                ticket2,
                ticket3,
                ticket5
        };

        Tickets[] actual = manager.findAll("MOW", "TJM");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotFindNonexistentTicketsFromToRequest() {
        manager.save(ticket1);
        manager.save(ticket2);
        manager.save(ticket3);
        manager.save(ticket4);
        manager.save(ticket5);
        manager.save(ticket6);
        manager.save(ticket7);
        manager.save(ticket8);

        Tickets[] expected = new Tickets[]{ };

        Tickets[] actual = manager.findAll("LED", "TJM");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindOneTicketFromToRequest() {
        manager.save(ticket1);
        manager.save(ticket2);
        manager.save(ticket3);
        manager.save(ticket4);
        manager.save(ticket5);
        manager.save(ticket6);
        manager.save(ticket7);
        manager.save(ticket8);

        Tickets[] expected = new Tickets[]{ticket6};

        Tickets[] actual = manager.findAll("KZN", "LED");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindTwoTicketsFromToRequestRepoMoreThanTwo() {
        manager.save(ticket1);
        manager.save(ticket2);
        manager.save(ticket3);
        manager.save(ticket4);
        manager.save(ticket5);
        manager.save(ticket6);
        manager.save(ticket7);
        manager.save(ticket8);

        Tickets[] expected = new Tickets[]{ticket4, ticket8};

        Tickets[] actual = manager.findAll("LED", "KZN");

        Assertions.assertArrayEquals(expected, actual);
    }
}