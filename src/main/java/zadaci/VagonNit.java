package zadaci;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
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

            do{
                synchronized (vagon) {

                    Poruka.text("tovari u vagon......>>");
                }
                Random ra=new Random();
                long period=ra.nextInt(2) * 1000;
                synchronized (vagon) {
                    vagon.setTeret(vagon.getTeret() + 1);
                }
                    try {
                        sleep(period);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                synchronized (vagon) {
                    Poruka.naslov("zavrsen utovar vagona " + vagon.getOznaka());
                }

            }while (vagon.getTeret() < vagon.getNosivost());


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
            

            QueryBuilder<Vagon,Integer> upit=DAOvagon.queryBuilder();
            List<Vagon> vagoni=upit.query();

            ArrayList<Thread> genTre=new ArrayList<Thread>();

            for (Vagon vagon : vagoni ) {
                if(vagon.getVoz().getNaziv().contentEquals("Voz1")){
                    VagonNit vag = new VagonNit(vagon.getOznaka(), vagon);
                    vag.start();

                    genTre.add(vag);


                }
            }

            //
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
