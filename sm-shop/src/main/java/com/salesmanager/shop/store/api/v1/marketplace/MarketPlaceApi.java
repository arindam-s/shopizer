package com.salesmanager.shop.store.api.v1.marketplace;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.marketplace.ReadableMarketPlace;
import com.salesmanager.shop.store.controller.marketplace.facade.MarketPlaceFacade;
import com.salesmanager.shop.utils.LanguageUtils;

@Controller
@RequestMapping("/api/v1")
public class MarketPlaceApi {
	
	@Inject
	private MarketPlaceFacade marketPlaceFacade;
	
	@Inject
	private LanguageUtils languageUtils;
	
	
	void create() throws Exception {
		
	}
	
	/**
	 * Get a marketplace from storeCode
	 * returns market place details and merchant store
	 */
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping( value={"/private/marketplace/{store}"}, method=RequestMethod.GET)
    public @ResponseBody ReadableMarketPlace marketPlace(@PathVariable String store, @RequestParam(value = "lang", required=false) String lang, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	Language l = languageUtils.getServiceLanguage(lang);
    	
    	ReadableMarketPlace marketPlace = marketPlaceFacade.get(store, l);
    	
		if(marketPlace==null) {
			response.sendError(404,  "Marketplace not found for merchant store [" + store + "]");
			return null;
		}
    	
    	return marketPlace;
    }
		

}
