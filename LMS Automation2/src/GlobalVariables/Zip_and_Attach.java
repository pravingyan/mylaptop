package GlobalVariables;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import GlobalVariables.GlobalDecl;

//ZIP directory and its all sub folder and all files
public class Zip_and_Attach
{
	GlobalDecl GV = new GlobalDecl();

	public  void main(String[] args) throws Exception 

	{
		File directoryToZip = new File(GV.SOURCE_FOLDER);

		List<File> fileList = new ArrayList<File>();
		System.out.println("---Getting references to all files in: " + directoryToZip.getCanonicalPath());
		getAllFiles(directoryToZip, fileList);
		System.out.println("---Creating zip file");
		writeZipFile(directoryToZip, fileList);
		System.out.println("---Done");


		//For Sending Email, Call SendEmail Class file.
		SendEmail e = new SendEmail();
		e.execute();
	}

	public static void getAllFiles(File dir, List<File> fileList) throws IOException 
	{
		File[] files = dir.listFiles();
		for (File file : files) {
			fileList.add(file);
			if (file.isDirectory()) {
				//System.out.println("directory:" + file.getCanonicalPath());
				getAllFiles(file, fileList);
			} else {
				//System.out.println("     file:" + file.getCanonicalPath());
			}
		}
	}

	public  void writeZipFile(File directoryToZip, List<File> fileList) 
	{

		try {
			FileOutputStream fos = new FileOutputStream(GV.OUTPUT_ZIP_FILE);
			ZipOutputStream zos = new ZipOutputStream(fos);

			for (File file : fileList)
			{
				if (!file.isDirectory()) 
				{ // we only zip files, not directories
					addToZip(directoryToZip, file, zos);
				}
			}



			try 
			{
				String path = GV.OUTPUT_PopupFolder;
				Runtime runtime = Runtime.getRuntime();
				runtime.exec("explorer.exe "+path); // Window explorer will dynamically open above path during run time
				System.out.println("Window explorer opened at "+ GV.OUTPUT_PopupFolder);
			} 
			catch (Exception E) 
			{
				System.out.println("Window explorer couldn't able to open at  "+ GV.OUTPUT_PopupFolder);
			}

			zos.close();
			fos.close();
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public static void addToZip(File directoryToZip, File file, ZipOutputStream zos) throws FileNotFoundException,
	IOException 
	{

		FileInputStream fis = new FileInputStream(file);

		// we want the zipEntry's path to be a relative path that is relative
		// to the directory being zipped, so chop off the rest of the path
		String zipFilePath = file.getCanonicalPath().substring(directoryToZip.getCanonicalPath().length() + 1,
				file.getCanonicalPath().length());
		//System.out.println("Writing '" + zipFilePath + "' to zip file");
		ZipEntry zipEntry = new ZipEntry(zipFilePath);
		zos.putNextEntry(zipEntry);

		byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes)) >= 0) 
		{
			zos.write(bytes, 0, length);
		}

		zos.closeEntry();
		fis.close();
	}

}