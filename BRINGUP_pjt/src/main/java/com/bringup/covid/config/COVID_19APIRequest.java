package com.bringup.covid.config;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.bringup.covid.config.api.APIEntity;
import com.bringup.covid.config.api.APIEntityList;
import com.bringup.covid.config.api.APIType;

public class COVID_19APIRequest implements APIRequester {

	private final APIEntityList apiConfig;
	private final HttpHeaderFactory httpHeader;

	@Override
	public Object request(Map<String, String> param, APIType type, APIBodyParser parse, APIResultMapper mapper, CustomURL url)
		throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

		HttpHeaders header = httpHeader.get();
		UriComponents uriComponents = createUriComponents(param, type, url);
		ResponseEntity<String> response = null;
		if (url == null) {
			response = restTemplate.exchange(new URI(uriComponents.toUriString()), HttpMethod.GET, new HttpEntity<String>(header),
				String.class);
		} else {
			response = restTemplate.exchange(uriComponents.toUriString(), HttpMethod.GET, new HttpEntity<String>(header),
				String.class);
		}
		String result = parse.pasre(response.getBody());
		if (mapper != null)
			return mapper.map(result);
		return result;
	}

	private UriComponents createUriComponents(Map<String, String> param, APIType type, CustomURL url) {
		APIEntity apiInfo = apiConfig.get(type);
		if (apiInfo == null) {
			throw new RuntimeException("not suport");
		}
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url == null ? apiInfo.getUrl() : url.url())
			.queryParam("ServiceKey", apiInfo.getServiceKey());
		Set<String> set = param.keySet();
		Iterator<String> iterator = set.iterator();
		while (iterator.hasNext()) {
			String paramKey = iterator.next();
			builder = builder.queryParam(paramKey, param.get(paramKey));
		}
		return builder.build();
	}

	public COVID_19APIRequest(APIEntityList apiConfig, HttpHeaderFactory httpHeader) {
		this.apiConfig = apiConfig;
		this.httpHeader = httpHeader;
	}

	public interface CustomURL {
		String url();
	}

	public interface APIBodyParser {
		String pasre(String body);
	}

	public interface APIResultMapper {
		Object map(String body);
	}
}
