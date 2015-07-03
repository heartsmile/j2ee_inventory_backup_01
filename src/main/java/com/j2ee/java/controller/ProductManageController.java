package com.j2ee.java.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.j2ee.java.model.bo.ManufactureBO;
import com.j2ee.java.model.bo.ProductBO;
import com.j2ee.java.model.bo.ProductTypeBO;
import com.j2ee.java.model.bo.ProductUnitBO;
import com.j2ee.java.model.bo.ProviderBO;
import com.j2ee.java.model.dto.Manufacture;
import com.j2ee.java.model.dto.Product;
import com.j2ee.java.model.dto.ProductType;
import com.j2ee.java.model.dto.ProductUnit;
import com.j2ee.java.model.dto.Provider;

/**
 * Handles requests for the application home page.
 */

@Controller
public class ProductManageController {

	private static final String DEFAULT_PHOTO_PRODUCT = "iVBORw0KGgoAA"
			+ "AANSUhEUgAAASwAAAEsCAYAAAB5fY51AAANF0lEQVR4nO3drZbizAKG0"
			+ "e/+7wCDaoNBtcG0wmAwKAwGFcMtcMQsZs30qUoqf939ztpim3O+oSEVn"
			+ "lQqAf57PB5PgAT/ffcTAGglWEAMwQJiCBYQQ7CAGIIFxBAsIIZgATEEC4"
			+ "ghWEAMwQJiCBYQQ7CAGIIFxBAsIIZgATEEC4ghWEAMwQJiCBYQQ7CAGII"
			+ "FxBAsIIZgATEEC4ghWEAMwQJiCBYQQ7CAGIIFxBAsIIZgATEEC4ghWEAM"
			+ "wQJiCBYQQ7CAGIIFxBAsIIZgATEEC4ghWEAMwQJiCBYQQ7CAGIIFxBAsII"
			+ "ZgATEEC4ghWEAMwQJiCBYQQ7CAGIIFxBAsIIZgATEEC4ghWEAMwQJiCBYQ"
			+ "Q7CAGIIFxBAsIIZgATEEC4ghWEAMwQJiCBYQQ7CAGIIFxBAsIIZgATEEC"
			+ "4ghWEAMwQJiCBYQQ7CAGIIFxBAsIIZgATEEC4ghWEAMwQJiCBYQQ7CAGII"
			+ "FxBAsIIZgATEEC4ghWEAMwQJiCBYQQ7CAGIIFxBAsIIZgATEEC4ghWEAMw"
			+ "QJiCBYQQ7CAGIIFxBAsIIZgATEEC4ghWEAMwQJiCBYQQ7CAGIIFxBAsII"
			+ "ZgATEEC4ghWEAMwQJiCBYQQ7CAGIIFxBAsIIZgATEEC4ghWEAMwQJiCBYQ"
			+ "Q7CAGIIFxBAsIIZgATEEC4ghWEAMwQJi/Ihg3e/35/V6/cvtdlvksT8/7v"
			+ "V6fXZdt+hzv1wuz+Px+DwcDs/9fv/bx8fH83g8Pq/X67dv467ritui9d/fb"
			+ "rfVxugrHr+0jy29L7S+ztPp9Dwej3/tK/v9/nk8Hn/vL3OfV+m1zvHd++/Lj"
			+ "wjW8Xh8bjab/7PEDlt63LkD0HXd83Q6PXe7XfHxa97f35+Xy+VbtvH1ei0+p9"
			+ "Z/X3utS7zhu64rPvZ+v1/s9b+/vxf/xvl8Xn3b3+/35+FweG6321H7y263e5"
			+ "5Op0nbeMzfafFTovWjg7Xb7WY/9pIbv+u65/F4HL3jffb29vblO8DcYNVey+l0W"
			+ "m38t9vtIq+9FsTXQWStbX6/36uhHOtwOIwKl2CtqLbDbjab5/F4nPXYS238"
			+ "2+32fHt7W3QneH9//7JTkrWC9fb2Nvu59W3XJV77+XzuHYc1xuB0Os0+sJU"
			+ "C3jojFKwV9QVrs5l3arjExh/a4efY7XbP+/2++jZeK1ibzWbWae7Qtl3it"
			+ "e/3+96/scQs8U+Hw2G1/WWzaTuNFawVDQXr7e1t8lFw7sZvidX7+/vzdDo9"
			+ "r9fr7/i8FpE/Pj4GZ2bb7XbRBeaSOcGq/duXOWtNQzGZu13u93vTQWOp7TwU"
			+ "q+12+zwcDs/z+fzX4vprfzkej72nka3vhVqYpy66f/XFiZqIYG02m+fHx8ekx"
			+ "54TrKFYHQ6H5tnR9XrtXaTf7Xar7hRrBmuz2UyaJbbEZO6R/XQ6Nc0glpjlfn"
			+ "x89IZmzAJ/bb20dXussS1/gphgTd3gUx/nfr9X1yC22+3kwe97rWsuAK8drCk"
			+ "HlJZTp7lvstLstvR3px4QXy6XS++BberBqOu63yEcs38I1opagzXl1HDqwNVOV"
			+ "ZaYCfXN3Na67WFOsFrGZ7vdjtouXdc1LUrPue3gdrsV96Ha/z7173RdVz3tPx"
			+ "wOi4zf5XKZfZVQsBZSekPUdoCxs5ApA1d7c499U/apRWuJq25jXtPU8Zkbl9Z"
			+ "TtTlXiUunaK+ZVCmWU9fLattnqVhNIVgrKg344XCorvmMmYVMGbja7GrpAf/Km"
			+ "xmXDlbpgDImtrV/v2SwSo/32ndKp4VTA1OK35wLRUsQrBWV3hD7/X6Rmc7Ygast"
			+ "BK9xtKytky15h/fLnGCVwlq7gbblTVFb7ynNiKZu99Lf+PNG1KH/v1VtpvwVd9A"
			+ "vud+n+NHBejzqC7Otb+qxA1eb3q91r1Tt9S19dJ4TrNKM8/XZyc//e8speymAh8Oh"
			+ "dz9YYrt+XlgvBXfsGmLptax1Wj+GYK2ob0ftW5xtueFv7MCV3pxL3qfzWWkBeMob"
			+ "Z8gawao9Zl/cazPY1z1ISwWrZY1qanCH/s7cK45LEKwVDe2ofaeGQzOfsQNX+u/nf"
			+ "jxoyNJrNyVzglV6U75OeUrrjH3PvXTa95qRlBbip8xWSqdppcepnZq2zm5rB5ufE"
			+ "Iaf+rzmigjW41G/KW/oCDxm4Gofkl17oEszmKXXseYEq2+blOJQWwuqzZZf8Zv78a"
			+ "GX0mlabdZTOli0rj8t9XzXUHpeY+90/yl3t/8pJlh997r0HdHHBGjKKc5Xvf651gr"
			+ "W49E/A/vTUNyWCEDtoFO7ZaF0IGzd9rVbM77rfTQ0ZmP9xBlZTLAej/oUvG+HXC"
			+ "JYP+X1zzH1tdXWnP7c3qXnX1r3Gzp9rD3HMUf6UkT61iBr+1TLQWqNcat90eLYL9"
			+ "UTrBWNGfix350lWPNeW8u/a5nVtMZo7hunFMWhizOlmXvLBZ01xq3lY1AtYydYKx"
			+ "o78LUbSkunhksEa+2Bm3Na0mrNYD0ewzditt6oOWf712aDQ7Ol0vZvuTJcO3iuMU"
			+ "6C9UtksG63W/VWh88beexAlP77tb/WuLTovvSNqlODVbsxsjQmn/+b1w2+Y9aV5m"
			+ "z/1lPTludee34t23TOYvWawbLovpApU+vagufnj0SMDVbp9GDt+2pKz/Gn3NYwZm"
			+ "xK4T2fz8XojXmM1m2x9DfCDo37Grc1rBmsnzhjGis2WLWd+/OONnbgvvrO5dq9QEv"
			+ "vXF8RrNJr2e/3xVP42qxparD6LshM1TLuU0LXp7boXtqGY5+XYC1karD6vrPqNThj"
			+ "B652CrTWYJcCudSPL/xparBKa099Y9Myy+kLwdRgrfW1xEOno6Xnu8YBrvR3+v57w"
			+ "VrRnKsttRnKa6cZO3Bf8ZNTL7VZwRoftJ4arNIbpW8G0fK1MX03Zk69ALH0Dz60jk"
			+ "Xt9S794WfB+iU+WI9H/WtaaldxhgaudrRe+scKalc719ixlgxW34xn6Iv5hr5pY8"
			+ "q+UDtovX6YtFXptQ7NdmsHuCW/O602Dn3/vWCtaG6wam+S1iuJn9Uujy/5YxFTP2o"
			+ "01dRgjf2sYN9r22yG13em7AulA8yUD6zXZrxDs6XaAW7Jr7wWrF/+iWA9HuOurrQMX"
			+ "G0nXCJafV+RvNZONTVYU97AfT8uMXRPVOttFC+1Gc7U2XBpDW4oPH1rqUuc3k+530"
			+ "uwVrTUHcN9R/axA9f32cXtdjvp3qyu63oXh9e8fWLJYLVsv9p3Xi39PGuBm/r5z1o"
			+ "chk7vhn5cZMrpYdd1vT/5tca4/XT/VLD6IjNl4IYulb+/vze/Mc7nc+9zW/M7tx6P"
			+ "acGa8+0V9/v9/y7Nt2yrsc+z9Iaesy1rs8OWGVvfbyyO+dXm2k98CdY/FqzHo+1+nD"
			+ "ED1/JDqvv9vvhDqpfLpemHVNf+TcLHY1qwav9mzR99HXPX+Zy49Cmt27VEsOu63t+ef"
			+ "IXrcDg8L5fLX/vh9Xp9ns/n5+FwaL7i2fdcattlzJ3uYz5s/VX+uWDVHm/OkeZyuax"
			+ "22fwrYvV6QywVrLWfa+uY1W4pmLs9a4/bMkNsidZSxm7DNf/eV/kng/V41G8ZmBKs"
			+ "x+PXkX/pHfErv0r3XwxWaTyWuDJXm7m1fkRoaK1yrN1uVzz1HbsNBWshawRrzAekx"
			+ "z7XubOt1y8CfeU2nhKfNb6NoEVp+34+zZt6C0KrUgzH3sF+uVxmf77xeDz+XtMSrH8"
			+ "4WI9HfWo/NxZd1z3P5/OoGddr7eK71gKWCtZa94n9qeVm1doV4bV/6HbK+t35fO692"
			+ "lcK4/F4/OsUVLB++RHBKl1RWmphd+2vzbjf78/L5fL7Z6/2+/1vHx8fv39d5ru3ce1D"
			+ "td81Ln1ut9vgFca1n1tte83Zd7quq+4rx+PxeTqdqq+h9HrH7vdzffc+/Hj8kGABtBAs"
			+ "IIZgATEEC4ghWEAMwQJiCBYQQ7CAGIIFxBAsIIZgATEEC4ghWEAMwQJiCBYQQ7CAGIIF"
			+ "xBAsIIZgATEEC4ghWEAMwQJiCBYQQ7CAGIIFxBAsIIZgATEEC4ghWEAMwQJiCBYQQ7CA"
			+ "GIIFxBAsIIZgATEEC4ghWEAMwQJiCBYQQ7CAGIIFxBAsIIZgATEEC4ghWEAMwQJiCBYQ"
			+ "Q7CAGIIFxBAsIIZgATEEC4ghWEAMwQJiCBYQQ7CAGIIFxBAsIIZgATEEC4ghWEAMwQJi"
			+ "CBYQQ7CAGIIFxBAsIIZgATEEC4ghWEAMwQJiCBYQQ7CAGIIFxBAsIIZgATEEC4ghWEAMw"
			+ "QJiCBYQQ7CAGIIFxBAsIIZgATEEC4ghWEAMwQJiCBYQQ7CAGIIFxBAsIIZgATEEC4ghWE"
			+ "AMwQJiCBYQQ7CAGIIFxBAsIIZgATEEC4ghWEAMwQJiCBYQQ7CAGIIFxBAsIIZgATEEC4gh"
			+ "WEAMwQJiCBYQQ7CAGIIFxBAsIIZgATEEC4ghWEAMwQJiCBYQQ7CAGIIFxBAsIIZgATEEC"
			+ "4ghWEAMwQJiCBYQQ7CAGIIFxBAsIIZgATEEC4jxP3qZSE0n8+v/AAAAAElFTkSuQmCC";

