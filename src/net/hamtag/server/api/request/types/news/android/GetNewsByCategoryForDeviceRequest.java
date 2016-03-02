package net.hamtag.server.api.request.types.news.android;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Response;

import net.hamtag.server.api.request.types.BaseDeviceRequest;
import net.hamtag.server.api.response.HamtagResponse;
import net.hamtag.server.api.response.types.news.NewsDTO;
import net.hamtag.server.datatypes.news.News;
import net.hamtag.server.datatypes.news.NewsContentMgr;
import net.hamtag.server.datatypes.news.NewsMgr;
import net.hamtag.server.utils.CategoryToStringConverter;

public class GetNewsByCategoryForDeviceRequest extends BaseDeviceRequest{
	private String maxNumber,lastUpdateTime;
	public GetNewsByCategoryForDeviceRequest(String maxNumber,String lastupdateTime,String token,String phoneNumber){
		this.maxNumber=maxNumber;
		this.lastUpdateTime=lastupdateTime;
		setToken(token);
		setPhoneNumber(phoneNumber);
	}
	@Override
	public Response handle() {
		List<NewsDTO>dtos=new ArrayList<>();
		Response response=auth();
		if(response!=null)
			return response;
		List<News>allNews=NewsMgr.getNewsByDate(lastUpdateTime, maxNumber);
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
