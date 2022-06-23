package enzofalvo.testes;

import enzofalvo.Produto;
import enzofalvo.dao.CategoriaDAO;
import enzofalvo.dao.ProdutoDAO;
import enzofalvo.modelo.Categoria;
import enzofalvo.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class CadastroProduto {
    public static void main(String[] args) {
        cadastrarProduto();

        EntityManager entityManager = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(entityManager);
        Produto produto = produtoDAO.buscarPorId(1l);
        System.out.println(produto.getPreco());

        List<Produto> produtos = produtoDAO.buscarPorNomeDaCategoria("CELULARES");
        produtos.forEach(p2 -> System.out.println(p2.getNome()));

        BigDecimal precoProduto = produtoDAO.buscarPrecoProdutoComNome("Xiaomi");
        System.out.println(precoProduto);
    }

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("CELULARES");
        Produto celular = new Produto("Xiaomi", "Produto novo", new BigDecimal(800), celulares);

        EntityManager entityManager = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(entityManager);
        CategoriaDAO categoriaDAO = new CategoriaDAO(entityManager);

        entityManager.getTransaction().begin();

        categoriaDAO.cadastrar(celulares);
        produtoDAO.cadastrar(celular);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
