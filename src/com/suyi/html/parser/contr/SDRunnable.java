package com.suyi.html.parser.contr;
import java.io.File;


public class SDRunnable implements Runnable{
	File diskPartition = new File("d:");
	long totalCapacity = diskPartition.getTotalSpace();
	long freePartitionSpace = diskPartition.getFreeSpace();
	long usablePatitionSpace = diskPartition.getUsableSpace();

	long sizeG = 1024 * 1024 * 1024;
	static long leftSize = 6;// 剩余
	LoadImagePageRunnable mLoadImagePage;
	
	public SDRunnable(LoadImagePageRunnable mLoadImagePage) {
		// TODO Auto-generated constructor stub
		this.mLoadImagePage=mLoadImagePage;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("磁盘" + totalCapacity / sizeG + "G;可以使用"
				+ freePartitionSpace / sizeG + "G");

		while (true) {
			long freePartitionSpaceNow = diskPartition.getFreeSpace();
			long usablePatitionSpaceNow = diskPartition
					.getUsableSpace();
			if (freePartitionSpaceNow / (sizeG) < 6) {
				mLoadImagePage.stop();
			}

			System.out.println("使用："
					+ (usablePatitionSpaceNow - usablePatitionSpace)/ sizeG + "G");

			try {
				Thread.sleep(1000 * 5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
