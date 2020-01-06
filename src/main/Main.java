package main;

import controlleur.RayonDAO;
import controlleur.SetupEM;
import controlleur.VendeurDAO;
import modele.Magasin;
import modele.Rayon;

public class Main {
    public static void main(String[] args) {

        new SetupEM();
        Magasin magasin = new Magasin();

        System.out.println(VendeurDAO.trouverVendeurAvecIdentifiant("jeannot"));
    }
}
