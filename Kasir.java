/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Rumah_Makan;

/**
 *
 * 
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Kasir {
    public static void menuKasir(Connection connection) {
        while (true) {
            System.out.println("Menu Kasir:");
            System.out.println("1. Tambah Menu");
            System.out.println("2. Hapus Menu");
            System.out.println("3. Lihat History Pesanan");
            System.out.println("4. Kembali");

            Scanner scanner = new Scanner(System.in);
            System.out.print("Pilihan : ");
            int pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    Menu.tambahMenu(connection);
                    break;
                case 2:
                    Menu.hapusMenu(connection);
                    break;
                case 3:
                    Pesanan.lihatHistoryPesanan(connection);
                    break;
                case 4:
                    System.out.println("Kembali ke menu peran pengguna.");
                    return; // Kembali ke menu peran pengguna
                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }
        }
    }
}
