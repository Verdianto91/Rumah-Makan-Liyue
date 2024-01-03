/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Rumah_Makan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Pesanan {
    public static void pesanMakanan(Connection connection) {
    try {
        Menu.lihatMenu(connection);

        System.out.print("Masukkan ID makanan yang ingin dipesan (Ketik 000 untuk membatalkan):");
        Scanner scanner = new Scanner(System.in);
        int idPesanan = scanner.nextInt();

        if (idPesanan == 000) {
            System.out.println("Pemesanan dibatalkan.");
            return;
        }

        // Cek apakah ID menu valid
        if (!Menu.cekMenuValid(connection, idPesanan)) {
            System.out.println("ID menu tidak valid. Masukkan ID yang benar.");
            return;
        }

        System.out.println("Masukkan jumlah pesanan:");
        int jumlahPesanan = scanner.nextInt();

        String query = "INSERT INTO pesanan (id_makanan, jumlah) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, idPesanan);
        preparedStatement.setInt(2, jumlahPesanan);
        preparedStatement.executeUpdate();

        System.out.println("Pesanan berhasil ditempatkan!");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    public static void lihatHistoryPesanan(Connection connection) {
    try {
        String query = "SELECT pesanan.id, menu.nama_makanan, pesanan.jumlah FROM pesanan JOIN menu ON pesanan.id_makanan = menu.id";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        System.out.println("===== History Pesanan =====");
        System.out.printf("%-4s | %-18s | %-6s%n", "ID", "Nama Makanan", "Jumlah");
        System.out.println("----------------------------");

        double totalPendapatan = 0;

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String namaMakanan = resultSet.getString("nama_makanan");
            int jumlah = resultSet.getInt("jumlah");

            // Ambil harga makanan dari database untuk menghitung total pendapatan
            double harga = Menu.getHargaMakanan(connection, id);

            System.out.printf("%-4d | %-18s | %-6d%n", id, namaMakanan, jumlah);

            // Hitung total pendapatan
            totalPendapatan += (harga * jumlah);
        }

        System.out.println("----------------------------");
        System.out.printf("Total Pendapatan: Rp%.2f%n", totalPendapatan);
        System.out.println("============================");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    public static boolean cekMenuTerkaitDenganPesanan(Connection connection, int idMenu) {
        try {
            String query = "SELECT COUNT(*) FROM pesanan WHERE id_makanan = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idMenu);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
