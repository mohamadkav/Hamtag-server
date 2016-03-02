package net.hamtag.server.api.request.types.news.panel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.Response;

import net.hamtag.server.api.request.types.BaseWebPanelRequest;
import net.hamtag.server.api.response.HamtagResponse;
import net.hamtag.server.api.response.types.news.NewsDTO;
import net.hamtag.server.datatypes.news.News;
import net.hamtag.server.datatypes.news.NewsContentMgr;
import net.hamtag.server.datatypes.news.NewsMgr;
import net.hamtag.server.utils.CategoryToStringConverter;

public class GetNewsRequest extends BaseWebPanelRequest{
	Integer maxResults;
	Long beforeTime;
	public GetNewsRequest(String maxResults,String beforeTime){
		if(maxResults!=null)
			this.maxResults=Integer.parseInt(maxResults);
		if(beforeTime!=null)
			this.beforeTime=Long.parseLong(beforeTime);
	}
	@Override
	public Response handle() {
		Set<String>authorizedRoles=new HashSet<>();
		authorizedRoles.add("ROLE_ADMIN");
		authorizedRoles.add("ROLE_WRITER");
		authorizedRoles.add("ROLE_DEVICE");
		Response response=auth(authorizedRoles);
		if(response!=null)
			return response;
		List<News>allNews=NewsMgr.getNewsByPublishTime(maxResults, beforeTime);
		List<NewsDTO>dtos=new ArrayList<>();
		for(News news:allNews){
			NewsDTO dto=new NewsDTO();
			dto.setId(news.getId());
			dto.setDate(news.getPublishTime().getTime()+"");
			dto.setText(news.getText());
			dto.setTitle(news.getTitle());
			dto.setCategories(CategoryToStringConverter.getCategoryList(news.getCategories()));
			try{
			dto.setProviderId(news.getContentProvider().getId());
			dto.setProviderName(news.getContentProvider().getName());
			}catch(NullPointerException e){}
			dto.setContentInfos(NewsContentMgr.getContentInfoByNews(news));
			dtos.add(dto);
		}
		return new HamtagResponse(dtos).getResponse(null);
	}
	
}
