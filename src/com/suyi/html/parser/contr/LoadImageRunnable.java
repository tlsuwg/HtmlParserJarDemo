package com.suyi.html.parser.contr;
import java.io.IOException;
import java.util.Vector;

import com.suyi.html.parser.domain.Channel;
import com.suyi.html.parser.domain.ImageUrl;
import com.suyi.html.parser.net.ImageLoader;

public class LoadImageRunnable implements Runnable {

	Vector<ImageUrl> listForImage = new Vector<>();
	int name;
	boolean isRun = true;

	public LoadImageRunnable(Vector<ImageUrl> listForImage, Channel mChannel, int i) {
		// TODO Auto-generated constructor stub
		this.listForImage = listForImage;
		this.name=i;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (isRun) {
			ImageUrl mImageUrl = getOneUrl();
			if (mImageUrl != null) {
				
				try {
					String info=ImageLoader.saveToFile(mImageUrl.url, mImageUrl.getFilePath());
					System.out.println("ÏÂÔØ"+mImageUrl.url+"________________"+info);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

			} else {
				System.out.println("LoadImage"+name+"½áÊø");
				break;
			}
		}
	}
	
	public void stop(){
		isRun=false;
		synchronized (listForImage) {
			listForImage.notifyAll();
		}
	}

	private ImageUrl getOneUrl() {
		// TODO Auto-generated method stub
		if (listForImage.size() > 0) {
			if (isRun)
				return listForImage.remove(0);
			else
				return null;
		} else {
			synchronized (listForImage) {
				try {
					listForImage.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			return getOneUrl();
		}
	}

}
