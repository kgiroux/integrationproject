package fr.esigelec.gsi.quizintegration.Objects;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;

/**
 * Created by Kevin PACE
 */
public class Personne
{
    public Personne ()
    {
        nom = "";
        prenom = "";
        mail ="";
        mdp = "";
    }

    /* Attribute */
    private int id;
    private String nom;
    private String prenom;
    private String mail;
    private String mdp;

    /* Getters & Setters */
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {

        MessageDigest digest;
        String encryptMdp = "";
        try{
            digest = MessageDigest.getInstance ("MD5");
            byte[] arrayreturn = digest.digest (mdp.getBytes ());
            encryptMdp = String.format("%032X",new BigInteger (1,arrayreturn));
        }catch(Exception e){
            Log.e("Error ", e.getMessage ());
        }
        this.mdp = encryptMdp;

    }

    public void JSONObjectToPersonne(JSONObject obj){
        if(null != obj){
            try{

                this.id = obj.getInt ("id");
                this.prenom = obj.getString ("prenom");
                this.mdp = obj.getString ("mdp");
                this.nom = obj.getString ("nom");
                this.mail = obj.getString ("mail");
            }catch(JSONException jsonE){
                Log.e ("ERREUR",jsonE.getMessage ());
            }
        }
    }


    public HashMap<String,String> PersonneToHashMap(){
        HashMap<String, String> returnHashMap = new HashMap<> ();
        if(!"".equals (nom)){
            returnHashMap.put ("nom",nom);
        }
        if(!"".equals (prenom)){
            returnHashMap.put ("prenom",prenom);
        }
        if(!"".equals (mail)){
            returnHashMap.put ("mail",mail);
        }
        if(!"".equals (mdp)){
            returnHashMap.put("mdp",mdp);
        }
        return returnHashMap;
    }

    @Override
    public String toString() {
        return "Personne{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", mail='" + mail + '\'' +
                ", mdp='" + mdp + '\'' +
                '}';
    }
}
