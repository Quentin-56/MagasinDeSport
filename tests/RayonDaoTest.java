/*import controlleur.RayonDAO;
import modele.Article;
import modele.Rayon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

class RayonDaoTest {

    @Mock
    EntityManager entityManagerMock;

    @Mock
    EntityTransaction transaction;

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

}*/