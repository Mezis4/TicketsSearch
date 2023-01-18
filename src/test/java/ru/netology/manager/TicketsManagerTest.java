package ru.netology.manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.comparator.TicketDurationComparator;
import ru.netology.comparator.TicketPriceComparator;
import ru.netology.data.Tickets;
import ru.netology.repository.TicketsRepository;

import java.util.Comparator;


public class TicketsManagerTest {
    TicketsRepository repo = new TicketsRepository();
    TicketsManager manager = new TicketsManager(repo);
    Comparator<Tickets> comparatorByDuration = new TicketDurationComparator();
    Comparator<Tickets> comparatorByPrice = new TicketPriceComparator();

    Tickets ticket1 = new Tickets(35, 2390, "MOW", "LED", 100);
    Tickets ticket2 = new Tickets(29, 3320, "MOW", "TJM", 170);
    Tickets ticket3 = new Tickets(44, 3320, "MOW", "TJM", 200);
    Tickets ticket4 = new Tickets(61, 1600, "LED", "KZN", 170);
    Tickets ticket5 = new Tickets(58, 3320, "MOW", "TJM", 150);
    Tickets ticket6 = new Tickets(26, 1600, "KZN", "LED", 170);
    Tickets ticket7 = new Tickets(14, 2400, "MOW", "LED", 100);
    Tickets ticket8 = new Tickets(24, 1600, "LED", "KZN", 160);

    @Test
    public void shouldNotFindTicketsFromToRequestInEmptyRepoPriceComparator() {
        Tickets[] expected = new Tickets[]{};
        Tickets[] actual = manager.findAll("KZN", "LED", comparatorByPrice);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindAllTicketsFromToRequestPriceComparator() {
        manager.save(ticket2);
        manager.save(ticket3);

        Tickets[] expected = new Tickets[]{ticket2, ticket3};
        Tickets[] actual = manager.findAll("MOW", "TJM", comparatorByPrice);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindSeveralTicketsFromToRequestPriceComparator() {
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

        Tickets[] actual = manager.findAll("MOW", "TJM", comparatorByPrice);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotFindNonexistentTicketsFromToRequestPriceComparator() {
        manager.save(ticket1);
        manager.save(ticket2);
        manager.save(ticket3);
        manager.save(ticket4);
        manager.save(ticket5);
        manager.save(ticket6);
        manager.save(ticket7);
        manager.save(ticket8);

        Tickets[] expected = new Tickets[]{};

        Tickets[] actual = manager.findAll("LED", "TJM", comparatorByPrice);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindOneTicketFromToRequestPriceComparator() {
        manager.save(ticket1);
        manager.save(ticket2);
        manager.save(ticket3);
        manager.save(ticket4);
        manager.save(ticket5);
        manager.save(ticket6);
        manager.save(ticket7);
        manager.save(ticket8);

        Tickets[] expected = new Tickets[]{ticket6};

        Tickets[] actual = manager.findAll("KZN", "LED", comparatorByPrice);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindTwoTicketsFromToRequestRepoMoreThanTwoPriceComparator() {
        manager.save(ticket1);
        manager.save(ticket2);
        manager.save(ticket3);
        manager.save(ticket4);
        manager.save(ticket5);
        manager.save(ticket6);
        manager.save(ticket7);
        manager.save(ticket8);

        Tickets[] expected = new Tickets[]{ticket4, ticket8};

        Tickets[] actual = manager.findAll("LED", "KZN", comparatorByPrice);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotFindTicketsFromToRequestInEmptyRepoDurationComparator() {
        Tickets[] expected = new Tickets[]{};
        Tickets[] actual = manager.findAll("KZN", "LED", comparatorByDuration);

        Assertions.assertArrayEquals(expected, actual);
    }

    // В 2-х следующих тестах проверям вывод всех билетов и сортировку равных по продолжительности полета
    @Test
    public void shouldFindAllTicketsFromToRequestDurationComparator() {
        manager.save(ticket1);
        manager.save(ticket7);

        Tickets[] expected = new Tickets[]{ticket1, ticket7};
        Tickets[] actual = manager.findAll("MOW", "LED", comparatorByDuration);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSortEqualDurationAllTicketsFromToRequestByAddingOrder() {
        manager.save(ticket7);
        manager.save(ticket1);

        Tickets[] expected = new Tickets[]{ticket7, ticket1};
        Tickets[] actual = manager.findAll("MOW", "LED", comparatorByDuration);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindSeveralTicketsFromToRequestDurationComparator() {
        manager.save(ticket1);
        manager.save(ticket2);
        manager.save(ticket3);
        manager.save(ticket4);
        manager.save(ticket5);
        manager.save(ticket6);
        manager.save(ticket7);
        manager.save(ticket8);

        Tickets[] expected = new Tickets[]{
                ticket5,
                ticket2,
                ticket3,
        };

        Tickets[] actual = manager.findAll("MOW", "TJM", comparatorByDuration);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotFindNonexistentTicketsFromToRequestDurationComparator() {
        manager.save(ticket1);
        manager.save(ticket2);
        manager.save(ticket3);
        manager.save(ticket4);
        manager.save(ticket5);
        manager.save(ticket6);
        manager.save(ticket7);
        manager.save(ticket8);

        Tickets[] expected = new Tickets[]{};

        Tickets[] actual = manager.findAll("LED", "TJM", comparatorByDuration);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindOneTicketFromToRequestDurationComparator() {
        manager.save(ticket1);
        manager.save(ticket2);
        manager.save(ticket3);
        manager.save(ticket4);
        manager.save(ticket5);
        manager.save(ticket6);
        manager.save(ticket7);
        manager.save(ticket8);

        Tickets[] expected = new Tickets[]{ticket6};

        Tickets[] actual = manager.findAll("KZN", "LED", comparatorByDuration);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindTwoTicketsFromToRequestRepoMoreThanTwoDurationComparator() {
        manager.save(ticket1);
        manager.save(ticket2);
        manager.save(ticket3);
        manager.save(ticket4);
        manager.save(ticket5);
        manager.save(ticket6);
        manager.save(ticket7);
        manager.save(ticket8);

        Tickets[] expected = new Tickets[]{ticket8, ticket4};

        Tickets[] actual = manager.findAll("LED", "KZN", comparatorByDuration);

        Assertions.assertArrayEquals(expected, actual);
    }
}