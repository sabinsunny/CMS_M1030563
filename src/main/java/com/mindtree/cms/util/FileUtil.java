/**
 * 
 */
package com.mindtree.cms.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Sabin
 *
 */
@Component
public class FileUtil {
	public String Base64ImgConvertor(byte[] file) {
		String imgBase64encoded = null;
		try {
			byte[] encodeBase64 = Base64.encodeBase64(file);
			imgBase64encoded = new String(encodeBase64, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imgBase64encoded;
	}
	public byte[] convertToBytes(MultipartFile file)
	{   byte[] bytes = null;
		try {
			bytes = file.getBytes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bytes;
	}
}
