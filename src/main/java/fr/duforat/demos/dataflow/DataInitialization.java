package fr.duforat.demos.dataflow;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;

// https://github.com/spring-projects/spring-data-r2dbc/issues/40
@Component
public class DataInitialization {

    private final DatabaseClient databaseClient;

    public DataInitialization(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
    	System.out.println("STARTUP EVENT");
    	    	
    	this.databaseClient.execute()
    	.sql("CREATE TABLE DATASOURCE (\r\n" + 
    			"id INTEGER PRIMARY KEY,\r\n" + 
    			"kind VARCHAR(64),\r\n" + 
    			"source VARCHAR(256),\r\n" + 
    			"title VARCHAR(256)\r\n" + 
    			");")
    	.fetch()
    	.all()
    	.log()
    	.subscribe();
 	
    	System.out.println("AFTER CREATE");
    	
        this.databaseClient.execute()
        .sql("INSERT INTO DATASOURCE (id, kind, source, title) VALUES\r\n" + 
        		"(1, 'csv', 'table.csv', 'Table'),\r\n" + 
        		"(2, 'json', 'test.json', 'Test'),\r\n" + 
        		"(3, 'r2dbc', 'mem:testdb', 'H2 connexion');")
        .fetch()
        .all()
        .log()
        .subscribe();
    }
	
}
