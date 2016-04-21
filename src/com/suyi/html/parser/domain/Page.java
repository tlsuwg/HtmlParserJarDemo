package com.suyi.html.parser.domain;

import java.util.ArrayList;
import java.util.List;

public class Page {

	public List<ImageUrl> list = new ArrayList<ImageUrl>();
	public String url, name;
	public Channel mChannel;

	public Page(String url, String name, Channel mChannel) {
		// TODO Auto-generated constructor stub
		this.url = url;
		this.name = name;
		this.mChannel=mChannel;
	}

	public void addUrl(String url,int i) {
		list.add(new ImageUrl(url,this,i));
	}

}
