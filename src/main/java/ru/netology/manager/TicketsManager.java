package ru.netology.manager;

import ru.netology.data.Tickets;
import ru.netology.repository.TicketsRepository;

import java.util.Arrays;

public class TicketsManager {
    private TicketsRepository repo;

    public TicketsManager(TicketsRepository repo) {
        this.repo = repo;
    }

    public void save(Tickets items) {
        repo.save(items);
    }

    public void removeById(int id) {
        repo.removeById(id);
    }

    public Tickets[] findAll(String departureAirport, String arrivalAirport) {
        Tickets[] amount = new Tickets[0];
        for (Tickets tickets : repo.findAll()) {
            if (matches(tickets, departureAirport, arrivalAirport)) {
                Tickets[] tmp = new Tickets[amount.length + 1];
                int result = 0;
                for (Tickets results : amount) {
                    tmp[result] = results;
                    result++;
                }
                tmp[amount.length] = tickets;
                amount = tmp;
            }
        }
        Arrays.sort(amount);
        return amount;
    }

    public boolean matches(Tickets ticket, String searchFrom, String searchTo) {
        if (ticket.matches(searchFrom, searchTo)) {
            return true;
        } else {
            return false;
        }
    }
}