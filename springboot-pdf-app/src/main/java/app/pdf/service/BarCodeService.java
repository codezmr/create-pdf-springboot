/*
 * package app.pdf.service;
 * 
 * import java.io.ByteArrayOutputStream; import java.io.IOException; import
 * java.util.HashMap; import java.util.Map;
 * 
 * import com.google.zxing.BarcodeFormat; import
 * com.google.zxing.EncodeHintType; import com.google.zxing.WriterException;
 * import com.google.zxing.client.j2se.MatrixToImageWriter; import
 * com.google.zxing.common.BitMatrix; import
 * com.google.zxing.oned.Code128Writer;
 * 
 * public class BarCodeService {
 * 
 * public static ByteArrayOutputStream getBarCode(String text) throws
 * IOException, WriterException{
 * 
 * 
 * BitMatrix bitMatrix; Map<EncodeHintType, Integer> hints = new HashMap();
 * hints.put(EncodeHintType.MARGIN, Integer.valueOf(0));
 * 
 * bitMatrix = new Code128Writer().encode(text, BarcodeFormat.CODE_128,
 * text.length() * 11, 20, hints );
 * 
 * ByteArrayOutputStream baos = new ByteArrayOutputStream();
 * 
 * MatrixToImageWriter.writeToStream(bitMatrix, "png", baos);
 * 
 * return baos; }
 * 
 * 
 * public static String getBarCodeAsBase64(String text) {
 * 
 * try { return ImageUtils.convertImageToBase64(getBarCode(text).toByteArray(),
 * ImageUtils.BASE64_IMAGE_PNG);
 * 
 * } catch (IOException e) { e.printStackTrace(); }
 * 
 * catch (WriterException e1) { e1.printStackTrace(); }
 * 
 * 
 * return ""; }
 * 
 * 
 * 
 * }
 * 
 */