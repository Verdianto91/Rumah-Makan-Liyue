/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Rumah_Makan;

import java.sql.Connection;
import java.util.Scanner;

public class Pelanggan {
    public static void menuPelanggan(Connection connection) {
        while (true) {
            System.out.println("Menu Pelanggan:");
            System.out.println("1. Lihat Menu");
            System.out.println("2. Pesan Makanan");
            System.out.println("3. Kembali");

            Scanner scanner = new Scanner(System.in);
            System.out.print("Pilihan : ");
            int pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    Menu.lihatMenu(connection);
                    break;
                case 2:
                    Pesanan.pesanMakanan(connection);
                    break;
                case 3:
                    System.out.println("Kembali ke menu peran pengguna.");
                    return; // Kembali ke menu peran pengguna
                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }
        }
    }
}

