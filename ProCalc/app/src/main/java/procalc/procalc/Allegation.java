package procalc.procalc;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by section11 on 13/03/16.
 */
public class Allegation {
    private String who, when, where, what;
    private ArrayList<Bitmap> evidencePhotos = new ArrayList<Bitmap>();
    private ArrayList<List<Byte>> bitmapBytes = new ArrayList<List<Byte>>();
    private ArrayList<String> base64;

    public Allegation(String who, String when, String where, String what, ArrayList<String> base) {
        this.who = who;
        this.when = when;
        this.where = where;
        this.what = what;
        this.base64 = new ArrayList<String>(base);
    }

    public String getWho() {
        return this.who;
    }

    public String getWhen() {
        return this.when;
    }

    public String getWhere() {
        return this.where;
    }

    public String getWhat() {
        return this.what;
    }

    public void setWho(String s) {
        this.who = s;
    }

    public void setWhen(String s) {
        this.when = s;
    }

    public void setWhere(String s) {
        this.where = s;
    }

    public void setWhat(String s) {
        this.what = s;
    }

    public void setBase64(ArrayList<String> s){
        this.base64 = new ArrayList<String>(s);
    }

    public ArrayList<String> getBase64(){
        return this.base64;
    }

    public ArrayList<List<Byte>> getBitmapBytes() {
        return this.bitmapBytes;
    }

    public ArrayList<Bitmap> getEvidencePhotos(){
        return this.evidencePhotos;
    }

    public void setEvidencePhotos(ArrayList<Bitmap> evPhotos){

        this.evidencePhotos = new ArrayList<Bitmap>(evPhotos);
    }

    public void setBitmapBytes(ArrayList<List<Byte>> bitmapBytes){
        this.bitmapBytes = new ArrayList<List<Byte>>(bitmapBytes);
    }


}
