package main;

import controlleur.Connexion;
import controlleur.MagasinControlleur;
import controlleur.RayonDAO;
import controlleur.SetupEM;
import modele.Magasin;
import modele.Rayon;

import javax.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {

        new SetupEM();
        Magasin magasin = new Magasin();

        Rayon r = new Rayon();
        r.setIdRayon(1);

        System.out.println(RayonDAO.recupererArticleDuRayon(r));


    }
}
