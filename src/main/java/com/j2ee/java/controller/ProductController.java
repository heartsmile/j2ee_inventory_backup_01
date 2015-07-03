package com.j2ee.java.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
import com.google.gson.reflect.TypeToken;
import com.j2ee.java.model.bo.ProductBO;
import com.j2ee.java.model.bo.ProductComponentBO;
import com.j2ee.java.model.dto.Product;

@Controller
public class ProductController {

	private static final Logger logger = LoggerFactory
			.getLogger(ProductController.class);

	@Autowired
	@Qualifier("ProductBOImpl")
	private ProductBO productBO;

	@Autowired
	@Qualifier("ProductComponentBOImpl")
	private ProductComponentBO productCompoBO;

	@RequestMapping(value = "/getProductInfor", method = RequestMethod.POST)
	public final @ResponseBody String getProduct(HttpServletRequest request) {
		logger.debug("Get Product information using ID");

		Gson gson = new Gson();

		String productID = request.getParameter("productID");

		Product product = productBO.getByID(Integer.parseInt(productID));

		Type type = new TypeToken<Product>() {
		}.getType();

		String jsonProduct = gson.toJson(product, type);
		return jsonProduct;
	}

	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public String product(Locale locale, Model model) {
		logger.info("Display List Product information");

		return "ListProduct";
	}

	@RequestMapping(value = "/getListProduct", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public @ResponseBody String getListProduct() {
		Gson gson = new Gson();

		List<Product> listProduct = new ArrayList<Product>();
		listProduct = productBO.getAllProduct();

		Type type = new TypeToken<List<Product>>() {
		}.getType();

		String jsonProduct = gson.toJson(listProduct, type);
		return jsonProduct;
	}

	// open page Create Component for Product
	@RequestMapping(value = "/createComponent", method = RequestMethod.GET)
	public String createComponent(Locale locale, Model model) {
		logger.info("Display form add component information");

		return "Component";
	}

	// Save Component for Product
	@RequestMapping(value = "/saveProductComponent", method = RequestMethod.POST)
	public @ResponseBody String saveProductComponent(HttpServletRequest request) {
		String response = "";
		try {
			response = productCompoBO.insertProductComponent(request);
		} catch (Exception e) {
			response = "{\"ID\": \"2\"}";
		}
		return response;
	}
}
