/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avatarglobal;

import Model.ResponsePokemon;
import Model.Pokemon;
import Model.PokemonSpecies;
import Model.ResponseInfoPoke;
import Model.Sprites;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jcardenas
 */
@WebServlet(name = "GetPokemon", urlPatterns = {"/GetPokemon"})
public class GetPokemon extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ResponsePokemon pokemon;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GetPokemon</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GetPokemon at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
        try {
            Gson gson = new Gson();
            String result = downloadWebPage(request.getParameter("infoUrl"));
            Pokemon p = gson.fromJson(result, Pokemon.class);

            String result1 = downloadWebPage("https://pokeapi.co/api/v2/pokemon/" + p.getName());
            Pokemon p1 = new Gson().fromJson(result1, Pokemon.class);

            String html = "<div class=\"row\">\n"
                    + "    <div class=\"col-sm-6\"><img src=\"" + p1.getSprites().getFront_default() + "\" class=\"d-block w-100\" alt=\"" + p.getName() + "\" /></div>";
            String is_baby = (p1.isIs_baby()) ? "Si" : "No";
            String is_legendary = (p1.isIs_legendary()) ? "Si" : "No";
            String is_mythical = (p1.isIs_mythical()) ? "Si" : "No";
            html += "<div class=\"col-sm-6\">"
                    + "Id: " + p.getId()
                    + "<br>Color: " + p.getColor().getName()
                    + "<br>Habitat: " + p.getHabitat().getName()
                    + "<br>Peso: " + p1.getWeight() + "kg"
                    + "<br>Es bebe: " + is_baby
                    + "<br>Es legendario: " + is_legendary
                    + "<br>Es mitico: " + is_mythical
                    + "<br>Forma: " + p.getShape().getName()
                    + "</div></div>";

            ResponseInfoPoke infoPoke = new ResponseInfoPoke();
            infoPoke.setBodyModal(html);
            String namePoke = p.getName();
            infoPoke.setName(namePoke.substring(0, 1).toUpperCase() + namePoke.substring(1));
            
            String json = new Gson().toJson(infoPoke);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Gson gson = new Gson();
            String result = downloadWebPage(request.getParameter("urlApi"));
            PokemonSpecies ps = gson.fromJson(result, PokemonSpecies.class);
            String items = "";
            String indicators = "";
            int i = 0;
            for (Pokemon pk : ps.getResults()) {
                String pokeName = pk.getName();
                String result1 = downloadWebPage("https://pokeapi.co/api/v2/pokemon/" + pokeName);
                Pokemon p = gson.fromJson(result1, Pokemon.class);

                String namPoke = pokeName.substring(0, 1).toUpperCase() + pokeName.substring(1);
                if (i == 0) {
                    indicators += "<button type=\"button\" data-bs-target=\"#carouselExampleCaptions\" data-bs-slide-to=\"0\" class=\"active\" aria-current=\"true\" aria-label=\"Slide 1\"></button>";
                    items += "<div class=\"carousel-item active\">\n"
                            + " <a href=\"#\" onclick=\"return getInfoPoke('" + pk.getUrl() + "');\"><img src=\"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/" + (i + 1) + ".png\" class=\"d-block w-100\" alt=\"" + namPoke + "\" /></a>\n"
                            + " <div class=\"carousel-caption  d-md-block\">\n"
                            + "     <h5>" + namPoke + "</h5>\n"
                            + "     <p></p>"
                            + " </div>\n"
                            + "</div>";
                } else {
                    indicators += "<button type=\"button\" data-bs-target=\"#carouselExampleCaptions\" data-bs-slide-to=\"" + i + "\" aria-label=\"Slide " + (i + 1) + "\"></button>";
                    items += "<div class=\"carousel-item\">\n"
                            + " <a href=\"#\" onclick=\"return getInfoPoke('" + pk.getUrl() + "');\"><img src=\"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/" + (i + 1) + ".png\" class=\"d-block w-100\" alt=\"" + namPoke + "\" /></a>\n"
                            + " <div class=\"carousel-caption d-none d-md-block\">\n"
                            + "     <h5>" + namPoke + "</h5>\n"
                            + "     <p></p>"
                            + " </div>\n"
                            + "</div>";
                }
                i++;
            }
            pokemon = new ResponsePokemon();
            pokemon.setIndicators(indicators);
            pokemon.setItems(items);
            pokemon.setUrlApi(ps.getNext());

            String json = new Gson().toJson(pokemon);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private static String downloadWebPage(String url) throws IOException {

        StringBuilder result = new StringBuilder();
        String line;
        URLConnection connection = new URL(url).openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        connection.connect();

        BufferedReader r = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));

        while ((line = r.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }
}
