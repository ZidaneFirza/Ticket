package main_package;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class BarcodeGenerator {
	public static void generateBarcode(String ticket) {
		int width = 400;
		int height = 300;
		String imageFormat = "png";

		try {
			BitMatrix bitMatrix;
			bitMatrix = new QRCodeWriter().encode(ticket, BarcodeFormat.QR_CODE, width, height);
			MatrixToImageWriter.writeToStream(bitMatrix, imageFormat,
					new FileOutputStream(new File("qrcode.png")));
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
