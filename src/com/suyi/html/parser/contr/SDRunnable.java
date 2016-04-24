package com.suyi.html.parser.contr;
import java.io.File;


public class SDRunnable implements Runnable{
	File diskPartition = new File("d:");
	long totalCapacity = diskPartition.getTotalSpace();
	long freePartitionSpace = diskPartition.getFreeSpace();
	long usablePatitionSpace = diskPartition.getUsableSpace();

	long sizeM = 1024 * 1024 ;
	static long leftSize = 6;// ʣ��
	LoadImagePageRunnable mLoadImagePage;
	
	public SDRunnable(LoadImagePageRunnable mLoadImagePage) {
		// TODO Auto-generated constructor stub
		this.mLoadImagePage=mLoadImagePage;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("����" + totalCapacity / sizeM + "M;����ʹ��"
				+ freePartitionSpace / sizeM + "");

		while (true) {
			long freePartitionSpaceNow = diskPartition.getFreeSpace();
			long usablePatitionSpaceNow = diskPartition
					.getUsableSpace();
			if (freePartitionSpaceNow / (sizeM) < 6) {
				mLoadImagePage.stop();
			}

			System.out.println("ʹ�ã�"
					+ (usablePatitionSpaceNow - usablePatitionSpace)/ sizeM + "M");

			try {
				Thread.sleep(1000 * 45);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
