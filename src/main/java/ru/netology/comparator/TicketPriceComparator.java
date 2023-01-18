package ru.netology.comparator;

import ru.netology.data.Tickets;

import java.util.Comparator;

public class TicketPriceComparator implements Comparator<Tickets> {
    @Override
    public int compare(Tickets o1, Tickets o2) {
        return o1.getPrice() - o2.getPrice();
    }
}
