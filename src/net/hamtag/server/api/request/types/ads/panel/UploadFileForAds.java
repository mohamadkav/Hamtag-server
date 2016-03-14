package net.hamtag.server.api.request.types.ads.panel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import net.hamtag.server.api.request.types.BaseWebPanelRequest;
import net.hamtag.server.api.response.HamtagResponse;
import net.hamtag.server.datatypes.ad.Ad;
import net.hamtag.server.datatypes.ad.AdContent;
import net.hamtag.server.datatypes.ad.AdContentMgr;
import net.hamtag.server.datatypes.ad.AdMgr;
import net.hamtag.server.utils.Config;

public class UploadFileForAds extends BaseWebPanelRequest{
	private InputStream uploadedInputStream;
	private FormDataContentDisposition fileDetail;
	public UploadFileForAds(InputStream uploadedInputStream,FormDataContentDisposition fileDetail){
		this.uploadedInputStream=uploadedInputStream;
		this.fileDetail=fileDetail;
	}
	private enum Error{
		AD_NOT_FOUND,FILE_DESCRIPTION_INVALID,WRITE_FAILED
	}
	@Override
	public Response handle() {
		Set<String> authorizedRoles = new HashSet<>();
		authorizedRoles.add("ROLE_ADMIN");
		authorizedRoles.add("ROLE_WRITER");
		Response response = auth(authorizedRoles);
		if (response != null)
			return response;
		String uploadedFileLocation =Config.ADS_UPLOAD_LOCATION_FOLDER + fileDetail.getFileName();
		Ad ad=AdMgr.getInstance().get(Ad.class,Long.parseLong(fileDetail.getFileName().split("-")[0]));
		if(ad==null)
			return new HamtagResponse(Error.AD_NOT_FOUND).getResponse(Response.Status.BAD_REQUEST);
		if(fileDetail.getFileName().split("-")[1]!="THUMBNAIL"&&fileDetail.getFileName().split("-")[1]!="IMAGE"&&fileDetail.getFileName().split("-")[1]!="VIDEO")
			return new HamtagResponse(Error.FILE_DESCRIPTION_INVALID).getResponse(Response.Status.BAD_REQUEST);
		//OK. validated. so write to disk...
		writeToFile(uploadedInputStream, uploadedFileLocation);
		//TODO: FILE NAME SHOULD BE:  ADID-TYPE. eg: 3-THUMBNAIL
		File file=new File(Config.ADS_UPLOAD_LOCATION_FOLDER + fileDetail.getFileName());
		AdContent adContent=new AdContent();
		adContent.setAd(ad);
		adContent.setType(fileDetail.getFileName().split("-")[1]);
		byte[] bFile = new byte[(int) file.length()];
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			// convert file into array of bytes
			fileInputStream.read(bFile);
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
			return new HamtagResponse(Error.WRITE_FAILED).getResponse(Response.Status.EXPECTATION_FAILED);
		}
		adContent.setContent(bFile);
		//TODO: LOG THIS CHANGE
		AdContentMgr.add(adContent);
		return new HamtagResponse().getResponse(null);
	}
	
	private void writeToFile(InputStream uploadedInputStream,
		String uploadedFileLocation) {

		try {
			OutputStream out = new FileOutputStream(new File(
					uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
}
