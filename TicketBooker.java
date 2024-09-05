import java.util.*;

public class TicketBooker {

    public static int availableLowerBerths = 1;
    public static int availableMiddleBerths = 1;
    public static int availableUpperBerths = 1;
    public static int availableRacTickets = 1;
    public static int availableWaitingList = 1;

    static Queue<Integer> racList = new LinkedList<>();
    static Queue<Integer> waitingList = new LinkedList<>();

    static List<Integer> bookedTicketList = new ArrayList<>();
    static List<Integer> lowerBerthsPositions = new ArrayList<>(Arrays.asList(1));
    static List<Integer> middleBerthsPositions = new ArrayList<>(Arrays.asList(1));
    static List<Integer> upperBerthsPositions = new ArrayList<>(Arrays.asList(1));
    static List<Integer> racPositions = new ArrayList<>(Arrays.asList(1));
    static List<Integer> waitinglistPosition = new ArrayList<>(Arrays.asList(1));

    static Map<Integer, Passenger> passengers = new HashMap<>();

    public void bookticket(Passenger p, int seatNumber, String allotedBerth) {
        p.allotedBerth = allotedBerth;
        p.seatNumber = seatNumber;
        passengers.put(p.passengerId, p);
        bookedTicketList.add(p.passengerId);
        System.out.println("-------Ticket Booked SuccessFully-----");
    }

    public void addToRoc(Passenger p, int seatNumber, String allotedBerth) {
        p.seatNumber = seatNumber;
        p.allotedBerth = allotedBerth;
        passengers.put(p.passengerId, p);
        racList.add(p.passengerId);
        availableRacTickets--;
        racPositions.remove(0);
    }

    public void addWaitingList(Passenger p, int seatNumber, String allotedBerth) {
        p.seatNumber = seatNumber;
        p.allotedBerth = allotedBerth;
        passengers.put(p.passengerId, p);
        waitingList.add(p.passengerId);
        availableWaitingList--;
        waitinglistPosition.remove(0);
    }

    public void cancelTicket(int passengerId) {
        Passenger p = passengers.get(passengerId);
        passengers.remove(passengerId);
        bookedTicketList.remove(passengerId);

        int seatNumber = p.seatNumber;

        if (p.allotedBerth.equals("L")) {
            availableLowerBerths++;
            lowerBerthsPositions.add(seatNumber);
            
        } else if (p.allotedBerth.equals("U")) {
            availableUpperBerths++;
            upperBerthsPositions.add(seatNumber);
        } else if (p.allotedBerth.equals("M")) {
            availableMiddleBerths++;
            middleBerthsPositions.add(seatNumber);
        }

        if (racList.size() > 0) {
            Passenger passengerFromRac = passengers.get(racList.poll());
            racPositions.add(passengerFromRac.seatNumber);
            racList.remove(passengerFromRac.passengerId);
            availableRacTickets++;
            Main.bookticket(passengerFromRac);

            if (waitingList.size() > 0) {
                Passenger waitingListPassenger = passengers.get(waitingList.poll());
                waitinglistPosition.add(waitingListPassenger.seatNumber);
                waitingList.remove(waitingListPassenger.passengerId);
                availableWaitingList++;
                addToRoc(waitingListPassenger, racPositions.get(0), "RAC");
                availableRacTickets--;
            }
        }
    }

    public void printAvailableTickets() {
        System.out.println("Available Lower Berths " + availableLowerBerths);
        System.out.println("Available Middle Berths " + availableMiddleBerths);
        System.out.println("Available Upper Berths " + availableUpperBerths);
        System.out.println("Availabel RACs " + availableRacTickets);
        System.out.println("Available Waiting List " + availableWaitingList);
        System.out.println("--------------------------");
    }

    public void printBookedTickets() {
        if (passengers.size() == 0) {
            System.out.println("-----No Tickets Booked----");
            return;

        }
        for (var p : passengers.values()) {
            System.out.println("PASSENGER ID " + p.passengerId);
            System.out.println(" Name " + p.name);
            System.out.println(" Age " + p.age);
            System.out.println(" Status " + p.seatNumber);
            System.out.println("--------------------------");
        }

    }
}