package com.suyi.html.parser.net;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.suyi.html.parser.domain.Channel;
import com.suyi.html.parser.domain.Page;
import com.suyi.html.parser.util.StringSplit;
import com.suyi.html.parser.util.StringUtil;

public class PageMaker {
	//

	public void make(String pageUrl,Channel mChannel, List list) throws ParserException,MalformedURLException, IOException {
		
		
		System.out.println("11111111111111111111"+pageUrl);
		Parser parser = new Parser((HttpURLConnection) (new URL(pageUrl)).openConnection());
		NodeFilter filter = new TagNameFilter("ul");
		NodeList nodes = parser.extractAllNodesThatMatch(filter);
		if (nodes != null) {
			for (int i = 0; i < nodes.size(); i++) {
				Node ul = (Node) nodes.elementAt(i);
				// message("getText:" + ul.getText());
				NodeList nodesUls = ul.getChildren();
				if (nodesUls != null) {
					for (int k = 0; k < nodesUls.size(); k++) {
						Node ulItem = (Node) nodesUls.elementAt(k);
						// message(" ulItem getText:" + ulItem.toHtml());

						if (ulItem != null) {
							String ht = ulItem.toHtml();
					
							if(!StringUtil.isEmp(ht)&&ht.contains("="))
							try {
								StringSplit mStringSp = new StringSplit(ht, "=");
								String url = mStringSp.getIndex(1).split(" ")[0]
										.replace("\"", "");
								String name = mStringSp.getIndex(2).split(" ")[0]
										.replace("\"", "");
								Page pa = new Page(url, name,mChannel);
								System.out.println(url + "   " + name);
								list.add(pa);
								synchronized (list) {
									list.notifyAll();
								}
							
							} catch (Exception e) {
//								e.printStackTrace();
								continue;
							}
						}
					}
				}
			}
		}
	}



}
