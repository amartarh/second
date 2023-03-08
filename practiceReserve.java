class Reserve {
    static int availableseats = 10;

    synchronized void reserveseat(int requestedSeats) {
        System.out.println(Thread.currentThread().getName() + " entered.");
        System.out.println("availableseats: " + availableseats + " requestedseats " + requestedSeats);
        if (requestedSeats <= availableseats) {
            System.out.println("seats available. Reserve now");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Interrupt exception");
            }
            availableseats = availableseats - requestedSeats;
            System.out.println(requestedSeats + " seats reserved");
        } else {
            System.out.println("requested seats not available");
        }

        System.out.println(Thread.currentThread().getName() + " leaving");
        System.out.println("-----------------------------------");
    }
}

class Person extends Thread {
    Reserve reserve;
    int requestedseats;

    Person(Reserve reserve, int requestedseats) {
        this.requestedseats = requestedseats;
        this.reserve = reserve;
    }

    public void run() {
        reserve.reserveseat(requestedseats);
    }
}

public class practiceReserve {
    public static void main(String[] args) {
        Reserve reserve = new Reserve();
        Person thread1 = new Person(reserve, 5);
        thread1.start();
        Person thread2 = new Person(reserve, 4);
        thread2.start();
        Person thread3 = new Person(reserve, 3);
        thread3.start();
    }
}
// winter
// summer