package ru.netology.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.Tickets;
import ru.netology.exception.AlreadyExistsException;
import ru.netology.exception.NotFoundException;

public class TicketsRepositoryTest {
    TicketsRepository repo = new TicketsRepository();

    Tickets ticket1 = new Tickets(35, 2390, "MOW", "LED", 100);
    Tickets ticket2 = new Tickets(29, 3320, "MOW", "TJM", 170);
    Tickets ticket3 = new Tickets(44, 3320, "MOW", "TJM", 200);
    Tickets ticket4 = new Tickets(61, 1600, "LED", "KZN", 170);
    Tickets ticket5 = new Tickets(58, 3320, "MOW", "TJM", 150);
    Tickets ticket6 = new Tickets(26, 1600, "KZN", "LED", 170);
    Tickets ticket7 = new Tickets(14, 2400, "MOW", "LED", 120);
    Tickets ticket8 = new Tickets(24, 1600, "LED", "KZN", 160);

    @BeforeEach
    void setup() {
        repo.save(ticket1);
        repo.save(ticket2);
        repo.save(ticket3);
        repo.save(ticket4);
        repo.save(ticket5);
        repo.save(ticket6);
        repo.save(ticket7);
    }

    @Test
    public void shouldRemoveById() {
        repo.removeById(61);

        Tickets[] expected = new Tickets[]{
                ticket1,
                ticket2,
                ticket3,
                ticket5,
                ticket6,
                ticket7,
        };
        Tickets[] actual = repo.getTickets();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotRemoveByIdNonexistentTicket() {
        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(13);
        });
    }

    @Test
    public void shouldAddNewTicket() {
        repo.save(ticket8);

        Tickets[] expected = new Tickets[]{
                ticket1,
                ticket2,
                ticket3,
                ticket4,
                ticket5,
                ticket6,
                ticket7,
                ticket8
        };

        Tickets[] actual = repo.getTickets();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotAddNewTicketWithExistentId() {
        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.save(ticket3);
        });
    }

    @Test
    public void shouldFindAllTickets() {
        Tickets[] expected = new Tickets[]{
                ticket1,
                ticket2,
                ticket3,
                ticket4,
                ticket5,
                ticket6,
                ticket7,
        };

        Tickets[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }
}