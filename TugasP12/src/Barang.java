import java.sql.SQLException;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.cj.jdbc.StatementWrapper;
import com.mysql.cj.protocol.Resultset;
public class Barang implements Penjualan  {
    public String noFaktur;
    public String namaBarang;
    public Integer number;
    public Integer hargaBarang;
    public Integer jumlah;
    public Integer subTotal;
    public Integer discount;
    public Integer totalHarga; 
    static Connection conn;
    String url = "jdbc:mysql://localhost:3306/pembayaran";
    String user = "root";
    String password = "";
    Scanner scanner = new Scanner(System.in);
    public void noFaktur() {
        noFaktur = null;
    }
    
    public void namaBarang() {
        namaBarang = null;
    }
    
    public void hargaBarang() {  
        hargaBarang = null;
    }

    public void jumlah() {
        jumlah = null;
    }

    public void subTotal() {
        subTotal = null;
    }
 
    public void discount() {
        discount = null;
    }
 
    public void totalHarga() {
        totalHarga = null;
    }
    void seeData() throws SQLException{
        String program1 = "\n See Data";
        System.out.println(program1.toUpperCase());
        try{
            String sql = "SELECT * FROM mytable";
            conn = DriverManager.getConnection(url, user, password);
            Statement state = conn.createStatement();
            ResultSet hasil = state.executeQuery(sql);
            while(hasil.next()){
                System.out.print("Number ke : ");
                System.out.println(hasil.getInt("number"));
                System.out.print("Pegawai : ");
                System.out.println(hasil.getString("namakasir"));
                System.out.print("Barang : ");
                System.out.println(hasil.getString("namabarang"));
                System.out.print("nofaktur: ");
                System.out.println(hasil.getString("nofa"));
                System.out.print("Harga: ");
                System.out.println(hasil.getInt("hargabarang"));
                System.out.print("Jumlah : ");
                System.out.println(hasil.getInt("jumlah"));
                System.out.print("Subtotal : ");
                System.out.println(hasil.getInt("sub"));
                System.out.print("Harga Diskon : ");
                System.out.println(hasil.getInt("disk"));
                System.out.print("Bayaran: ");
                System.out.println(hasil.getInt("total"));
            }
        }
        catch(SQLException ex){
            System.out.println("Tidak Teridentifikasi");
        }
    }
    void deletedata()throws SQLException{
        String program5 = "\n Hapus Data\t";
        System.out.println(program5.toUpperCase());
        try{
            seeData();
            System.out.print("Inputkan Number : ");
            Integer number = Integer.parseInt(scanner.nextLine());
            String sql = "DELETE FROM mytable WHERE number = "+number;
            conn = DriverManager.getConnection(url, user, password);
            Statement state = conn.createStatement();
            if(state.executeUpdate(sql) > 0){
                System.out.println("FINISHED CLEAR DATA");
            }
        }
        catch(SQLException e){
            System.err.println("TERJADI KESALAHAN");
        }
    }
    void update()throws SQLException{
        String program3 = "\n Update Data\t";
        System.out.println(program3.toUpperCase());
        try{
            seeData();
            System.out.println("Masukkan Number Data : ");
            Integer number = Integer.parseInt(scanner.nextLine());
            String sql = "SELECT * FROM mytable WHERE number = "+number;
            Statement state = conn.createStatement();
            ResultSet hasil = state.executeQuery(sql);
            if(hasil.next()){
                System.out.println("Nama Barang ["+hasil.getString("namabarang")+"]\t : ");
                String nama = scanner.nextLine();
                sql = "UPDATE mytable SET namabarang = '"+nama+"' WHERE number = '"+number+"'";
                if(state.executeUpdate(sql) > 0){
                    System.out.println("Complated change data");
                }
            }
            state.close();
        }
        catch(SQLException e){
            System.err.println("Terjadi Kesalahan");
            System.err.println(e.getMessage());
        }
    }
    void cari() throws SQLException{
        String program4 = "\n Search Data";
        System.out.println(program4.toUpperCase());
        try{
            System.out.println("Faktur : ");
            String keyword = scanner.nextLine();
            String sql = "SELECT * FROM mytable WHERE nofa LIKE '%"+keyword+"'";
            conn = DriverManager.getConnection(url, user, password);
            Statement state = conn.createStatement();
            ResultSet hasil = state.executeQuery(sql);
            while(hasil.next()){
                System.out.print("Number ke : ");
                System.out.println(hasil.getInt("number"));
                System.out.print("Pegawai : ");
                System.out.println(hasil.getString("namakasir"));
                System.out.print("Barang : ");
                System.out.println(hasil.getString("namabarang"));
                System.out.print("nofaktur: ");
                System.out.println(hasil.getString("nofa"));
                System.out.print("Harga: ");
                System.out.println(hasil.getInt("hargabarang"));
                System.out.print("Jumlah : ");
                System.out.println(hasil.getInt("jumlah"));
                System.out.print("Subtotal : ");
                System.out.println(hasil.getInt("sub"));
                System.out.print("Harga Diskon : ");
                System.out.println(hasil.getInt("disk"));
                System.out.print("Bayaran: ");
                System.out.println(hasil.getInt("total"));
            }
        }
        catch(SQLException ex){
            System.out.println("Tidak Teridentifikasi");
        } 
    }
}