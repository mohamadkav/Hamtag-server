package net.hamtag.server.api.request.types.news;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import net.hamtag.server.api.request.types.BaseDeviceRequest;
import net.hamtag.server.api.response.HamtagResponse;
import net.hamtag.server.api.response.types.news.NewsDTO;
import net.hamtag.server.datatypes.contentprovider.ContentProvider;
import net.hamtag.server.datatypes.contentprovider.ContentProviderMgr;
import net.hamtag.server.datatypes.news.News;
import net.hamtag.server.datatypes.news.NewsContentMgr;
import net.hamtag.server.datatypes.news.NewsMgr;
import net.hamtag.server.utils.CategoryToStringConverter;

public class GetNewsByProviderRequest extends BaseDeviceRequest {
	private enum Error {
		PROVIDER_NOT_FOUND
	}

	String providerId, beforeTime, maxResults;

	public GetNewsByProviderRequest(String providerId, String beforeTime, String maxResults, String phoneNumber,
			String token) {
		this.providerId = providerId;
		this.beforeTime = beforeTime;
		this.maxResults = maxResults;
		setPhoneNumber(phoneNumber);
		setToken(token);
	}

	public Response handle() {
		Response response=auth();
		if(response!=null)
			return response;
		ContentProvider contentProvider;
		try {
			contentProvider = ContentProviderMgr.getInstance().get(ContentProvider.class, Long.parseLong(providerId));
			contentProvider.getName();
		} catch (NullPointerException|NumberFormatException e) {
			return new HamtagResponse(Error.PROVIDER_NOT_FOUND).getResponse(Response.Status.BAD_REQUEST);
		}
		List<NewsDTO>dtos=new ArrayList<>();
		List<News>allNews=NewsMgr.getNewsByContentProviderAndDate(contentProvider, beforeTime, maxResults);
		for (News news : allNews) {
			NewsDTO dto = new NewsDTO();
			dto.setId(news.getId());
			dto.setDate(news.getPublishTime().getTime() + "");
			dto.setText(news.getText());
			dto.setTitle(news.getTitle());
			dto.setCategories(CategoryToStringConverter.getCategoryList(news.getCategories()));
			try {
				dto.setProviderId(news.getContentProvider().getId());
				dto.setProviderName(news.getContentProvider().getName());
			} catch (NullPointerException e) {
			}
			dto.setContentInfos(NewsContentMgr.getContentInfoByNews(news));
			dtos.add(dto);
		}
		return new HamtagResponse(dtos).getResponse(null);
	}
}
