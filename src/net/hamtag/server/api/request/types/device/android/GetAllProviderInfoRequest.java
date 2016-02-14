package net.hamtag.server.api.request.types.device.android;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import net.hamtag.server.api.request.types.BaseDeviceRequest;
import net.hamtag.server.api.response.HamtagResponse;
import net.hamtag.server.api.response.types.device.ProviderInfoDTO;
import net.hamtag.server.datatypes.contentprovider.ContentProvider;
import net.hamtag.server.datatypes.contentprovider.ContentProviderMgr;

public class GetAllProviderInfoRequest extends BaseDeviceRequest{
	public GetAllProviderInfoRequest (String phoneNumber,String token){
		setPhoneNumber(phoneNumber);
		setToken(token);
	}
	public Response handle(){
		Response response=auth();
		if(response!=null)
			return response;
		List<ProviderInfoDTO>dtos=new ArrayList<>();
		for(ContentProvider cp:ContentProviderMgr.list()){
			ProviderInfoDTO dto=new ProviderInfoDTO();
			dto.setId(cp.getId());
			dto.setName(cp.getName());
			dtos.add(dto);
		}
		return new HamtagResponse(dtos).getResponse(null);
	}
}
