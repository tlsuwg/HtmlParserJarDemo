package com.suyi.html.parser.domain;

import java.io.File;

public class ImageUrl {

	public Page mPage;
	public String url;
	int index;

	public ImageUrl(String url2, Page mPage,int index) {
		// TODO Auto-generated constructor stub
		this.url = url2;
		this.mPage = mPage;
		this.index=index;
	}
	
	public String getFilePath(){
		return "D://htmlLoad/image/"+mPage.mChannel.name+File.separator+mPage.name+File.separator+index+".jpg";
	}
}
