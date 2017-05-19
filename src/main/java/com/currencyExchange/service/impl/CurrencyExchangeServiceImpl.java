package com.currencyExchange.service.impl;

import java.math.BigDecimal;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.currencyExchange.service.CurrencyExchangeService;

@Service
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {
	
	private final Logger logger = LogManager.getLogger(CurrencyExchangeServiceImpl.class);

	@Value("${dbs.exchange.url}")
	private String dbsurl;
	
	@Override
	public String getExchangeRate(String bank, String country) {
		try {
			Document doc = Jsoup.connect(dbsurl).get();
			Element india = doc.getElementsByClass("filter_Indian_Rupee").get(0);
			String price = india.getElementsByClass("column-3").get(0).text();
			System.out.println(price);
			BigDecimal decimal = new BigDecimal(price);
			BigDecimal result = new BigDecimal("100").divide(decimal, 2, 0);
			return "<p>You will get <b> " + result.toString() + " Rupees</b> for <strong>1 </strong> Singapore Dollar</p>";
		} catch (Exception ex) {
			logger.error(ex);
			return "Unable to get the rate. Try after some time;";
		}
	}
 
}
