package com.j2ee.java.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.j2ee.java.model.bo.ProviderBO;
import com.j2ee.java.model.dto.Provider;

@Controller
public class ProviderManageController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(ProviderManageController.class);

	@Autowired
	@Qualifier("ProviderBOImpl")
	private ProviderBO providerBO;

	@RequestMapping(value = "/listProvider")
	public String listProvider(Model model) {

		logger.info("List provider");

		List<Provider> listProvider = providerBO.getAllProvider();
		if (listProvider == null) {
			return "ErrorPage";
		}
		model.addAttribute("listProvider", listProvider);

		return "ListProvider";
	}

	@RequestMapping(value = "/createProvider", method = RequestMethod.GET)
	public String createProvider(Model model) {

		model.addAttribute("isEdit", 0);

		return "ProviderDetail";
	}

	@RequestMapping(value = "/editProvider", method = RequestMethod.GET)
	public String editProvider(Model model, HttpServletRequest req) {

		logger.info("Edit provider!");

		String providerIDRequest = (String) req.getParameter("providerID");
		if (providerIDRequest == null || "".equals(providerIDRequest)) {
			return "ErrorPage";
		}

		int providerID = Integer.parseInt(providerIDRequest);

		Provider provider = providerBO.getByID(providerID);
		if (provider == null) {
			return "ErrorPage";
		}

		// add product information to model
		model.addAttribute("providerToEdit", provider);

		// set this process is create new
		model.addAttribute("isEdit", 1);

		return "ProviderDetail";
	}

	@RequestMapping(value = "/saveProvider")
	public @ResponseBody String saveProvider(HttpServletRequest req, Model model) {

		// get provider info
		String providerInfo = req.getParameter("0");
		JsonObject providerObj = new Gson().fromJson(providerInfo, JsonObject.class);

		Provider provider = new Provider();

		// set provider's name
		provider.setProviderName(providerObj.get("providerName").getAsString());

		//set telephone
		provider.setTel(providerObj.get("tel").getAsString());
		
		// set address
		provider.setAddress(providerObj.get("address").getAsString());
		
		//set email
		provider.setEmail(providerObj.get("email").getAsString());
		
		//set website
		provider.setWebsite(providerObj.get("website").getAsString());
		
		//set description
		provider.setDescription(providerObj.get("description").getAsString());

		//get isEdit
		int isEdit = providerObj.get("isEdit").getAsInt();

		if (isEdit == 0) {// create new
			if (providerBO.insertProvider(provider)) {
				return "{\"result\" : \"1\", \"isEdit\" : \"1\"}";
			}
		} else {// update
			provider.setProviderID(providerObj.get("providerID").getAsInt());
			if (providerBO.updateProvider(provider)) {
				return "{\"result\" : \"2\", \"isEdit\" : \"1\"}";
			}
		}

		return "{\"result\" : \"0\"}";
	}

	@RequestMapping(value = "/deleteProvider")
	public @ResponseBody String deleteProvider(Model model, HttpServletRequest req) {

		// get provider info
		String providerInfo = req.getParameter("0");
		JsonObject providerObj = new Gson().fromJson(providerInfo,
				JsonObject.class);

		int providerID = providerObj.get("providerID").getAsInt();
		if(providerID == 0){
			return "{\"result\" : \"0\"}";
		}
		
		Provider provider = providerBO.getByID(providerID);
		if (provider == null) {
			return "{\"result\" : \"0\"}";
		}

		if (providerBO.deleteProvider(provider)) {
			return "{\"result\" : \"1\"}";
		}

		return "{\"result\" : \"0\"}";

	}
}
