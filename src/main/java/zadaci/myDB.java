package zadaci;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by borcha on 22.04.17..
 */
public class myDB {

	private static final String dbpath="jdbc:sqlite:vozVagon.db";
        private  ConnectionSource conn = null;

        public myDB(){
            try {
                this.conn=new JdbcConnectionSource(dbpath);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        //Konekcija baze
        public  ConnectionSource getKonekcija(){
            return conn;
        }

        //Zatvaranje konekcije
     public void zatvoriKonekciju(){
         try {
             this.conn.close();
         } catch (IOException e) {
             e.printStackTrace();
         }
     }

}
