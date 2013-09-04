/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawler;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 *
 * @author abiniks
 */
public class Crawler {
    
    private List<URI> seedURI = new ArrayList<>();
    private List<URI> uriQueue = new ArrayList<>();
    private Set<URI> uriSeenSet = new HashSet<>();
    
    public Crawler(List<URI> seedURI) { 
        this.seedURI = seedURI;       
    }        
        
    public void init() {
        uriQueue.clear();
        for(URI eachURI : seedURI) {  
            if(!uriSeenSet.contains(eachURI))
                uriQueue.add(eachURI);
        }
        System.out.println("Initialized set up");
    }
    
    public static void runSpider(List<URI> seedURI) {
        
        Crawler spider = new Crawler(seedURI);
        spider.init();
        ExecutorService executor = Executors.newFixedThreadPool(10);
        executor.submit(new CrawlURI());
    }
}
