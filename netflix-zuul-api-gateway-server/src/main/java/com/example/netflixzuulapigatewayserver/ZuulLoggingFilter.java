package com.example.netflixzuulapigatewayserver;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class ZuulLoggingFilter extends ZuulFilter{
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		log.info("tgeee => {} req uri => {}", request, request.getRequestURI());
		System.out.println("-----------------");
//		System.out.println(request.getLocale().getCountry());
//		System.out.println(request.getLocale().getDisplayCountry());
//		System.out.println(request.getLocale().getDisplayLanguage());
//		System.out.println(request.getLocale().getDisplayName());
//		System.out.println(request.getLocale().getDisplayScript());
//		System.out.println(request.getLocale().getDisplayVariant());
//		System.out.println(request.getLocale().getISO3Language());
//		System.out.println(request.getLocale().getISO3Country());
//		System.out.println(request.getLocale().getLanguage());
//		System.out.println(request.getLocale().getUnicodeLocaleAttributes().toString());
//		System.out.println(request.getLocale().getUnicodeLocaleKeys().toString());
//		System.out.println(request.getLocale().getDefault());
//		System.out.println(request.getLocale().getISOCountries());
//		System.out.println(request.getLocale().getISOLanguages());
		
		String header = request.getHeader("Authorization").replace("Bearer ", "");
		System.out.println("token: " + header);
		
		System.out.println("-----------------");
		
		// TODO impl auth here
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 2;
	}

}