	private static final Logger logger = LoggerFactory
			.getLogger(ProductManageController.class);

	@Autowired
	@Qualifier("ProductBOImpl")
	private ProductBO productBO;

	@Autowired
	@Qualifier("ProductTypeBOImpl")
	private ProductTypeBO productTypeBO;

	@Autowired
	@Qualifier("ProductUnitBOImpl")
	private ProductUnitBO productUnitBO;

	@Autowired
	@Qualifier("ManufactureBOImpl")
	private ManufactureBO manufactureBO;

	@Autowired
	@Qualifier("ProviderBOImpl")
	private ProviderBO providerBO;

	@RequestMapping(value = "/exportProduct")
	public ModelAndView exportProduct(HttpServletRequest request,
			HttpServletResponse response) {

		logger.info("Exporting Product!");

		final String fileName = "E:\\C1_report.pdf";
		final String reportPath = "E:\\j2ee_backUp_001\\src\\main\\resources\\demo.jrxml";
		try {
			new com.j2ee.java.report.ProductReportBO().runReport(reportPath,
					fileName);

			File reportFile = new File(fileName);
			
			OutputStream outStream = response.getOutputStream();
			
			String dir = reportFile.getAbsolutePath();
			Path path = Paths.get(dir);
			
			byte[] fileData = Files.readAllBytes(path);
			
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ fileName + "\"");
			response.setContentLength((int)reportFile.length());
			
			outStream.write(fileData, 0, (int)reportFile.length());
			outStream.close();
			
		} catch (IOException e) {

			e.printStackTrace();
		}

