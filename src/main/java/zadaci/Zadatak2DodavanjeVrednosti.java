package zadaci;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import model.Vagon;
import model.Voz;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by androiddevelopment on 25.4.17..
 */
public class Zadatak2DodavanjeVrednosti {

    private static myDB db=null;
    private static Dao<Voz,Integer> DAOVoz=null;
    private static Dao<Vagon,Integer> DAOvagon=null;

    public static void main(String[] args){
        db=new myDB();
        try {
            DAOVoz= DaoManager.createDao(db.getKonekcija(),Voz.class);
            DAOvagon=DaoManager.createDao(db.getKonekcija(),Vagon.class);


            Voz voz1=new Voz("Voz1","Dizel");
            DAOVoz.create(voz1);
            Voz voz2=new Voz("Voz2","Elektricni");
            DAOVoz.create(voz2);

            //Ubacivanje vagona i spajanje sa vozom
            Vagon vagon1=new Vagon("vagon1","Za prenos goriva",10.00);
            vagon1.setVoz(voz1);
            DAOvagon.create(vagon1);

            Vagon vagon2=new Vagon("vagon2","Za prenos toksicnih materija",5.00);
            vagon2.setVoz(voz1);
            DAOvagon.create(vagon2);

            Vagon vagon3=new Vagon("vagon3","Za prenos psenice",20.00);
            vagon3.setVoz(voz1);
            DAOvagon.create(vagon3);

            Vagon vagon4=new Vagon("vagon4","Za spavanje",5.00);
            vagon4.setVoz(voz2);
            DAOvagon.create(vagon4);

            Vagon vagon5=new Vagon("vagon5","Restoran",3.00);
            vagon5.setVoz(voz2);
            DAOvagon.create(vagon5);


            List<Voz> vozovi=DAOVoz.queryForAll();
            Poruka.naslov("Prikaz liste svih unesenih vozova");
            for (Voz voz : vozovi) {
                Poruka.text(voz.toString());
            }
            Poruka.linija40();
            Poruka.noviRed();

            List<Vagon> vagoni=DAOvagon.queryForAll();
            Poruka.naslov("Spisak svih vagona");
            for (Vagon vag : vagoni) {
                Poruka.text(vag.toString() + " > Voz " + vag.getVoz().getOznaka());
            }
            Poruka.linija40();
            Poruka.noviRed();





        } catch (SQLException e) {
            e.printStackTrace();
        }




    }

}
