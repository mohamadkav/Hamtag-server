package net.hamtag.server.api.request.handler.news;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.Response;

import net.hamtag.server.api.request.handler.BaseRequestHandler;
import net.hamtag.server.api.request.types.news.GetNewsByCategoryForDeviceRequest;
import net.hamtag.server.api.response.HamtagResponse;
import net.hamtag.server.api.response.types.news.NewsDTO;
import net.hamtag.server.datatypes.category.Category;
import net.hamtag.server.datatypes.news.News;
import net.hamtag.server.datatypes.news.NewsContentMgr;
import net.hamtag.server.datatypes.news.NewsMgr;

public class GetNewsByCategoryForDeviceRequestHandler extends BaseRequestHandler{
	private GetNewsByCategoryForDeviceRequest request;
	public GetNewsByCategoryForDeviceRequestHandler(GetNewsByCategoryForDeviceRequest req){
		this.request=req;
	}
	@Override
	public Response handle() {
		List<NewsDTO>dtos=new ArrayList<>();
		Response response=request.auth();
		if(response!=null)
			return response;
		List<News>allNews=NewsMgr.getNewsByDate(request.getLastUpdateTime(), request.getNumber());
		for(News news:allNews){
			NewsDTO dto=new NewsDTO();
			dto.setId(news.getId());
			dto.setDate(news.getPublishTime().getTime()+"");
			dto.setText(news.getText());
			dto.setTitle(news.getTitle());
			dto.setCategories(getCategoryList(news.getCategories()));
			dto.setContentInfos(NewsContentMgr.getContentInfoByNews(news));
			dtos.add(dto);
		}
		return new HamtagResponse(dtos).getResponse(null);
	}
	public static List<String> getCategoryList(Set<Category> categories){
		List<String>toReturn=new ArrayList<>();
		for(Category category:categories)
			toReturn.add(category.getName());
		return toReturn;
	}
}
