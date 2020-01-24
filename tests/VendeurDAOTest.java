import controlleur.VendeurDAO;
import modele.Article;
import modele.Rayon;
import modele.Vendeur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class VendeurDAOTest {
    @Mock
    EntityManager entityManagerMock;

    @Mock
    EntityTransaction transaction;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void creer_un_vendeur_test(){
        when(entityManagerMock.getTransaction()).thenReturn(transaction);
        doAnswer(new Answer<Object>(){
            @Override
            public Object answer(InvocationOnMock invocation){
                Vendeur vendeur = (Vendeur) invocation.getArguments()[0];
                vendeur.setIdPersonne(1);
                return vendeur;
            }
        }).when(entityManagerMock).persist(any(Article.class));
        VendeurDAO dao = new VendeurDAO();
        dao.setEntityManager(entityManagerMock);

        Vendeur vendeur = dao.creerVendeur("","","","",new Rayon());
        vendeur.setIdPersonne(1);
        verify(transaction).begin();
        verify(transaction).commit();
        verify(entityManagerMock).persist(vendeur);
    }
}