		return null;
	}

	//

	@RequestMapping(value = "/productNew", method = RequestMethod.GET)
	public String createNewProduct(Model model) {

		logger.info("Create New Product!");

		// get list product type and set it to model
		List<ProductType> listProductType = productTypeBO.getAllProductType();
		model.addAttribute("listProductType", listProductType);

		// get list product unit and set it to model
		List<ProductUnit> listProductUnit = productUnitBO.getAllProductUnit();
		model.addAttribute("listProductUnit", listProductUnit);

		// get list Manufacture and set it to model
		List<Manufacture> listManufacture = manufactureBO.getAllManufacture();
		model.addAttribute("listManufacture", listManufacture);

		// get list Manufacture and set it to model
		List<Provider> listProvider = providerBO.getAllProvider();
		model.addAttribute("listProvider", listProvider);

		// set this process is create new
		model.addAttribute("isEdit", 0);

		// set default photo for product
		model.addAttribute("productPhoto", DEFAULT_PHOTO_PRODUCT);

		return "ProductNew";
	}

	// when choose edit a product
	@RequestMapping(value = "/editProduct", method = RequestMethod.GET)
	public String updateProduct(Model model, HttpServletRequest req) {

		logger.info("Edit product!");

		String productIDRequest = (String) req.getParameter("productID");
		if (productIDRequest == null || "".equals(productIDRequest)) {
			return "ErrorPage";
		}

		int productID = Integer.parseInt(productIDRequest);

		Product product = productBO.getByID(productID);
		if (product == null) {
			return "ErrorPage";
		}

		// add product information to model
		model.addAttribute("productToEdit", product);

		// add product photo blob -> string to model
		Blob productPhoto = product.getPhoto();
		try {
			if (productPhoto != null) {
				model.addAttribute(
						"productPhoto",
						new String(productPhoto.getBytes(1,
								(int) productPhoto.length())));
			} else {
				model.addAttribute("productPhoto", DEFAULT_PHOTO_PRODUCT);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// get list product type and set it to model
		List<ProductType> listProductType = productTypeBO.getAllProductType();
		model.addAttribute("listProductType", listProductType);

		// get list product unit and set it to model
		List<ProductUnit> listProductUnit = productUnitBO.getAllProductUnit();
		model.addAttribute("listProductUnit", listProductUnit);

		// get list Manufacture and set it to model
		List<Manufacture> listManufacture = manufactureBO.getAllManufacture();
		model.addAttribute("listManufacture", listManufacture);

		// get list Manufacture and set it to model
		List<Provider> listProvider = providerBO.getAllProvider();
		model.addAttribute("listProvider", listProvider);

		// set this process is create new
		model.addAttribute("isEdit", 1);

		return "ProductNew";
	}

	// when choose delete product
	@RequestMapping(value = "/deleteProduct", method = RequestMethod.POST)
	public @ResponseBody String deleteProduct(HttpServletRequest req) {

		// get product info
		String productInfo = req.getParameter("0");
		JsonObject productObj = new Gson().fromJson(productInfo,
				JsonObject.class);

		Product product = productBO.getByID(productObj.get("productID")
				.getAsInt());
		if (product == null) {
			return "{\"result\" : \"0\"}";
		}

		if (productBO.deleteProduct(product)) {
			return "{\"result\" : \"1\"}";
		}

		return "{\"result\" : \"0\"}";
	}

	@RequestMapping(value = "/saveProduct")
	public @ResponseBody String saveProduct(HttpServletRequest req, Model model) {

		// get product info
		String productInfo = req.getParameter("0");
		JsonObject productObj = new Gson().fromJson(productInfo,
				JsonObject.class);

		if (productObj == null) {
			return "{\"result\" : \"0\"}";
		}

		Product product = new Product();

		// set product's photo
		String img64 = productObj.get("productImg64").getAsString();

		product.setPhoto(org.hibernate.Hibernate.createBlob(img64.getBytes()));

		// set product's name
		product.setProductName(productObj.get("productName").getAsString());

		// set origin price
		product.setOrgPrice(productObj.get("orgPrice").getAsBigDecimal());

		// set default sale price
		product.setSalePrice(productObj.get("orgPrice").getAsBigDecimal());

		// set product type
		ProductType productType = productTypeBO.getByID(productObj.get(
				"productTypeID").getAsInt());
		product.setTypeID(productType);

		// set product unit
		ProductUnit productUnit = productUnitBO.getByID(productObj.get(
				"productUnitID").getAsInt());
		product.setUnitID(productUnit);

		// set manufacture
		Manufacture manufacture = manufactureBO.getByID(productObj.get(
				"manufactureID").getAsInt());
		product.setManufactureID(manufacture);

		// set provider
		Provider provider = providerBO.getByID(productObj.get("providerID")
				.getAsInt());
		product.setProviderID(provider);

		// set minimum stock
		product.setMinStock(productObj.get("minStock").getAsInt());

		// set maximum stock
		product.setMaxStock(productObj.get("maxStock").getAsInt());

		// set description
		product.setDescription(productObj.get("description").getAsString());

		int isEdit = productObj.get("isEdit").getAsInt();

		if (isEdit == 0) {// create new
			if (productBO.insertProduct(product)) {
				return "{\"result\" : \"1\", \"isEdit\" : \"1\"}";
			}
		} else {// update
			product.setProductID(productObj.get("productID").getAsInt());
			if (productBO.updateProduct(product)) {
				return "{\"result\" : \"2\", \"isEdit\" : \"1\"}";
			}
		}

		return "{\"result\" : \"0\"}";
	}

}
