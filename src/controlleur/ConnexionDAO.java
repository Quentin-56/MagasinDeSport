package controlleur;

import modele.Personne;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import modele.ChefMagasin;

public class ConnexionDAO {

    private EntityManager entityManager;

    //Getters et setters
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    /**
     * Vérifie si c'est l'identifiant du chef
     * @param identifiant champs entrée par l'utilisateur
     * @return vrai si c'est le chef faux sinon
     */
    public boolean leChefSeConnecte(String identifiant){
        ChefMagasin chef = null;
        try {
            Query query = entityManager.createQuery("from ChefMagasin chef where chef.identifiant =  ?1");
            chef = (ChefMagasin) query.setParameter(1, identifiant).getSingleResult();

        }catch(Exception e)
        {
            System.out.println("Ce n'est pas un chef");
        }

        if(chef == null)
        {
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
    public boolean verifierIdentifiant(String identifiant){

        Personne personne=null;
        identifiant = identifiant.toLowerCase();
        try{
            Query query = entityManager.createQuery(" from Personne personne where personne.identifiant = ?1 ");
            personne = (Personne) query.setParameter(1, identifiant).getSingleResult();
        }catch (Exception e){}
        if(personne == null){
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
    public boolean verifierMotDePasse(String identifiant, String motDePasse){
        String mdp = "";
        try{
            Personne personne;
            Query query = entityManager.createQuery(" from Personne personne where personne.identifiant = ?1");
            query.setParameter(1, identifiant);
            personne =  (Personne) query.getSingleResult();
            mdp = personne.getMotDePasse();
        }catch(Exception e){}
        if(mdp.equals(motDePasse)){
            return true;
        }else{
            return false;
        }
    }
}