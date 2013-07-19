package zip;

import java.io.*;
import java.util.zip.*;

/**
 * 程序实现了ZIP压缩。共分为2部分 ： 压缩（compression）与解压（decompression）
 * <p>
 * 大致功能包括用了多态，递归等JAVA核心技术，可以对单个文件和任意级联文件夹进行压缩和解压。 需在代码中自定义源输入路径和目标输出路径。
 * <p>
 * 在本段代码中，实现的是压缩部分；解压部分见本包中Decompression部分。
 * 
 * @author HAN
 * 
 */

public class ZipCompress {

	public static void zip(String zipFileName, String inputFile) throws Exception {
		File [] file = new File[]{new File(inputFile)};
		zip(zipFileName,file);
	}
	
	public static void zip(String zipFileName, String[] inputFile) throws Exception {
		File [] file = new File[inputFile.length];
		for (int i = 0; i < file.length; i++) {
			file[i] = new File(inputFile[i]);
		}
		zip(zipFileName,file);
	}
	
	public static void zip(String zipFileName, File[] inputFile) throws Exception {
		System.out.println("压缩中...");
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
				zipFileName));
		BufferedOutputStream bo = new BufferedOutputStream(out);
		_zip(out, inputFile, bo);
		bo.close();
		out.close(); // 输出流关闭
		System.out.println("压缩完成");
	}
	
	private static void _zip(ZipOutputStream out, File[] fl,
			BufferedOutputStream bo) throws Exception { // 方法重载
		for (int i = 0; i < fl.length; i++) {
			out.putNextEntry(new ZipEntry(fl[i].getName())); // 创建zip压缩进入点base
			System.out.println(fl[i].getAbsolutePath());
			FileInputStream in = new FileInputStream(fl[i]);
			BufferedInputStream bi = new BufferedInputStream(in);
			int b;
			while ((b = bi.read()) != -1) {
				bo.write(b); // 将字节流写入当前zip目录
			}
			bi.close();
			in.close(); // 输入流关闭
		}
	}
	
	public static void zip(String zipFileName, File inputFile) throws Exception {
		System.out.println("压缩中...");
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
				zipFileName));
		BufferedOutputStream bo = new BufferedOutputStream(out);
		_zip(out, inputFile, inputFile.getName(), bo);
		bo.close();
		out.close(); // 输出流关闭
		System.out.println("压缩完成");
	}

	private static void _zip(ZipOutputStream out, File f, String base,
			BufferedOutputStream bo) throws Exception { // 方法重载
		int k = 1; // 定义递归次数变量
		if (f.isDirectory()) {
			File[] fl = f.listFiles();
			if (fl.length == 0) {
				out.putNextEntry(new ZipEntry(base + "/")); // 创建zip压缩进入点base
				System.out.println(base + "/");
			}
			for (int i = 0; i < fl.length; i++) {
				_zip(out, fl[i], base + "/" + fl[i].getName(), bo); // 递归遍历子文件夹
			}
			System.out.println("第" + k + "次递归");
			k++;
		} else {
			out.putNextEntry(new ZipEntry(base)); // 创建zip压缩进入点base
			System.out.println(base);
			FileInputStream in = new FileInputStream(f);
			BufferedInputStream bi = new BufferedInputStream(in);
			int b;
			while ((b = bi.read()) != -1) {
				bo.write(b); // 将字节流写入当前zip目录
			}
			bi.close();
			in.close(); // 输入流关闭
		}
	}
	
	public static void main(String args[]){
		try {
			zip("D://4877X2461.png.zip","D://4877X2461.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
