package prep2.ticket;

import java.util.*;

/**
 *
 * @author Yousef ELHAID
 */
enum TicketType {
    BUG("BUG"), FEATURE("FEAT"), TASK("TASK");

    private final String prefix;

    TicketType(String prefix) {
        this.prefix = prefix;
    }

    String prefix() {
        return prefix;
    }
}

final class Ticket {
    private static final Map<TicketType, Integer> COUNTERS = new EnumMap<>(TicketType.class);

    private final String id;
    private final TicketType type;
    private final String title;

    Ticket(TicketType type, String title) {
        Objects.requireNonNull(type, "type");
        Objects.requireNonNull(title, "title");
        String cleanTitle = title.trim();
        if (cleanTitle.isEmpty()) {
            throw new IllegalArgumentException("Title must not be blank");
        }
        int number = COUNTERS.merge(type, 1, Integer::sum);
        this.id = type.prefix() + "-" + number;
        this.type = type;
        this.title = cleanTitle;
    }

    static int createdCount(TicketType type) {
        return COUNTERS.getOrDefault(type, 0);
    }

    String getId() {
        return id;
    }

    TicketType getType() {
        return type;
    }

    String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return id + " " + title;
    }
}
final class TicketBoard {
    private final Map<String, Ticket> ticketsById = new HashMap<>();
    private final Map<TicketType, List<Ticket>> ticketsByType = new EnumMap<>(TicketType.class);

    void add(Ticket ticket) {
        Objects.requireNonNull(ticket, "ticket");
        if (ticketsById.putIfAbsent(ticket.getId(), ticket) != null) {
            throw new IllegalArgumentException("Duplicate ticket: " + ticket.getId());
        }
        ticketsByType.computeIfAbsent(ticket.getType(), t -> new ArrayList<>()).add(ticket);
    }

    Optional<Ticket> findById(String id) {
        return Optional.ofNullable(ticketsById.get(id));
    }

    List<Ticket> byType(TicketType type) {
        return Collections.unmodifiableList(ticketsByType.getOrDefault(type, List.of()));
    }
}

public class Main {

    public static void main(String[] args) {
        Ticket bug1 = new Ticket(TicketType.BUG, " Login crash ");
        Ticket feat1 = new Ticket(TicketType.FEATURE, "Dark mode");
        Ticket bug2 = new Ticket(TicketType.BUG, "Wrong total");
        try {
            new Ticket(TicketType.TASK, "   ");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        Ticket task1 = new Ticket(TicketType.TASK, "Update README");

        System.out.println(bug1);
        System.out.println(bug2.getId());
        System.out.println(feat1);
        System.out.println(task1.getId());
        System.out.println(Ticket.createdCount(TicketType.BUG));
        System.out.println(Ticket.createdCount(TicketType.TASK));

        TicketBoard board = new TicketBoard();
        board.add(bug1);
        board.add(feat1);
        board.add(bug2);
        board.add(task1);

        System.out.println(board.byType(TicketType.BUG));
        System.out.println(new TicketBoard().byType(TicketType.TASK));
        System.out.println(board.findById("FEAT-1"));
        System.out.println(board.findById("BUG-9"));
        try {
            board.add(bug1);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        try {
            board.byType(TicketType.BUG).add(task1);
        } catch (UnsupportedOperationException e) {
            System.out.println("Lecture seule");
        }
        System.out.println(board.byType(TicketType.BUG).size());
    }
}
