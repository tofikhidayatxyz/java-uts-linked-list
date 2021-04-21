import java.util.Scanner;
import com.uts.controllers.HotelController;


public class Main {

    /**
     * Project for creating hotel data with using
     */

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        HotelController controller = new HotelController();
        // running seed
        controller.runSeed();
        // action
        Integer prompt = 1;

        while (prompt > 0 && prompt < 7) {
            System.out.println("===== Daftar Menu =====");
            System.out.println("1. Daftar ruangan");
            System.out.println("2. Tambah Ruangan");
            System.out.println("3. Hapus ruangan");
            System.out.println("4. Booking ruangan");
            System.out.println("5. Checkout ruangan");
            System.out.println("6. Detail per ruangan");


            System.out.print("Masukan nomor menu : ");
            try {
                prompt = Integer.parseInt(scan.nextLine());
            } catch(Exception e) {
                prompt = 0;
            }

            switch (prompt) {
                case 1 -> controller.viewRoom();
                case 2 -> controller.addRoom(scan);
                case 3 -> controller.deleteRoom(scan);
                case 4 -> controller.allocateuserToRoom(scan);
                case 5 -> controller.checkoutRoom(scan);
                case 6 -> controller.detailEveryRoom(scan);
            }
        }
    }
}
