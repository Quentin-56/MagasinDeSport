import controlleur.RayonDAO;
import modele.Article;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import javax.persistence.EntityManager;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

class RayonDaoTest {

    @Mock
    EntityManager entityManagerMock;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void article_ajouter_au_rayon()
    {
        //when(entityManagerMock.getTransaction().begin())

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

        rayonDAO.creerArticle(null,"velo",2,"rouge",null,10.0);
    }
}