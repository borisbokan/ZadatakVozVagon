package zadaci;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.QueryBuilder;
import model.Vagon;
import model.Voz;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by androiddevelopment on 25.4.17..
 */
public class Zadatak3IzmenaVrednosti {

    private static myDB db=null;
    private static Dao<Voz,Integer> DAOVoz=null;
    private static Dao<Vagon,Integer> DAOvagon=null;


    public static void main(String[] args){
        db=new myDB();

        try {
            DAOVoz= DaoManager.createDao(db.getKonekcija(), Voz.class);
            DAOvagon=DaoManager.createDao(db.getKonekcija(), Vagon.class);

            //Prikaz svih vagona
            List<Vagon> vagoni=DAOvagon.queryForAll();
            Poruka.naslov("prikaz svih vagona pre izmene");
            for (Vagon vag : vagoni) {
                Poruka.text(vag.toString() + " > Voz " + vag.getVoz().getOznaka());
            }
            Poruka.linija40();
            Poruka.noviRed();

            QueryBuilder<Vagon,Integer> upit=DAOvagon.queryBuilder();
            Vagon vagon =upit.where().eq(Vagon.POLJE_OPIS,"Restoran").queryForFirst();
            vagon.setOpis("Za sedenje");
            DAOvagon.update(vagon);

            //Prikaz svih vagona nakon izmene
            List<Vagon> vagoniNakonIzmene=DAOvagon.queryForAll();
            Poruka.naslov("prikaz svih vagona nakon izmene");
            for (Vagon vag : vagoniNakonIzmene) {
                Poruka.text(vag.toString() + " > Voz " + vag.getVoz().getOznaka());
            }
            Poruka.linija40();
            Poruka.noviRed();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            db.zatvoriKonekciju();
        }



    }
}
