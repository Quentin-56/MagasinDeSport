package controlleur;

import modele.Personne;
import org.hibernate.Hibernate;

import javax.persistence.Query;
import modele.ChefMagasin;

import java.util.List;

public class Connexion {
    /**
     * Vérifie si c'est l'identifiant du chef
     * @param identifiant champs entrée par l'utilisateur
     * @return vrai si c'est le chef faux sinon
     */
    public static boolean leChefSeConnecte(String identifiant){
        Query query = SetupEM.getEm().createQuery("from ChefMagasin chef where chef.idPersonne = (select personne.idPersonne from Personne personne where personne.identifiant = ?1 )");
        List<ChefMagasin> listP =  query.setParameter(1, identifiant).getResultList();
        if(listP.isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    /**
     * Recupere la saisie de lutilisateur et compare dans la base de donnée si il existe
     * @param identifiant champs entrée par l'utilisateur
     * @return  vrai si c'est un identifiant valide faux sinon
     */
    public static boolean verifierIdentifiant(String identifiant){
        Query query = SetupEM.getEm().createQuery(" from Personne personne where personne.identifiant = ?1 ");
        List<Personne> listP =  query.setParameter(1, identifiant).getResultList();
        if(listP.isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    /**
     * Est APPELE si verifier identifant est vrai, elle verifie si le mot de passe correspond a lidentifant
     * @param identifiant identifiant valide
     * @param motDePasse mot de passe donnee
     * @return vrai si il mot de passe est bon faux sinon
     */
    public static boolean verifierMotDePasse(String identifiant, String motDePasse){
        Query query = SetupEM.getEm().createQuery(" from Personne personne where personne.identifiant = ?1 and personne.motDePasse = ?2");
        query.setParameter(1, identifiant);
        query.setParameter(2, motDePasse);
        List<Personne> listP =  query.getResultList();
        if(listP.isEmpty()){
            return false;
        }else{
            return true;
        }
    }
}
