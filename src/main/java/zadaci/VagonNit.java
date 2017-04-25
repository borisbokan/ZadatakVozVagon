package zadaci;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.QueryBuilder;
import model.Vagon;
import model.Voz;

import javax.management.Query;
import java.sql.SQLException;
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


        synchronized (vagon){
            Poruka.naslov("Krece utovar vagona " + this.vagon.getOznaka());
            do{
                Poruka.text("tovari u vagon......>>");
                Random ra=new Random();
                long period=ra.nextInt(2) * 1000;
                this.vagon.setTeret(this.vagon.getTeret() + 1);
                try {
                    sleep(period);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Poruka.naslov("zavrsen utovar vagona " + this.vagon.getOznaka());

            }while (this.vagon.getTeret() < this.vagon.getNosivost());

           
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
            Dao<Voz, Integer> DAOvoz = DaoManager.createDao(db.getKonekcija(), Voz.class);

            QueryBuilder<Vagon,Integer> upit=DAOvagon.queryBuilder();
            List<Vagon> vagoni=upit.query();

            for (Vagon vagon : vagoni ) {
                if(vagon.getVoz().getNaziv().contentEquals("Voz1")){
                    VagonNit vag=new VagonNit(vagon.getOznaka().toString(),vagon);
                    vag.start();

                    try {
                        vag.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }

           /* VagonNit vag1=new VagonNit(vagoni.get(0).getOznaka(),vagoni.get(0));
            VagonNit vag2=new VagonNit(vagoni.get(1).getOznaka(),vagoni.get(1));
            VagonNit vag3=new VagonNit(vagoni.get(2).getOznaka(),vagoni.get(2));

            vag1.start();
            vag2.start();
            vag3.start();

            try {
                vag1.join();
                vag2.join();
                vag3.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
