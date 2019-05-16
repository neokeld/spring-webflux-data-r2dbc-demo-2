package fr.duforat.demos.dataflow;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import io.r2dbc.h2.H2ConnectionConfiguration;
import io.r2dbc.h2.H2ConnectionFactory;

@Configuration
@EnableR2dbcRepositories
public class DataflowConfiguration extends AbstractR2dbcConfiguration {

    @Bean
	public H2ConnectionFactory connectionFactory() {
    	H2ConnectionConfiguration config = H2ConnectionConfiguration.builder()
                .username("sa")
                .password("")
                .url("mem:testdb;DB_CLOSE_DELAY=-1") // https://stackoverflow.com/questions/5763747/h2-in-memory-database-table-not-found
                .build();
 
        return new H2ConnectionFactory(config);
    }
}
