package com.suyi.html.parser.net;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.suyi.html.parser.domain.Page;
import com.suyi.html.parser.util.StringSplit;
import com.suyi.html.parser.util.StringUtil;

public class ImageMaker {

	public void make(Page urlforgetpages) throws ParserException,
			MalformedURLException, IOException {
		
		String rul=urlforgetpages.mChannel.Hosturl+""+urlforgetpages.url;
		
		System.out.println("222222222222222222"+rul);
		Parser parser = new Parser((HttpURLConnection) (new URL(
				rul)).openConnection());

		NodeFilter filter = new TagNameFilter("div");
		NodeList nodes = parser.extractAllNodesThatMatch(filter);
		if (nodes != null) {
			for (int i = 0; i < nodes.size(); i++) {
				Node ul = (Node) nodes.elementAt(i);
				// message("getText:" + ul.getText());

				if ("div class=\"news\"".equals(ul.getText())) {
					NodeList nodesUls = ul.getChildren();
					if (nodesUls != null) {
						int index=1;
						for (int k = 0; k < nodesUls.size(); k++) {
							Node ulItem = (Node) nodesUls.elementAt(k);
							// message(" ulItem getText:" + ulItem.toHtml());
							if (ulItem != null) {
								String ht = ulItem.toHtml();
								if (!StringUtil.isEmp(ht) && ht.contains("img"))
									try {
										// System.out.println(ht);
										StringSplit mStringSp = new StringSplit(ht,
												"='");
										String url = mStringSp.getIndex(1)
												.split("' >")[0];
										System.out.println("33333333333333333333"+url);
										urlforgetpages.addUrl(url,index++);
									} catch (Exception e) {
										continue;
									}
							}
						}
					}
				}
			}
		}
	}



}
