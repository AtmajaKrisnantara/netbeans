
package tokoapp;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import java.util.Scanner;
import org.bson.Document;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

public class TokoApp {

    public static void main(String[] args) {
        
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("toko");
        MongoCollection barang = db.getCollection("barang");
        MongoCollection summary = db.getCollection("summary");
        
        long jumlah = barang.count();
        Scanner in = new Scanner(System.in);
        
        while(true){
        System.out.print("Masukkan Kode Barangnya: ");
        String kode = in.nextLine();
        if(kode.equals(""))break;
        System.out.print("Masukkan Nama Barangnya: ");
        String nama = in.nextLine();
        System.out.print("Masukkan Harga Barangnya: ");
        int harga = in.nextInt();
        
        Document doc = new Document();
        doc.append("kode", kode);
        doc.append("nama", nama);
        doc.append("harga", harga);
        
        barang.insertOne(doc);
        
        jumlah++;
        summary.updateOne(eq("kode", "barang"), set("jumlah", jumlah));
        
        in.nextLine();
        System.out.print("Data tersimpan, tekan <ENTER> untuk lanjut!");
        in.nextLine();
    }
        
  }
}
