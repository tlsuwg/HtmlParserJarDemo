package com.suyi.html.parser.domain;

import java.util.ArrayList;
import java.util.List;

public class Channel {
	public List<Page> list = new ArrayList<Page>();
	public String Hosturl, name,url;
	public Channel(String url1,String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.Hosturl=url1;
		this.url=Hosturl+"/"+name+"/";
	}
	volatile int index=2;
	
	public synchronized String getMore(){
//		2016zv.com/tupianqu/yazhou/index_2.html	
		String urlc=this.url+"index_"+(index++)+".html";
		return urlc;
	}

}
