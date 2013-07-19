package zip;

import java.io.*;
import java.util.LinkedList;
import java.util.zip.*;
/**
 * ����ʵ����ZIPѹ��������Ϊ2���� ��
 * ѹ����compression�����ѹ��decompression��
 * <p>
 * ���¹��ܰ������˶�̬���ݹ��JAVA���ļ��������ԶԵ����ļ������⼶���ļ��н���ѹ���ͽ�ѹ��
 * ���ڴ������Զ���Դ����·����Ŀ�����·����
 * <p>
 * �ڱ��δ����У�ʵ�ֵ��ǽ�ѹ���֣�ѹ�����ּ�������compression���֡�
 * @author HAN
 *
 */
public class ZipDecompress {
	public static LinkedList<File> unzip(String path){
		String outpath = new File(path).getParent();
		return unzip(path,outpath);
	}
	public static LinkedList<File> unzip(String path,String outpath){
		LinkedList<File> fl = new LinkedList<File>();
		long startTime=System.currentTimeMillis();
		try {
			ZipInputStream Zin=new ZipInputStream(new FileInputStream(path));//����Դzip·��
			BufferedInputStream Bin=new BufferedInputStream(Zin);
			String Parent=outpath; //���·�����ļ���Ŀ¼��
			File Fout=null;
			ZipEntry entry;
			try {
				while((entry = Zin.getNextEntry())!=null && !entry.isDirectory()){
					Fout=new File(Parent,entry.getName());
					if(!Fout.exists()){
						(new File(Fout.getParent())).mkdirs();
					}
					if(!Fout.isDirectory()){
						fl.add(Fout);
					}
					FileOutputStream out=new FileOutputStream(Fout);
					BufferedOutputStream Bout=new BufferedOutputStream(out);
					int b;
					while((b=Bin.read())!=-1){
						Bout.write(b);
					}
					Bout.close();
					out.close();
					System.out.println(Fout+"��ѹ�ɹ�");	
				}
				Bin.close();
				Zin.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		long endTime=System.currentTimeMillis();
		System.out.println("�ķ�ʱ�䣺 "+(endTime-startTime)+" ms");
		return fl;
	}
	public static void main(String[] args) {
		System.out.println(unzip("D://20130719.zip"));
	}
}
