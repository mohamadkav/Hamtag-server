package net.hamtag.server.api.request.types.news.panel;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Response;

import org.springframework.security.core.context.SecurityContextHolder;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.hamtag.server.api.request.types.BaseWebPanelRequest;
import net.hamtag.server.api.response.HamtagResponse;
import net.hamtag.server.datatypes.category.Category;
import net.hamtag.server.datatypes.category.CategoryMgr;
import net.hamtag.server.datatypes.contentprovider.ContentProvider;
import net.hamtag.server.datatypes.contentprovider.ContentProviderMgr;
import net.hamtag.server.datatypes.news.News;
import net.hamtag.server.datatypes.news.NewsMgr;
import net.hamtag.server.datatypes.user.User;
import net.hamtag.server.datatypes.user.UserMgr;
import net.hamtag.server.datatypes.user.UserRole;

public class AddNewsRequest extends BaseWebPanelRequest {
	private enum Error {
		CATEGORY_DOES_NOT_EXIST, CATEGORIES_MUST_BE_SELECTED, JSON_PARSE_EXCEPTION, PROVIDER_VISIBILITY_ERROR
	}

	String requestJson;
	NewsInputRequestJson requestInputJson;

	public AddNewsRequest(String requestJson) {
		this.requestJson = requestJson;
	}

	@Override
	public Response handle() {
		Set<String> authorizedRoles = new HashSet<>();
		authorizedRoles.add("ROLE_ADMIN");
		authorizedRoles.add("ROLE_WRITER");
		Response response = auth(authorizedRoles);
		if (response != null)
			return response;
		try {
			this.requestInputJson = new ObjectMapper().readValue(requestJson, NewsInputRequestJson.class);
		} catch (IOException e) {
			e.printStackTrace();
			return new HamtagResponse(Error.JSON_PARSE_EXCEPTION).getResponse(Response.Status.BAD_REQUEST);
		}
		Set<Category> categories = new HashSet<>();
		if (requestInputJson.getCategories() == null || requestInputJson.getCategories().isEmpty())
			return new HamtagResponse(Error.CATEGORIES_MUST_BE_SELECTED).getResponse(Response.Status.BAD_REQUEST);
		for (String categoryName : requestInputJson.getCategories()) {
			Category category = CategoryMgr.getCategoryByName(categoryName);
			if (category == null)
				return new HamtagResponse(Error.CATEGORY_DOES_NOT_EXIST).getResponse(Response.Status.BAD_REQUEST);
			categories.add(category);
		}
		News news = new News();
		news.setCategories(categories);
		ContentProvider contentProvider = ContentProviderMgr.getInstance().get(ContentProvider.class,
				requestInputJson.getProviderId());
		User user = UserMgr.getByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		boolean canSeeAll = false;
		for (UserRole roles : user.getUserRoles()) {
			if (roles.getRole().equals("ROLE_ADMIN")) {
				canSeeAll = true;
				break;
			}
		}
		if (canSeeAll)
			news.setContentProvider(contentProvider);
		else {
			Set<ContentProvider> visibleProviders = user.getVisibleProviders();
			if (!visibleProviders.contains(contentProvider))
				return new HamtagResponse(Error.PROVIDER_VISIBILITY_ERROR).getResponse(Response.Status.BAD_REQUEST);
			news.setContentProvider(contentProvider);
		}
		news.setPublishTime(new Date());
		news.setText(requestInputJson.getText());
		news.setTitle(requestInputJson.getTitle());
		news.setUser(user);
		news.setId(null);
		NewsMgr.add(news);
		return new HamtagResponse(news.getId()).getResponse(null);
	}

}
