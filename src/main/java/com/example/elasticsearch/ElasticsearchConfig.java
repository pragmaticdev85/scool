package com.example.elasticsearch;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchConfig {

	@Value("${spring.elasticsearch.host:localhost}")
	private String hostUris;

	@Value("${spring.elasticsearch.port:9200}")
	private int hostPort;

	@Value("${spring.elasticsearch.scheme:http}")
	private String scheme;

	@Value("${spring.elasticsearch.username}")
	private String username;


	@Value("${spring.elasticsearch.password}")
	private String password;

	@Bean
	public RestClient restHighLevelClient() {
		return RestClient.builder(new HttpHost(hostUris, hostPort, scheme))
				.setHttpClientConfigCallback(
						httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider()))
				.build();
	}

	@Bean
	public CredentialsProvider credentialsProvider() {
		CredentialsProvider provider = new BasicCredentialsProvider();
		provider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
		return provider;
	}
}