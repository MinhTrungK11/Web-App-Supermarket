package util;

import java.io.File;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import constants.GlobalConstants;

public class FileUtil {

	public static String rename(String fileName, int c) {
            String nameFile = null;
            if(c==0)
            {
                nameFile = fileName + "." + "jpg";
                
            }
            else
            {
                nameFile = fileName + "." + String.valueOf(c) + "." + "jpg";
            }
            return nameFile;
	}

	public static String getName(final Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}	
	// xóa file ảnh đã có trong thư mục
	public static void delFile(String fileName, HttpServletRequest request) {
		if(!"".equals(fileName)) {
			String filePart = request.getServletContext().getRealPath("")+
					GlobalConstants.DIR_UPLOAD + File.separator + fileName;
			File file = new File(filePart);
			file.delete();
		}
	}

}
