package net.hamtag.server.api.request.types.ads.panel;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Response;

import org.hibernate.Query;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.hamtag.server.api.request.types.BaseWebPanelRequest;
import net.hamtag.server.api.response.HamtagResponse;
import net.hamtag.server.core.RootMgr;
import net.hamtag.server.datatypes.category.Category;
import net.hamtag.server.datatypes.category.CategoryMgr;

public class GetUserNumberForFilterRequest extends BaseWebPanelRequest {
	String requestJson;
	AdReportRequestJson requestInputJson;

	private enum Error {
		CATEGORY_DOES_NOT_EXIST, JSON_PARSE_EXCEPTION,
	}

	public GetUserNumberForFilterRequest(String requestJson) {
		this.requestJson = requestJson;
	}

	public Response handle() {
		Set<String> authorizedRoles = new HashSet<>();
		authorizedRoles.add("ROLE_ADMIN");
		Response response = auth(authorizedRoles);
		if (response != null)
			return response;
		try {
			this.requestInputJson = new ObjectMapper().readValue(requestJson,
					AdReportRequestJson.class);
		} catch (IOException e) {
			e.printStackTrace();
			return new HamtagResponse(Error.JSON_PARSE_EXCEPTION)
					.getResponse(Response.Status.BAD_REQUEST);
		}
		Set<Category> categories = new HashSet<>();
		if (requestInputJson.getCategories() != null
				&& !requestInputJson.getCategories().isEmpty())
			for (String categoryName : requestInputJson.getCategories()) {
				Category category = CategoryMgr.getCategoryByName(categoryName);
				if (category == null)
					return new HamtagResponse(Error.CATEGORY_DOES_NOT_EXIST)
							.getResponse(Response.Status.BAD_REQUEST);
				categories.add(category);
			}
		Integer maxCharge=requestInputJson.getMaxPrice();
		Integer minCharge=requestInputJson.getMinPrice();
		//TODO: CONFIG!
		String stringQuery="select count(id)from devices dev where dev.id in (select distinct (deviceid) "
				+ " from news_display nwsp "
				+ "where nwsp.newsid in (select distinct id from news nw where "
				+ "nw.publish_time BETWEEN LOCALTIMESTAMP - INTERVAL '10 days' AND LOCALTIMESTAMP ";
		if(!categories.isEmpty()){
			stringQuery+="and nw.id in(select distinct newsid from news_category where ";
			for(Category category:categories)
				stringQuery+="categoryid= "+category.getId()+" or ";
			stringQuery+="FALSE))";
		}
		else
			stringQuery+=")";
		stringQuery+=" and nwsp.showdate BETWEEN LOCALTIMESTAMP - INTERVAL '7 days' AND LOCALTIMESTAMP)";
		if(maxCharge!=null)
			stringQuery+=" and dev.charge<"+maxCharge;
		if(minCharge!=null)
			stringQuery+=" and dev.charge>"+minCharge;
		//stringQuery+=")";
	//	System.out.println(stringQuery);
		Query query=RootMgr.getInstance().createSQLQuery(stringQuery);
		return new HamtagResponse(query.uniqueResult()).getResponse(null);
	}
}
