package Rumah_Makan;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        try {
            Connection connection = DatabaseConnector.getConnection();

            while (true) {
                // Pilihan peran pengguna
                System.out.println("Pilih peran pengguna:");
                System.out.println("1. Kasir");
                System.out.println("2. Pelanggan");
                System.out.println("3. Keluar");

                Scanner scanner = new Scanner(System.in);
                System.out.print("Pilihan : ");
                int peran = scanner.nextInt();

                switch (peran) {
                    case 1:
                        Kasir.menuKasir(connection);
                        break;
                    case 2:
                        Pelanggan.menuPelanggan(connection);
                        break;
                    case 3:
                        System.out.println("Terima kasih! Selamat tinggal.");
                        System.exit(0);
                    default:
                        System.out.println("Peran tidak valid.");
                        break;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
