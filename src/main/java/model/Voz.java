package model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by androiddevelopment on 25.4.17..
 */
@DatabaseTable(tableName = "voz")
public class Voz {

        public static final String POLJE_OZNAKA="oznaka";
        public static final String POLJE_NAZIV="naziv";

        @DatabaseField(generatedId = true)
        private int id;
        @DatabaseField(columnName = POLJE_OZNAKA)
        private String oznaka;
        @DatabaseField(columnName = POLJE_NAZIV)
        private String naziv;
        @ForeignCollectionField(columnName = "voz")
        ForeignCollection<Vagon> vagoni;

        public Voz(){

        }

        public Voz(String _naziv,String _oznaka){
            this.naziv=_naziv;
            this.oznaka=_oznaka;

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

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public ForeignCollection<Vagon> getVagoni() {
        return vagoni;
    }

    public void setVagoni(ForeignCollection<Vagon> vagoni) {
        this.vagoni = vagoni;
    }


    public  String toString(){
        return "Voz ( " + this.id   + this.naziv + " - " + this.oznaka + " )";
    }
}
