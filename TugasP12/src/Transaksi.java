import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.cj.jdbc.StatementWrapper;
import com.mysql.cj.protocol.Resultset;

public class Transaksi extends Barang {
    public String kasir;
    static Connection conn;
    String url = "jdbc:mysql://localhost:3306/pembayaran";
    String user = "root";
    String password = "";

    Scanner input = new Scanner(System.in);
    @Override
    public void noFaktur() {
        System.out.println(">> No Faktur  = ");
        noFaktur = input.nextLine();
    }
    
    @Override
    public void namaBarang() {
        System.out.println(">> Nama Barang  = ");
        namaBarang = input.nextLine();
        namaBarang = namaBarang.toUpperCase();
    }
    
    @Override
    public void hargaBarang() {  
        System.out.println(">> Harga Barang  = ");
        hargaBarang = input.nextInt();
    }

    @Override
    public void jumlah() {
        System.out.println(">> Banyak Barang  = ");
        jumlah = input.nextInt();
    }

    @Override
    public void subTotal() {
        subTotal = hargaBarang * jumlah;
    }
 
    @Override
    public void discount() {
        if (subTotal >= 250000){
            discount = subTotal * 30/100;
        }

        else if (subTotal >= 200000 && subTotal < 250000){
            discount = subTotal * 20/100; 
        }

        else if (subTotal >= 150000 && subTotal < 200000){
            discount = subTotal * 10/100;
        }

        else if (subTotal >= 100000 && subTotal < 150000){
            discount = subTotal * 5/100;
        }

        else {
            discount = 0;
        }
    }
 
    @Override
    public void totalHarga() {
        totalHarga = subTotal - discount;
    }

    public void namaKasir(){
        System.out.println(">> Nama Kasir  = ");
        kasir = input.nextLine();
    }

    public void tanggal()
    {
        Date tanggal = new Date();
        SimpleDateFormat dt = new SimpleDateFormat("EE, dd/MM/yyyy");
        System.out.println(dt.format(tanggal));
    }

    private void waktu()
    {
        Date waktu = new Date();    
        SimpleDateFormat tm = new SimpleDateFormat("HH:mm:ss");  
        System.out.println(tm.format(waktu));
    }

    public void output()throws SQLException{
        try{
            System.out.println("======================================");
            System.out.println("-----------CATATAN PEMBELIAN----------");
            System.out.println("======================================");
            System.out.println("Masukkan No barang : ");
            number = input.nextInt();
            System.out.println("Nama Kasir             = " + kasir);   
            tanggal();
            waktu();
            System.out.println("");
            System.out.println("No Faktur              = " + noFaktur);
            System.out.println("Nama Barang            = " + namaBarang);
            System.out.println("Harga                  = " + hargaBarang);
            System.out.println("Banyak Barang          = " + jumlah);
            System.out.println("Sub Total              = " + subTotal);
            System.out.println("Discount               = " + discount);
            System.out.println(">> Total Transaksi     = " + totalHarga);
            String sql = "INSERT INTO mytable (namakasir, nofa, namabarang, hargabarang, jumlah, sub, disk, total, number) VALUES ('"+kasir+"','"+noFaktur+"','"+namaBarang+"','"+hargaBarang+"','"+jumlah+"','"+subTotal+"','"+discount+"','"+totalHarga+"','"+number+"')";
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            statement.execute(sql);
            System.out.println("Data Berhasil Ditambahkan");
        }
        catch(InputMismatchException ex){
            System.err.println("Input lagi");
        }
        catch(SQLException e){
            System.err.println("Terjadi Kesalahan");
        }
    }
}