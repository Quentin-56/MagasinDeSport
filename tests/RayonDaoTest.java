import controlleur.RayonDAO;
import modele.Article;
import modele.Rayon;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

class RayonDaoTest {

    @Mock
    EntityManager entityManagerMock;

    @Mock
    EntityTransaction transaction;

    @Mock
    private Query queryMock;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void article_ajouter_au_rayon()
    {
        when(entityManagerMock.getTransaction()).thenReturn(transaction);

        doAnswer(new Answer<Object>(){
            @Override
            public Object answer(InvocationOnMock invocation){
                Article article = (Article) invocation.getArguments()[0];
                article.setIdArticle(1);
                return null;
            }
        }).when(entityManagerMock).persist(any(Article.class));
        RayonDAO rayonDAO = new RayonDAO();
        rayonDAO.setEntityManager(entityManagerMock);

        Article article =rayonDAO.creerArticle(new Rayon(),"velo",2,"rouge",null,10.0);
        verify(transaction).begin();
        verify(transaction).commit();
        verify(entityManagerMock).persist(article);
    }
    @Test
    public void article_supprimer_du_rayon()
    {
        when(entityManagerMock.getTransaction()).thenReturn(transaction);
        Rayon rayon = new Rayon();
        RayonDAO rayonDAO = new RayonDAO();
        rayonDAO.setEntityManager(entityManagerMock);
        Article article =article =rayonDAO.creerArticle(rayon,"velo",2,"rouge",null,10.0);
        when(entityManagerMock.find(Article.class, article.getIdArticle())).thenReturn(article);
        doNothing().when(entityManagerMock).remove(article);
        rayonDAO.supprimerArticle(rayon ,  article);
        verify(entityManagerMock).remove(article);

    }

    @Test
    public void modifier_article_du_rayon()
    {
        Article article = new Article();
        Article articleModifie = article;
        articleModifie.setNom("test");
        when(entityManagerMock.getTransaction()).thenReturn(transaction);
        when(entityManagerMock.merge(article)).thenReturn(articleModifie);
        RayonDAO rayonDAO = new RayonDAO();
        rayonDAO.setEntityManager(entityManagerMock);
        rayonDAO.modifierArticle(article);
        verify(transaction).begin();
        verify(transaction).commit();
        verify(entityManagerMock).merge(articleModifie);

    }


    @Test
    public void ajouter_article_dans_la_liste_article_test(){
        //Before
        Rayon rayon = new Rayon();
        Article article1 = new Article();
        Article article2 = new Article();
        RayonDAO dao = new RayonDAO();
        List<Article> listeArticleInitial = new ArrayList<>();
        listeArticleInitial.add(article1);
        rayon.setListeArticles(listeArticleInitial);
        //test
        dao.ajouterArticleDansListeArticle(rayon, article2);
        assertEquals(article1,rayon.getListeArticles().get(0));
        assertEquals(article2,rayon.getListeArticles().get(1));
        //after
        rayon.setListeArticles(null);
    }
    @Test
    public void supprimer_article_dans_la_liste_article(){
        //Before
        Rayon rayon = new Rayon();
        Article article1 = new Article();
        Article article2 = new Article();
        RayonDAO dao = new RayonDAO();
        List<Article> listeArticleInitial = new ArrayList<>();
        listeArticleInitial.add(article1);
        listeArticleInitial.add(article2);
        rayon.setListeArticles(listeArticleInitial);
        //test
        dao.supprimerArticleDansListeArticle(rayon, article2);
        assertEquals(article1,rayon.getListeArticles().get(0));
        try{
            rayon.getListeArticles().get(1);
        }catch (IndexOutOfBoundsException e){
            assertEquals("Index 1 out of bounds for length 1",e.getMessage());
        }
        //after
        rayon.setListeArticles(null);
    }

    @Test
    public void recuperer_article_du_rayon(){
        RayonDAO dao = new RayonDAO();
        Rayon rayon = new Rayon();
        Article article1 = new Article();
        Article article2 = new Article();
        List<Article> listeTest =List.of(article1, article2);

        when(entityManagerMock.getTransaction()).thenReturn(transaction);

        when(entityManagerMock.createQuery("from Article article where article.rayonA = ?1")).thenReturn(queryMock);
        when(queryMock.setParameter(1,rayon)).thenReturn(queryMock);
        when(queryMock.getResultList()).thenReturn(listeTest);

        assertEquals(listeTest.get(0), dao.recupererArticleDuRayon(rayon).get(0));
        verify(transaction).begin();
        verify(transaction).commit();


    }


}