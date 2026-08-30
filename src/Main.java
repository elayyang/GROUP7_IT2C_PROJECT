import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);


    static String[] services = {
            "Haircut",
            "Hair Color",
            "Manicure",
            "Hair Treatment"
    };

    static double[] prices = {
            100,
            500,
            200,
            350
    };


    static String[] staff = {
            "Mark",
            "Jenny",
            "Claire"
    };


    static String[] timeSlots = {
            "9:00 AM",
            "10:00 AM",
            "11:00 AM",
            "1:00 PM",
            "2:00 PM",
            "3:00 PM"
    };


    static String[] clientNames = new String[50];
    static String[] bookedServices = new String[50];
    static double[] bookedPrices = new double[50];
    static String[] assignedStaff = new String[50];
    static String[] appointmentTimes = new String[50];

    static int appointmentCount = 0;

    public static void main(String[] args) {

        int choice;

        System.out.println("======================================");
        System.out.println("       WELCOME TO SALONBOOK");
        System.out.println(" Multi-Service Appointment Scheduling");
        System.out.println("     & Client Booking System");
        System.out.println("======================================");

        do {

            System.out.println("\n========== SALONBOOK MENU ==========");
            System.out.println("1. Book Appointment");
            System.out.println("2. View Appointments");
            System.out.println("3. Cancel Appointment");
            System.out.println("4. View Services");
            System.out.println("5. View Available Staff");
            System.out.println("6. Exit");
            System.out.println("====================================");

            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {

                case 1:
                    bookAppointment();
                    break;

                case 2:
                    viewAppointments();
                    break;

                case 3:
                    cancelAppointment();
                    break;

                case 4:
                    viewServices();
                    break;

                case 5:
                    viewStaff();
                    break;

                case 6:
                    System.out.println("\nThank you for using SalonBook!");
                    System.out.println("Have a nice day!");
                    break;

                default:
                    System.out.println("\nInvalid choice. Please try again.");

            }

        } while (choice != 6);

        input.close();
    }






    static void viewServices() {

        System.out.println("\n========== AVAILABLE SERVICES ==========");

        for (int i = 0; i < services.length; i++) {

            System.out.println(
                    (i + 1) + ". " +
                            services[i] +
                            " - PHP " +
                            prices[i]
            );
        }
    }




    static void viewStaff() {

        System.out.println("\n========== AVAILABLE STAFF ==========");

        for (int i = 0; i < staff.length; i++) {

            System.out.println((i + 1) + ". " + staff[i]);
        }
    }




    static void bookAppointment() {

        if (appointmentCount >= clientNames.length) {

            System.out.println("Sorry, the appointment list is full.");
            return;
        }

        System.out.println("\n========== BOOK APPOINTMENT ==========");


        System.out.print("Enter client name: ");
        String clientName = input.nextLine();


        viewServices();

        System.out.print("\nSelect service: ");
        int serviceChoice = input.nextInt();
        input.nextLine();

        if (serviceChoice < 1 || serviceChoice > services.length) {

            System.out.println("Invalid service selection.");
            return;
        }

        String selectedService = services[serviceChoice - 1];
        double selectedPrice = prices[serviceChoice - 1];



        System.out.println("\n========== AVAILABLE TIME SLOTS ==========");

        for (int i = 0; i < timeSlots.length; i++) {

            System.out.println(
                    (i + 1) + ". " +
                            timeSlots[i]
            );
        }

        System.out.print("\nSelect appointment time: ");
        int timeChoice = input.nextInt();
        input.nextLine();

        if (timeChoice < 1 || timeChoice > timeSlots.length) {

            System.out.println("Invalid time slot.");
            return;
        }

        String selectedTime = timeSlots[timeChoice - 1];



        if (isTimeBooked(selectedTime)) {

            System.out.println("\nSorry! The selected time slot is already booked.");
            System.out.println("Please choose another time.");
            return;
        }



        viewStaff();

        System.out.print("\nSelect staff member: ");
        int staffChoice = input.nextInt();
        input.nextLine();

        if (staffChoice < 1 || staffChoice > staff.length) {

            System.out.println("Invalid staff selection.");
            return;
        }

        String selectedStaff = staff[staffChoice - 1];



        if (isStaffBooked(selectedStaff, selectedTime)) {

            System.out.println("\nSorry! " + selectedStaff +
                    " is already assigned at " + selectedTime + ".");

            System.out.println("Please choose another staff member.");
            return;
        }



        clientNames[appointmentCount] = clientName;
        bookedServices[appointmentCount] = selectedService;
        bookedPrices[appointmentCount] = selectedPrice;
        assignedStaff[appointmentCount] = selectedStaff;
        appointmentTimes[appointmentCount] = selectedTime;

        appointmentCount++;



        System.out.println("\n======================================");
        System.out.println("       APPOINTMENT CONFIRMED!");
        System.out.println("======================================");

        System.out.println("Client Name : " + clientName);
        System.out.println("Service     : " + selectedService);
        System.out.println("Price       : PHP " + selectedPrice);
        System.out.println("Staff       : " + selectedStaff);
        System.out.println("Time        : " + selectedTime);

        System.out.println("======================================");
    }




    static boolean isTimeBooked(String time) {

        for (int i = 0; i < appointmentCount; i++) {

            if (appointmentTimes[i].equals(time)) {

                return true;
            }
        }

        return false;
    }





    static boolean isStaffBooked(String staffName, String time) {

        for (int i = 0; i < appointmentCount; i++) {

            if (assignedStaff[i].equals(staffName)
                    && appointmentTimes[i].equals(time)) {

                return true;
            }
        }

        return false;
    }





    static void viewAppointments() {

        System.out.println("\n========== EXISTING APPOINTMENTS ==========");

        if (appointmentCount == 0) {

            System.out.println("No appointments found.");
            return;
        }

        for (int i = 0; i < appointmentCount; i++) {

            System.out.println("\nAppointment #" + (i + 1));
            System.out.println("--------------------------------------");
            System.out.println("Client Name : " + clientNames[i]);
            System.out.println("Service     : " + bookedServices[i]);
            System.out.println("Price       : PHP " + bookedPrices[i]);
            System.out.println("Staff       : " + assignedStaff[i]);
            System.out.println("Time        : " + appointmentTimes[i]);
            System.out.println("--------------------------------------");
        }
    }




    static void cancelAppointment() {

        System.out.println("\n========== CANCEL APPOINTMENT ==========");

        if (appointmentCount == 0) {

            System.out.println("There are no appointments to cancel.");
            return;
        }

        viewAppointments();

        System.out.print("\nEnter appointment number to cancel: ");
        int appointmentNumber = input.nextInt();
        input.nextLine();

        if (appointmentNumber < 1 ||
                appointmentNumber > appointmentCount) {

            System.out.println("Invalid appointment number.");
            return;
        }

        int index = appointmentNumber - 1;

        System.out.println("\nAppointment for "
                + clientNames[index]
                + " has been cancelled.");

        for (int i = index; i < appointmentCount - 1; i++) {

            clientNames[i] = clientNames[i + 1];
            bookedServices[i] = bookedServices[i + 1];
            bookedPrices[i] = bookedPrices[i + 1];
            assignedStaff[i] = assignedStaff[i + 1];
            appointmentTimes[i] = appointmentTimes[i + 1];
        }

        appointmentCount--;
    }
}
