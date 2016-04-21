package com.suyi.html.parser.contr;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Vector;

import org.htmlparser.util.ParserException;

import com.suyi.html.parser.domain.Channel;
import com.suyi.html.parser.domain.ImageUrl;
import com.suyi.html.parser.domain.Page;
import com.suyi.html.parser.net.ImageMaker;
import com.suyi.html.parser.net.PageMaker;

public class LoadImagePageRunnable implements Runnable {

	Vector<Page> listForPage;

	Channel mChannel;
	Vector<ImageUrl> listForImage;

	PageMaker mPageMaker = new PageMaker();
	ImageMaker mImageMaker = new ImageMaker();
	boolean isRun = true;

	public LoadImagePageRunnable(Channel mChannel, Vector<ImageUrl> listForImage) {
		// TODO Auto-generated constructor stub
		this.listForPage = new Vector<>();
		this.mChannel = mChannel;
		this.listForImage = listForImage;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (isRun) {
			checkPage();
			checkImageList();
		}
	}

	private void checkImageList() {
		// TODO Auto-generated method stub

		if (listForImage.size() > 10) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			Page mPage = getOneUrl();

			if (mPage != null) {

				try {
					mImageMaker.make(mPage);
				} catch (ParserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				listForImage.addAll(mPage.list);
				synchronized (listForImage) {
					listForImage.notifyAll();
				}
			}

			System.out.println("33333333now" + listForImage.size());

			if (isRun)
				checkImageList();
		}

	}

	private void checkPage() {
		// TODO Auto-generated method stub
		if (listForPage.size() <= 3) {
			try {
				mPageMaker.make(mChannel.getMore(),mChannel, listForPage);
			} catch (ParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			if (isRun)
				checkPage();
		}

	}

	private Page getOneUrl() {
		// TODO Auto-generated method stub
		if (listForPage.size() > 0) {
			if (isRun)
				return listForPage.remove(0);
			else
				return null;
		} else {
			checkPage();
			return getOneUrl();
		}

	}

	public void stop() {
		// TODO Auto-generated method stub
		isRun = true;
	}

}
