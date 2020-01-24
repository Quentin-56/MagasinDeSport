package controlleur;

import modele.Article;
import modele.Rayon;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class RayonDAO {

    private EntityManager entityManager;

    //Getter et setter
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     *Permet d'ajouter un article dans un rayon et est ajouté dans la BDD
     * @param rayon rayon ou ajouter l'article
     * @param nom
     * @param quantite
     * @param details
     * @param rayonA
     */
    public Article creerArticle(/*Rayon rayon,*/ String nom, int quantite, String details, Rayon rayonA, double prix)
    {
        Article article = new Article(nom, quantite, details, rayonA, prix);

        entityManager.getTransaction().begin();

        //Ajout de l'article dans la bdd
        entityManager.persist(article);

        entityManager.getTransaction().commit();

        ajouterArticleDansListeArticle(rayonA, article);

        return article;
    }

    /**
     * Ajoute un article dans la liste d'articles du rayon
     * @param rayon
     * @param article
     */
    public void ajouterArticleDansListeArticle(Rayon rayon, Article article)
    {
       rayon.getListeArticles().add(article);
    }

    /**
     * Supprime l'article dans la BDD et dans la liste d'article du rayon
     * @param articleASupprimer
     */
    public void supprimerArticle(Article articleASupprimer)
    {
        entityManager.getTransaction().begin();

        Article article = entityManager.find(Article.class, articleASupprimer.getIdArticle());
        entityManager.remove(article);

        entityManager.getTransaction().commit();

        supprimerArticleDansListeArticle(articleASupprimer.getRayonA(), articleASupprimer);
    }

    /**
     * Supprime l'article dans la liste d'article du rayon
     * @param rayon
     * @param article
     */
    public void supprimerArticleDansListeArticle(Rayon rayon, Article article)
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
    public void modifierArticle(Article article)
    {
        entityManager.getTransaction().begin();

        entityManager.merge(article);

        entityManager.getTransaction().commit();
    }

    /**
     * Permet de recuper les articles d'un rayon
     * @param rayon
     * @return listP la liste des articles du rayon
     */
    public List<Article> recupererArticleDuRayon(Rayon rayon)
    {
        entityManager.getTransaction().begin();
        Query query = SetupEM.getEm().createQuery("from Article article where article.rayonA = ?1");
        List<Article> listP =  query.setParameter(1, rayon).getResultList();

        entityManager.getTransaction().commit();

        return listP;
    }

    public void transfererUnArticle(Article article, Rayon nouveauRayon)
    {
        article.getRayonA().getListeArticles().remove(article);

        //si l'article etait un article reserve alors le retirer de la liste des articles reserves de l ancien rayon
        // et l ajouter dans la liste des articles reserves du nouveau rayon
        if(article.getRayonA().getListeReservationArticle().contains(article))
        {
            article.getRayonA().getListeReservationArticle().remove(article);
            nouveauRayon.getListeReservationArticle().add(article);
        }

        nouveauRayon.getListeArticles().add(article);

        article.setRayonA(nouveauRayon);
    }

    /**
     * @param article l article a reserver
     * @param quantite la quantite reservee
     */
    public void reserverUnArticle(Article article, int quantite)
    {
        article.setQuantite(article.getQuantite() - quantite);
        article.setQuantiteReserve(article.getQuantiteReserve() + quantite);

    }

}
