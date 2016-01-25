/*package net.hamtag.server.api.request.types.device;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Response;

import net.hamtag.server.api.request.handler.BaseRequestHandler;
import net.hamtag.server.api.request.types.BaseDeviceRequest;
import net.hamtag.server.api.response.HamtagResponse;
import net.hamtag.server.datatypes.category.Category;
import net.hamtag.server.datatypes.category.CategoryMgr;
import net.hamtag.server.datatypes.device.Device;
import net.hamtag.server.datatypes.device.DeviceMgr;

public class UpdateDeviceCategoriesRequest extends BaseDeviceRequest{
	private String rawCategories;
	private enum Error{
		CATEGORY_NOT_FOUND
	}
	@Override
	public BaseRequestHandler getHandler() {
		return null;
	}
	public UpdateDeviceCategoriesRequest(String rawCategories,String phone,String token){
		setPhoneNumber(phone);
		setToken(token);
		this.rawCategories=rawCategories;
	}
	public Response handle(){
		String[]categoriesStr=rawCategories.split(",");
		Set<Category>categories=new HashSet<>();
		for(String strcat:categoriesStr){
			Category category=CategoryMgr.getCategoryByName(strcat);
			if(category==null)
				return new HamtagResponse(Error.CATEGORY_NOT_FOUND).getResponse(Response.Status.BAD_REQUEST);
			categories.add(category);
		}
		Device device=DeviceMgr.getDeviceByPhoneNumber(getPhoneNumber());
		device.setCategories(categories);
		DeviceMgr.update(device);
		return new HamtagResponse().getResponse(null);
	}
}
*/