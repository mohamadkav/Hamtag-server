package net.hamtag.server.api.request.types.news.panel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.Response;

import net.hamtag.server.api.request.types.BaseWebPanelRequest;
import net.hamtag.server.api.response.HamtagResponse;
import net.hamtag.server.api.response.types.ads.CountShownDTO;
import net.hamtag.server.datatypes.news.NewsShownMgr;

public class GetNewsCountShownForWeekRequest extends BaseWebPanelRequest{
	@Override
	public Response handle() {
		Set<String>neededRoles=new HashSet<>();
		neededRoles.add("ROLE_ADMIN");
		Response response=auth(neededRoles);
		if(response!=null)
			return response;
		List<CountShownDTO>dtos=new ArrayList<>();
		for(int i=0;i<7;i++){
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -1);
			CountShownDTO dto=new CountShownDTO();
			dto.setDaysBefore(i);
			dto.setCount(NewsShownMgr.getCountShownForDay(cal.getTime()));
			dtos.add(dto);
		}
		return new HamtagResponse(dtos).getResponse(null);
	}
}
