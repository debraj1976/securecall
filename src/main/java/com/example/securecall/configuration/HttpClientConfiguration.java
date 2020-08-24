package com.example.securecall.configuration;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.io.Resource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.io.File;


@Configuration
class SSLConnection {
	
	@Value("${ssl1.key-store}")
	private Resource resource;

	@Value("${ssl1.key-store-password}")
	private String keystorePassword;

	@Value("${ssl1.trust-store}")
	private Resource trustResource;


	/**
	 * @return SSLConnectionSocketFactory
	 */
	@Bean
	public SSLConnectionSocketFactory sslSocket() {
	
		SSLConnectionSocketFactory sslsf = null;
		try {

				File keyStoreFile = resource.getFile();
				File trustStoreFile = trustResource.getFile();

				/*SSLContext sslcontext = SSLContexts.custom()
						.loadTrustMaterial(keyStoreFile, keystorePassword.toCharArray(),
								new TrustSelfSignedStrategy())
						.build();*/

			SSLContext sslcontext = SSLContexts.custom()
					.loadTrustMaterial(trustStoreFile, keystorePassword.toCharArray(),
							new TrustSelfSignedStrategy())
					.loadKeyMaterial(keyStoreFile, keystorePassword.toCharArray(),
							keystorePassword.toCharArray())
					.build();
	
				// Allow TLSv12 protocol only
				 sslsf = new SSLConnectionSocketFactory(
						sslcontext,
						new String[]{"TLSv1.2"},
						null,
						SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return sslsf;
	}

}

@Configuration
public class HttpClientConfiguration {
	
	@Autowired
	private SSLConnectionSocketFactory sSLConnectionSocketFactory;


	public CloseableHttpClient httpClient() throws Exception {

		CloseableHttpClient httpClient = HttpClients.custom()
				.setSSLSocketFactory(sSLConnectionSocketFactory).build();

		return httpClient;
	}

	@Bean
	public RestTemplate getRestTemplate() throws Exception {

		final RestTemplate restTemplate = new RestTemplate();
		final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setHttpClient(httpClient());
		restTemplate.setRequestFactory(factory);
		return restTemplate;
	}

	/*@Bean
	public RestTemplate getRestTemplate() throws Exception {

		final RestTemplate restTemplate = new RestTemplate();
		//final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		//factory.setHttpClient(httpClient());
		//restTemplate.setRequestFactory(factory);
		return restTemplate;
	}*/

}
