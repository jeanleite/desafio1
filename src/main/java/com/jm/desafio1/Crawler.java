package com.jm.desafio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author michel
 */
public class Crawler {

    private final DefaultHttpClient client = new DefaultHttpClient();

    private final int limitPorCategoria = 10;

    private final int limitMaxProd = 1000;

    public String getPage(String url) {
        try {
            HttpGet request = new HttpGet(url);
            if (request.isAborted()) {
                return null;
            }
            request.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:18.0) Gecko/20100101 Firefox/18.0");

            HttpResponse response = client.execute(request);

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuilder result = new StringBuilder();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            EntityUtils.consume(response.getEntity());

            return result.toString();
        } catch (UnknownHostException ex) {
            // Quando o link é invalido
        } catch (IOException ex) {
            Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Produto> findProduct(String url) throws Exception {
        List<Produto> listProduto = new ArrayList<>();
        // Acessa o mapa do site
        Document doc = Jsoup.parse(getPage(fixeUrl(url, "/mapa-do-site")));
        Element element = doc.getElementById("content-middle");
        Elements links = element.getElementsByTag("a");
        //Percorre as categiorias
        for (Element linkMenu : links) {
            String linkHref = linkMenu.attr("href");
            if (!linkHref.isEmpty() || !linkHref.equals("#")) {
                String page = getPage(fixeUrl(url, linkHref));
                if (page != null) {
                    Document docCategoria = Jsoup.parse(page);
                    String categoria = docCategoria.select("h1.category-title").isEmpty() ? null : docCategoria.select("h1.category-title").first().text();
                    Elements elements = docCategoria.select("div.product-grid-item");
                    if (!elements.isEmpty()) {
                        for (Element cardProduto : elements.subList(0, elements.size() > limitPorCategoria ? limitPorCategoria : elements.size())) {
                            String link = cardProduto.select("a.card-product-url").isEmpty() ? null : fixeUrl(url, cardProduto.select("a.card-product-url").first().attr("href"));
                            String nome = cardProduto.select("h1.card-product-name").isEmpty() ? null : cardProduto.select("h1.card-product-name").first().text();
                            String desconto = cardProduto.select("span.label-discount-rate").isEmpty() ? null : cardProduto.select("span.label-discount-rate").first().text();
                            String preco = cardProduto.select("div.card-product-price").isEmpty() ? null : cardProduto.select("div.card-product-price").first().text();
                            String rating = cardProduto.select("div.rating-fill").isEmpty() ? null : cardProduto.select("div.rating-fill").first().attr("style");
                            BigDecimal precoFormatted = preco == null ? null : new BigDecimal(preco.replace(".", "").replace("R$", "").replace(",", ".").trim());
                            Float ratingFormatted = rating == null ? null : new Float(rating.replace("width:", "").replace("%", "").trim());
                            Float descontoFormatted = desconto == null ? null : new Float(desconto.replace("%", "").trim());
                            // Tira os produto indiponiveis 
                            if (preco != null) {
                                Produto produto = new Produto(link, nome, categoria, null, null, descontoFormatted, ratingFormatted, precoFormatted);
                                listProduto.add(produto);
                                // Para não demorar muito em varrer tem uma limitação de produtos
                                if (listProduto.size() >= limitMaxProd) {
                                    return listProduto;
                                }
                            }
                        }
                    }
                }
            }
        }
        return listProduto;
    }

    public String fixeUrl(String url, String link) {
        return link.startsWith(url) ? link : url.concat(link);
    }

    public void close() {
        client.getConnectionManager().shutdown();
    }

}
