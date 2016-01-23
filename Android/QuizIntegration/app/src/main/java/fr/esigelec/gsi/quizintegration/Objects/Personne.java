package fr.esigelec.gsi.quizintegration.Objects;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Created by Kevin PACE
 */
public class Personne implements Comparator<Personne> , Comparable<Personne>
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
    private String noEncryMdp;
    private int score;
	private int classement;

	public int getClassement ()
	{
		return classement;
	}

	public void setClassement (int classement)
	{
		this.classement = classement;
	}

	public int getScore ()
	{
		return score;
	}

	public void setScore (int score)
	{
		this.score = score;
	}

	public String getNoEncryMdp(){return noEncryMdp;}

    public void setNoEncryMdp(String noEncryMdp){this.noEncryMdp = noEncryMdp;}
    public String getMail(){return mail;}


    /* Getters & Setters */
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        if(null != nom && !"".equals (nom))
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
        if(null != prenom && !"".equals (prenom))
            this.prenom = prenom;
    }

    public void setMail(String mail) {
        if(null != mail && !"".equals (mail))
            this.mail = mail;
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

    /* Convertion methods */
    public void JSONObjectToPersonne(JSONObject obj){
        if(null != obj){
            try{

                this.setId( obj.getInt ("id"));
                this.setPrenom(obj.getString ("prenom"));
                this.setMdp(obj.getString ("mdp"));
                this.setNom(obj.getString ("nom"));
                this.setMail(obj.getString ("mail"));
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

    public int fillPersonneByIndex(int index, String value){
        switch(index){
            case 1 :
                if("".equals(value)){
                    return -1;
                }else{
                    this.nom = value;
                }

                break;
            case 2 :
                if("".equals(value)){
                    return -1;
                }else{
                    this.prenom = value;
                }


                break;
            case 3 :
                if("".equals(value)){
                    return -1;
                }else{
                    this.mail = value;
                }
                break;
            case 4 :
            case 5 :
                if("".equals(mdp))
                {
                    this.mdp = value;
                }else{
                    if(!value.equals(this.mdp)){
                        return -1;
                    }else{
                        this.setMdp(this.mdp);
                    }
                }
                break;
        }
        return 0;
    }

	@Override
	public int compare (Personne lhs, Personne rhs)
	{
		return rhs.getScore () - lhs.getScore ();
	}

	@Override
	public int compareTo (Personne another)
	{
		return another.getScore () - this.getScore ();
	}
}
