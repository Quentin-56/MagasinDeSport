package controlleur;

import modele.Article;
import modele.Rayon;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class RayonDAO {

    /**
     *Permet d'ajouter un article dans un rayon et est ajouté dans la BDD
     * @param rayon rayon ou ajouter l'article
     * @param nom
     * @param quantite
     * @param details
     * @param rayonA
     */
    public static void creerArticle(Rayon rayon, String nom, int quantite, String details, Rayon rayonA)
    {
        Article article = new Article(nom, quantite, details, rayonA);

        EntityManager em =SetupEM.getEm();
        em.getTransaction().begin();

        //Ajout de l'article dans la bdd
        em.persist(article);

        em.getTransaction().commit();

        ajouterArticleDansListeArticle(rayon, article);
    }

    /**
     * Ajoute un article dans la liste d'articles du rayon
     * @param rayon
     * @param article
     */
    public static void ajouterArticleDansListeArticle(Rayon rayon, Article article)
    {
       rayon.getListeArticles().add(article);
    }

    /**
     * Supprime l'article dans la BDD et dans la liste d'article du rayon
     * @param rayon
     * @param articleASupprimer
     */
    public static void supprimerArticle(Rayon rayon, Article articleASupprimer)
    {
        EntityManager em =SetupEM.getEm();
        em.getTransaction().begin();

        Article article = em.find(Article.class, articleASupprimer.getIdArticle());
        em.remove(article);

        em.getTransaction().commit();

        supprimerArticleDansListeArticle(rayon, articleASupprimer);
    }

    /**
     * Supprime l'article dans la liste d'article du rayon
     * @param rayon
     * @param article
     */
    public static void supprimerArticleDansListeArticle(Rayon rayon, Article article)
    {

        rayon.getListeArticles().remove(article);

        //Si l'article est reserve alors on le supprime des reservations
        if(rayon.getListeReservationArticle().contains(article))
        {
            rayon.getListeReservationArticle().remove(article);
        }
    }

    /**
     * Modifier un article
     * @param article
     */
    public static void modifierArticle(Article article)
    {
        EntityManager em =SetupEM.getEm();

        em.getTransaction().begin();

        em.merge(article);

        em.getTransaction().commit();
    }

    /**
     * Permet de recuper les articles d'un rayon
     * @param rayon
     * @return listP la liste des articles du rayon
     */
    public static List<Article> recupererArticleDuRayon(Rayon rayon)
    {
        EntityManager em =SetupEM.getEm();

        em.getTransaction().begin();
        Query query = SetupEM.getEm().createQuery("from Article article where article.rayonA = ?1");
        List<Article> listP =  query.setParameter(1, rayon).getResultList();

        em.getTransaction().commit();

        return listP;
    }

    public static void transfererUnArticle(Article article, Rayon nouveauRayon)
    {
        //penser à modifier lattribut rayon de l'article et la liste d'article de l'ancien et du nouveau rayon
    }

}
