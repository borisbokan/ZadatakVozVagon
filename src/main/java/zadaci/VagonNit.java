package zadaci;

import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.stmt.QueryBuilder;
import model.Vagon;
import model.Voz;

import javax.management.Query;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by androiddevelopment on 25.4.17..
 */
public class VagonNit extends Thread {


    private Vagon vagon;
    private String oznaka;


    public VagonNit(String _oznaka,Vagon _vagon){
        this.oznaka=_oznaka;
        this.vagon=_vagon;

    }


    private void utovar(){

        synchronized (vagon) {

            Poruka.naslov("Krece utovar vagona " + vagon.getOpis());
        }

            while (vagon.getTeret() < vagon.getNosivost()){   //Proveravam utovar tj nosivost

                synchronized (vagon){

                    Poruka.text("tovari u vagon......>> " + vagon.getOpis());
                }

                Random ra=new Random();
                long period=ra.nextInt(2) * 1000;

                    vagon.setTeret(vagon.getTeret() + 1);

                synchronized (vagon){

                    Poruka.text("Vagon  " + vagon.getOpis() + " natovaren sa:  " + vagon.getTeret() + " tona.");
                }
                    try {
                        sleep(period);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

              

            }


    }


    @Override
    public void run(){
            utovar();

    }


 //*********************************************TEST****************************
    public static void main(String[] args){

        myDB db = new myDB();

        try {

            Dao<Vagon, Integer> DAOvagon = DaoManager.createDao(db.getKonekcija(), Vagon.class);
            Dao<Voz, Integer> DAOVoz = DaoManager.createDao(db.getKonekcija(), Voz.class);
            

            QueryBuilder<Vagon,Integer> upit=DAOvagon.queryBuilder();
            QueryBuilder<Voz,Integer> upitVoz=DAOVoz.queryBuilder();
            upitVoz.where().eq(Voz.POLJE_NAZIV,"Voz1");
            List<Vagon> vagoni=upit.join(upitVoz).query();

            
            //Array lista generickog tipa Thread koja uzima vrednsoti napravljenih tredova
            //kako bi na njih mogli nakaciti join method.
            ArrayList<Thread> genTre=new ArrayList<Thread>();

            int brojac =1; //Brojac koji se appenduje na naziv jedinstvenog threda
            for (Vagon vagon : vagoni ) {
                if(vagon.getVoz().getNaziv().contentEquals("Voz1")){
                    VagonNit vag = new VagonNit(vagon.getOznaka() + " " + brojac, vagon);
                    vag.start();
                    Poruka.text("Nit " + vag.getName() + " poceo da radi!");

                    genTre.add(vag);
                                  
                    brojac++;

                }
            }

            //Dodajem join na filtriranim  tj prethodno uradjenim thredovima
            for (Thread th : genTre) {
                try {
                    th.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Poruka.text(".......Kraj utovara....");
                  
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
