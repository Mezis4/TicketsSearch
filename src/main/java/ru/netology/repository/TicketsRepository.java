package ru.netology.repository;

import ru.netology.data.Tickets;
import ru.netology.exception.AlreadyExistsException;
import ru.netology.exception.NotFoundException;


public class TicketsRepository {
    private Tickets[] ticket = new Tickets[0];

    public void save(Tickets item) {
        if (findById(item.getId()) != null) {
            throw new AlreadyExistsException("Билет с таким ID уже существует");
        }
        Tickets[] tmp = new Tickets[ticket.length + 1];
        for (int i = 0; i < ticket.length; i++) {
            tmp[i] = ticket[i];
        }
        tmp[tmp.length - 1] = item;
        ticket = tmp;
    }

    public void removeById(int id) {
        if (findById(id) == null) {
            throw new NotFoundException("Билет с id " + id + " не найден");
        }
        Tickets[] tmp = new Tickets[ticket.length - 1];
        int copyToIndex = 0;
        for (Tickets items : ticket) {
            if (items.getId() != id) {
                tmp[copyToIndex] = items;
                copyToIndex++;
            }
        }
        ticket = tmp;
    }

    public Tickets[] findAll() {
        Tickets[] all = new Tickets[ticket.length];
        for (int i = 0; i < all.length; i++) {
            all[i] = ticket[i];
        }
        return all;
    }

    public Tickets[] getTickets() {
        return ticket;
    }

    public Tickets findById(int id) {
        for (Tickets tickets : ticket) {
            if (tickets.getId() == id) {
                return tickets;
            }
        }
        return null;
    }
}