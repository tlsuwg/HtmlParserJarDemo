package com.suyi.html.parser.main;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.suyi.html.parser.contr.LoadImagePageRunnable;
import com.suyi.html.parser.contr.LoadImageRunnable;
import com.suyi.html.parser.contr.SDRunnable;
import com.suyi.html.parser.domain.Channel;
import com.suyi.html.parser.domain.ImageUrl;
import com.suyi.html.parser.domain.Page;

public class Main {
	
	static final int isLoadImage = 1;
	static final int loadImageThreadCache = 3;

	public static void main(String[] args) {
		Main mMain = new Main();
		mMain.load();
	}

	Vector<Page> listForPage = new Vector<>();
	Vector<ImageUrl> listForImage = new Vector<>();
	ExecutorService fixedThreadPool;
	
	
	private void load() {
		// TODO Auto-generated method stub
		Channel mChannel = new Channel("http://www.2016zv.com/", "tupianqu/yazhou",4);
		
//		下载image持续
		if (isLoadImage == 1) {
			 fixedThreadPool = Executors.newFixedThreadPool(loadImageThreadCache);
			for (int i = 0; i < loadImageThreadCache*3; i++) {
				fixedThreadPool.execute(new LoadImageRunnable(listForImage, mChannel, i));//
			}
		}
		
//		添加任务
		final LoadImagePageRunnable mLoadImagePage = new LoadImagePageRunnable(mChannel,listForImage,loadImageThreadCache);
		Thread mThreadforpage = new Thread(mLoadImagePage);
		mThreadforpage.setPriority(10);
		mThreadforpage.start();
		
//		监听磁盘
		final SDRunnable mSDRunnable = new SDRunnable(mLoadImagePage);
		Thread mThread = new Thread(mSDRunnable);
		mThread.setPriority(10);
		mThread.start();
		

	

	}
}
