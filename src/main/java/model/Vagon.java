package model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by androiddevelopment on 25.4.17..
 */
@DatabaseTable(tableName = "vagon")
public class Vagon {

        public static final String POLJE_OZNAKA= "oznaka";
        public static final String POLJE_OPIS= "opis";
        public static final String POLJE_NOSIVOST= "nosivost";
        public static final String POLJE_TERET= "teret";

        @DatabaseField(generatedId = true)
        private int id;
        @DatabaseField(columnName = POLJE_OZNAKA)
        private String oznaka;
        @DatabaseField(columnName = POLJE_OPIS)
        private String opis;
        @DatabaseField(columnName = POLJE_NOSIVOST)
        private Double nosivost;
        @DatabaseField(columnName = POLJE_TERET)
        private Double teret=0.0d;

        @DatabaseField(foreign = true,foreignAutoRefresh = true)
        private Voz voz;

        public Vagon(){
        }

        public Vagon(String _oznaka,String _opis,Double _nosivost){
            this.oznaka=_oznaka;
            this.opis=_opis;
            this.nosivost=_nosivost;

        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOznaka() {
            return oznaka;
        }

        public void setOznaka(String oznaka) {
            this.oznaka = oznaka;
        }

        public String getOpis() {
            return opis;
        }

        public void setOpis(String opis) {
            this.opis = opis;
        }

        public Double getNosivost() {
            return nosivost;
        }

        public void setNosivost(Double nosivost) {
            this.nosivost = nosivost;
        }

        public Double getTeret() {
            return teret;
        }

        public void setTeret(Double teret) {
            this.teret = teret;
        }

        public Voz getVoz() {
            return voz;
        }

        public void setVoz(Voz voz) {
            this.voz = voz;
        }

        public String toString(){
            return " vagon ( " + this.id + " " + this.oznaka + " " + this.opis + " " + this.nosivost + ")";
        }

}
