package zadaci;

import com.j256.ormlite.table.TableUtils;
import model.Vagon;
import model.Voz;

import java.sql.SQLException;

/**
 * Created by androiddevelopment on 25.4.17..
 */
public class Zadatak1KreiranjeTabela {

    private static myDB db=null;

    public static void main(String[] args){
        db=new myDB();

        try {
            //Brisanje tabela
            TableUtils.dropTable(db.getKonekcija(), Vagon.class,true);
            TableUtils.dropTable(db.getKonekcija(), Voz.class,true);


            //Pravim tabele
            TableUtils.createTable(db.getKonekcija(),Voz.class);
            TableUtils.createTable(db.getKonekcija(),Vagon.class);


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
