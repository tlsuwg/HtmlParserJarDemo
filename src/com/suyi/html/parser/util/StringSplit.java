package com.suyi.html.parser.util;

public class StringSplit {

	String[] ars;
	String ht, s;

	public StringSplit(String ht, String string) {
		// TODO Auto-generated constructor stub
		this.ht = ht;
		this.s = string;

	}

	public String getIndex(int i) throws Exception {
		if (ars == null) {
			if (StringUtil.isEmp(ht) || StringUtil.isEmp(s))
				throw new Exception("原始数据为null");
			ars = ht.split(s);
		}

		if (ars == null)
			throw new Exception("ars is null");
		
		if(i>=ars.length){
			throw new Exception("not has this ;"+ht+";"+s);
		}
		return ars[i];
	}

}
