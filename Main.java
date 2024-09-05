import java.util.*;

class Main {

    public static void bookticket(Passenger p) {
        TicketBooker booker = new TicketBooker();

        if (TicketBooker.availableWaitingList == 0) {
            System.out.println("No Tickets available");
            return;
        }

        if ((p.berthPrefernce.equals("L") && TicketBooker.availableLowerBerths > 0)
                || (p.berthPrefernce.equals("M") && TicketBooker.availableMiddleBerths > 0)
                || (p.berthPrefernce.equals("U") && TicketBooker.availableUpperBerths > 0)) {
            System.out.println("------Berth Preference Available-------");
            if (p.berthPrefernce.equals("L")) {
                System.out.println("Lower Berth Given");
                booker.bookticket(p, TicketBooker.lowerBerthsPositions.get(0), "L");
                TicketBooker.availableLowerBerths--;
                TicketBooker.lowerBerthsPositions.remove(0);
            } else if (p.berthPrefernce.equals("U")) {
                System.out.println("Upper Berth Given");
                booker.bookticket(p, TicketBooker.upperBerthsPositions.get(0), "U");
                TicketBooker.availableUpperBerths--;
                TicketBooker.upperBerthsPositions.remove(0);
            } else if (p.berthPrefernce.equals("M")) {
                System.out.println("Middle Berth Given");
                booker.bookticket(p, TicketBooker.middleBerthsPositions.get(0), "M");
                TicketBooker.availableMiddleBerths--;
                TicketBooker.middleBerthsPositions.remove(0);
            }
        }
        // Preference Not Available
        else if (TicketBooker.availableLowerBerths > 0) {
            System.out.println("Lower Berth Given");
            booker.bookticket(p, TicketBooker.lowerBerthsPositions.get(0), "L");
            TicketBooker.availableLowerBerths--;
            TicketBooker.lowerBerthsPositions.remove(0);
        } else if (TicketBooker.availableUpperBerths > 0) {
            System.out.println("Upper Berth Given");
            booker.bookticket(p, TicketBooker.upperBerthsPositions.get(0), "U");
            TicketBooker.availableUpperBerths--;
            TicketBooker.upperBerthsPositions.remove(0);
        } else if (TicketBooker.availableMiddleBerths > 0) {
            System.out.println("Middle Berth Given");
            booker.bookticket(p, TicketBooker.middleBerthsPositions.get(0), "M");
            TicketBooker.availableMiddleBerths--;
            TicketBooker.middleBerthsPositions.remove(0);
        } else if (TicketBooker.availableRacTickets > 0) {
            System.out.println("RAC Available");
            booker.addToRoc(p, TicketBooker.racPositions.get(0), "RAC");
        } else if (TicketBooker.availableWaitingList > 0) {
            System.out.println("Added To Waiting List");
            booker.addWaitingList(p, TicketBooker.waitinglistPosition.get(0), "WL");

        }
    }

    public static void cancelTicket(int passengerId) {
        TicketBooker booker = new TicketBooker();

        if (!TicketBooker.passengers.containsKey(passengerId)) {
            System.out.println("Passenger Detail Unknown");
        } else {
            booker.cancelTicket(passengerId);
        }
    }

    public static void main(String[] args) {
        System.out.println("---------Welcome to Railway Reservation Booking System------");
        Scanner sc = new Scanner(System.in);
        TicketBooker booker = new TicketBooker();

        boolean loop = true;

        while (loop) {
            System.out
                    .println(" 1.Book Ticket \n 2.Cancel Ticket \n 3.Available Tickets \n 4.Booked Tickets \n 5.Exit");

            int num = sc.nextInt();
            sc.nextLine();

            switch (num) {
                case 1:
                    System.out
                            .println(" 1.Enter Your Name \n 2.Enter Your Age \n 3.Enter Your berth Preference (L,M,U)");
                    String name = sc.nextLine();
                    int age = sc.nextInt();
                    sc.nextLine();
                    String berthPreference = sc.nextLine();
                    Passenger p = new Passenger(name, age, berthPreference);
                    bookticket(p);
                    break;
                case 2:
                    System.out.println(" Enter the passenger ID to cancel");
                    int id = sc.nextInt();
                    cancelTicket(id);
                    break;
                case 3:

                    booker.printAvailableTickets();
                    break;
                case 4:
                    booker.printBookedTickets();
                    break;
                case 5:
                    loop = false;
                    break;

                default:
                    System.out.println(" Please Enter a Valid Choice");
                    break;
            }
        }
        sc.close();
    }
}