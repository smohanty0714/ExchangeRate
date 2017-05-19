package com.currencyExchange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.currencyExchange.service.impl.CurrencyExchangeServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
public class CurrencyExchangeController {
	
	@Autowired
	CurrencyExchangeServiceImpl exchangeService;
	
	@ApiOperation("Get Current Exchange Rate")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "bad request"), @ApiResponse(code = 500, message = "Internal error") })
	@RequestMapping("/rate")
	public String getExchangeRate(@RequestParam(name = "bank", required = false, defaultValue = "DBS") String bank,
			@RequestParam(name = "country", required = false, defaultValue = "IN") String country) {
		return exchangeService.getExchangeRate(bank, country);
	}

}
