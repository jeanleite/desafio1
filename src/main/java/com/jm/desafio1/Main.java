package com.jm.desafio1;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author michel
 */
public class Main {
     public static void main(String args[]) {
        // Atenção a execução demora alguns minutos
        Crawler crawler = new Crawler();
        try {
            String url = "https://www.americanas.com.br";
            List<Produto> listProduto = crawler.findProduct(url);
            crawler.close();

            Collections.sort(listProduto, (Produto p1, Produto p2) -> p1.getPreco() != null ? p1.getPreco().compareTo((p2.getPreco() == null ? BigDecimal.ZERO : p2.getPreco())) : -1);

            Produto prodBarato = listProduto.get(0);
            System.out.println("Produto Mais Barato ---- ");
            System.out.println("Link:" + prodBarato.getLink());
            System.out.println("Categoria : " + prodBarato.getCategoria());
            System.out.println("Nome :" + prodBarato.getNome());
            System.out.println("Aprovação : " + prodBarato.getRating()+ "%");
            System.out.println("Desconto: " + prodBarato.getDesconto() + "%");
            System.out.println("Preco: " + prodBarato.getPreco());
            
            
            Collections.sort(listProduto, (Produto p1, Produto p2) -> p2.getRating() != null ? p2.getRating().compareTo((p1.getRating() == null ? 0F : p1.getRating())) : -1);

            Produto prodPopular = listProduto.get(0);
            System.out.println("Produto Mais Popular ---- ");
            System.out.println("Link:" + prodPopular.getLink());
            System.out.println("Categoria : " + prodPopular.getCategoria());
            System.out.println("Nome :" + prodPopular.getNome());
            System.out.println("Aprovação : " + prodPopular.getRating()+ "%");
            System.out.println("Desconto: " + prodPopular.getDesconto() + "%");
            System.out.println("Preco: " + prodPopular.getPreco());
            
            Collections.sort(listProduto, (Produto p1, Produto p2) -> p2.getDesconto()!= null ? p2.getDesconto().compareTo((p1.getDesconto() == null ? 0F : p1.getDesconto())) : -1);

            Produto prodDesconto = listProduto.get(0);
            System.out.println("Produto com Maior Desconto ---- ");
            System.out.println("Link:" + prodDesconto.getLink());
            System.out.println("Categoria : " + prodDesconto.getCategoria());
            System.out.println("Nome :" + prodDesconto.getNome());
            System.out.println("Aprovação : " + prodDesconto.getRating()+ "%");
            System.out.println("Desconto: " + prodDesconto.getDesconto() + "%");
            System.out.println("Preco: " + prodDesconto.getPreco());
            
            
        } catch (Exception ex) {
            Logger.getLogger(Main.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
}
