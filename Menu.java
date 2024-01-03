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

public class Menu {
    public static void tambahMenu(Connection connection) {
        try {
            System.out.println("Masukkan nama makanan:");
            Scanner scanner = new Scanner(System.in);
            String namaMakanan = scanner.nextLine();

            System.out.println("Masukkan harga makanan:");
            double harga = scanner.nextDouble();

            String query = "INSERT INTO menu (nama_makanan, harga) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, namaMakanan);
            preparedStatement.setDouble(2, harga);
            preparedStatement.executeUpdate();

            System.out.println("Menu berhasil ditambahkan!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void hapusMenu(Connection connection) {
    try {
        lihatMenu(connection);

        System.out.println("Masukkan ID menu yang ingin dihapus (Ketik 000 untuk membatalkan):");
        Scanner scanner = new Scanner(System.in);
        int idMenu = scanner.nextInt();

        if (idMenu == 000) {
            System.out.println("Penghapusan dibatalkan.");
            return;
        }

        // Cek apakah ID menu valid
        if (!cekMenuValid(connection, idMenu)) {
            System.out.println("ID menu tidak valid. Masukkan ID yang benar.");
            return;
        }

        // Periksa apakah ada pesanan yang menggunakan menu tersebut
        if (Pesanan.cekMenuTerkaitDenganPesanan(connection, idMenu)) {
            System.out.println("Tidak dapat menghapus menu yang memiliki pesanan terkait.");
        } else {
            // Hapus menu jika tidak ada pesanan terkait
            String query = "DELETE FROM menu WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idMenu);
            preparedStatement.executeUpdate();

            System.out.println("Menu berhasil dihapus!");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    public static void lihatMenu(Connection connection) {
        try {
            String query = "SELECT * FROM menu";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("======== Rumah Makan Liyue ========");
            System.out.printf("%-4s | %-14s | %-6s%n", "ID", "Nama Makanan", "Harga");
            System.out.println("-----------------------------------");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String namaMakanan = resultSet.getString("nama_makanan");
                int harga = (int) resultSet.getDouble("harga"); // Mengubah tipe data harga menjadi int
                System.out.printf("%-4d | %-14s | Rp %-6d%n", id, namaMakanan, harga);
            }

            System.out.println("===================================");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static double getHargaMakanan(Connection connection, int idMakanan) {
        try {
            String query = "SELECT harga FROM menu WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idMakanan);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getDouble("harga");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public static boolean cekMenuValid(Connection connection, int idMenu) {
    try {
        String query = "SELECT COUNT(*) FROM menu WHERE id = ?";
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
