/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Audio;

import Resource.Info;
import java.io.IOException;
 
import java.util.ArrayList;
import java.util.List;
import Resource.SearchResult;
import org.jsoup.HttpStatusException;
 
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

/**
 *
 * @author Alien Ideology <alien.ideology at alien.org>
 */
public class Lyrics {
 
   public static SearchResult getSongLyrics(String input) throws IOException, HttpStatusException
   {
        List<String> lyrics= new ArrayList<String>();
 
        String lyricsURL = Info.LYRICSURL + input.substring(0,1).toUpperCase() + input.substring(1).replace(" ", "-").toLowerCase() + "lyrics";
        
        Document doc = Jsoup.connect(lyricsURL).get();
        String title = doc.title();
        System.out.println(title);
        
        //String author = doc.select(".drop-target").get(0).childNodes().get(0).toString();
        String lyricText = "";
        int count = 0;
        Element p = doc.select(".lyrics").select("p").get(0);
        doc.select("br").append("");
        
        for (Node e : p.childNodes()) 
        {
            if(e.hasAttr("data-id"))
            {
                Element el = p.select("[data-id]").get(count);
                for (Node en : el.childNodes()) 
                {
                    lyrics.add(en.toString());
                    if (e instanceof TextNode) {
                        lyrics.add(((TextNode)e).getWholeText());
                    }
                }
                count ++;
            }
            
            if (e instanceof TextNode) {
                lyrics.add(((TextNode)e).getWholeText());
            }
        }
        
        return new SearchResult(title, "", lyricsURL, "", lyrics);
   }
}
