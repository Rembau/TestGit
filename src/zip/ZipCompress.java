package zip;

import java.io.*;
import java.util.zip.*;

/**
 * ����ʵ����ZIPѹ��������Ϊ2���� �� ѹ����compression�����ѹ��decompression��
 * <p>
 * ���¹��ܰ������˶�̬���ݹ��JAVA���ļ��������ԶԵ����ļ������⼶���ļ��н���ѹ���ͽ�ѹ�� ���ڴ������Զ���Դ����·����Ŀ�����·����
 * <p>
 * �ڱ��δ����У�ʵ�ֵ���ѹ�����֣���ѹ���ּ�������Decompression���֡�
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
		System.out.println("ѹ����...");
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
				zipFileName));
		BufferedOutputStream bo = new BufferedOutputStream(out);
		_zip(out, inputFile, bo);
		bo.close();
		out.close(); // ������ر�
		System.out.println("ѹ�����");
	}
	
	private static void _zip(ZipOutputStream out, File[] fl,
			BufferedOutputStream bo) throws Exception { // ��������
		for (int i = 0; i < fl.length; i++) {
			out.putNextEntry(new ZipEntry(fl[i].getName())); // ����zipѹ�������base
			System.out.println(fl[i].getAbsolutePath());
			FileInputStream in = new FileInputStream(fl[i]);
			BufferedInputStream bi = new BufferedInputStream(in);
			int b;
			while ((b = bi.read()) != -1) {
				bo.write(b); // ���ֽ���д�뵱ǰzipĿ¼
			}
			bi.close();
			in.close(); // �������ر�
		}
	}
	
	public static void zip(String zipFileName, File inputFile) throws Exception {
		System.out.println("ѹ����...");
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
				zipFileName));
		BufferedOutputStream bo = new BufferedOutputStream(out);
		_zip(out, inputFile, inputFile.getName(), bo);
		bo.close();
		out.close(); // ������ر�
		System.out.println("ѹ�����");
	}

	private static void _zip(ZipOutputStream out, File f, String base,
			BufferedOutputStream bo) throws Exception { // ��������
		int k = 1; // ����ݹ��������
		if (f.isDirectory()) {
			File[] fl = f.listFiles();
			if (fl.length == 0) {
				out.putNextEntry(new ZipEntry(base + "/")); // ����zipѹ�������base
				System.out.println(base + "/");
			}
			for (int i = 0; i < fl.length; i++) {
				_zip(out, fl[i], base + "/" + fl[i].getName(), bo); // �ݹ�������ļ���
			}
			System.out.println("��" + k + "�εݹ�");
			k++;
		} else {
			out.putNextEntry(new ZipEntry(base)); // ����zipѹ�������base
			System.out.println(base);
			FileInputStream in = new FileInputStream(f);
			BufferedInputStream bi = new BufferedInputStream(in);
			int b;
			while ((b = bi.read()) != -1) {
				bo.write(b); // ���ֽ���д�뵱ǰzipĿ¼
			}
			bi.close();
			in.close(); // �������ر�
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
