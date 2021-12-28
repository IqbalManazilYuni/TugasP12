import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.StatementWrapper;
import com.mysql.cj.protocol.Resultset;

public class Program {
    static Connection conn;
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/pembayaran";
        String user = "root";
        String password = "";
        boolean keadaan = true;
        String choice;
        Scanner inputuser = new Scanner(System.in);
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Berhasil Terkoneksi");
            Barang pjl = new Barang();
            while(keadaan){
                System.out.println("======================");
                System.out.println("|     MENU PROGRAM   |");
                System.out.println("======================");
                System.out.println("1. Lihat Database ");
                System.out.println("2. Input Database ");
                System.out.println("3. Update Database ");
                System.out.println("4. Cari Database ");
                System.out.println("5. Hapus Database ");
                System.out.print(" Masukkan Pilihan Program : ");
                choice = inputuser.next();
                switch(choice){
                    case "1":
                    pjl.seeData();
                    break;
                    case "2":
                    Transaksi transaksi = new Transaksi();
                    transaksi.namaKasir();
                    transaksi.noFaktur();
                    transaksi.namaBarang();
                    transaksi.hargaBarang();
                    transaksi.jumlah();
                    transaksi.subTotal();
                    transaksi.discount();
                    transaksi.totalHarga();
                    transaksi.output();
                    break;
                    case "3":
                    pjl.update();
                    break;
                    case "4":
                    pjl.cari();
                    break;
                    case "5":
                    pjl.deletedata();
                    break;
                    default :
                    System.err.println("MENU TIDAK TERSEDIA");
                }
                System.out.println("\n Close Program [y/n]? : ");
                choice = inputuser.next();
                keadaan = choice.equalsIgnoreCase("n");
            }
            System.out.println("THANKS");
        }
        catch(ClassNotFoundException ex){
            System.err.println("DRIVER Tidak Ditemukan");
            System.exit(0);
        }
        catch(SQLException e){
            System.err.println("TIDAK TERHUBUNG");
        }
    }
}