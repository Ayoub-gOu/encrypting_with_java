package cryptage;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Encryption {

	private SecretKey key;
	private int key_Size=128;
	private int T_LEN=128;
	private Cipher encryption;
	

			
	public SecretKey init() throws Exception
	{
		KeyGenerator generator = KeyGenerator.getInstance("AES");
		generator.init(key_Size);
		key=generator.generateKey();
		return key;
	}
	
	public String encrypt(String text) throws Exception
	{
		byte[] textInBytes = text.getBytes();
		encryption=Cipher.getInstance("AES/GCM/NoPadding");
		encryption.init(Cipher.ENCRYPT_MODE,key);
		byte [] iv = encryption.getIV();
		byte [] encrypted=encryption.doFinal(textInBytes);	
//		return encode(encrypted);
		
		
		byte[] combined = new byte[iv.length + encrypted.length];
	    System.arraycopy(iv, 0, combined, 0, iv.length);
	    System.arraycopy(encrypted, 0, combined, iv.length, encrypted.length);
 
	    return encode(combined);
	}
	
	private static String encode (byte[] data)
	{
		return Base64.getEncoder().encodeToString(data);
	}
	private static byte[] decode (String data)
	{
		return Base64.getDecoder().decode(data);
	}
	public String decrypt(String encrypted) throws Exception
	{
		byte[] combined = decode(encrypted);

		 // Extract IV from the first 12 bytes
		byte[] iv = new byte[12];
	    byte[] ciphertext = new byte[combined.length - 12];
		
//		byte[] textInBytes = decode(encrypted);
//		Cipher decryption=Cipher.getInstance("AES/GCM/NoPadding");
//		GCMParameterSpec spec=new GCMParameterSpec(T_LEN,iv);
//		decryption.init(Cipher.DECRYPT_MODE,key,spec);
//		byte[] decryptedBytes=decryption.doFinal(textInBytes);
//		return new String(decryptedBytes);
	    System.arraycopy(combined, 0, iv, 0, 12);
	    System.arraycopy(combined, 12, ciphertext, 0, ciphertext.length);
	    Cipher decryption = Cipher.getInstance("AES/GCM/NoPadding");
	    GCMParameterSpec spec = new GCMParameterSpec(T_LEN, iv); // use extracted IV
	    decryption.init(Cipher.DECRYPT_MODE, key, spec);

	    byte[] decryptedBytes = decryption.doFinal(ciphertext);
	    return new String(decryptedBytes);
	}
	public static String decrypt(String encrypted,String Stringkey) throws Exception
	{
//		byte[] secret = Base64.getDecoder().decode(key);
//		Cipher decryption = Cipher.getInstance("AES/GCM/NoPadding");
//		byte[] X=decryption.getIV();
		int T_LEN=128;
//		byte[] textInBytes = decode(encrypted);
//		SecretKey screto = new SecretKeySpec(secret, "AES");
//		decryption=Cipher.getInstance("AES/GCM/NoPadding");
//		GCMParameterSpec spec=new GCMParameterSpec(T_LEN,X);
//		decryption.init(Cipher.DECRYPT_MODE,screto,spec);
//		byte[] decryptedBytes=decryption.doFinal(textInBytes);
//		return new String(decryptedBytes);
//		
		byte[] combined = decode(encrypted);

		 // Extract IV from the first 12 bytes
		byte[] secret = Base64.getDecoder().decode(Stringkey);
		SecretKey secreto = new SecretKeySpec(secret, "AES");
		byte[] iv = new byte[12];
	    byte[] ciphertext = new byte[combined.length - 12];
	    System.arraycopy(combined, 0, iv, 0, 12);
	    System.arraycopy(combined, 12, ciphertext, 0, ciphertext.length);
	    Cipher decryption = Cipher.getInstance("AES/GCM/NoPadding");
	    GCMParameterSpec spec = new GCMParameterSpec(T_LEN, iv);
	    decryption.init(Cipher.DECRYPT_MODE, secreto, spec);
	    byte[] decryptedBytes = decryption.doFinal(ciphertext);
	    return new String(decryptedBytes);
		
	}
	
	public static String encrypt(String text,String Stringkey) throws Exception
	{
		byte[] secret = Base64.getDecoder().decode(Stringkey);
		SecretKey secreto = new SecretKeySpec(secret, "AES");
		byte[] textInBytes = text.getBytes();
		Cipher encryption=Cipher.getInstance("AES/GCM/NoPadding");
		encryption.init(Cipher.ENCRYPT_MODE,secreto);
		byte [] iv = encryption.getIV();
		byte [] encrypted=encryption.doFinal(textInBytes);	
//		return encode(encrypted);
		byte[] combined = new byte[iv.length + encrypted.length];
	    System.arraycopy(iv, 0, combined, 0, iv.length);
	    System.arraycopy(encrypted, 0, combined, iv.length, encrypted.length);
 
	    return encode(combined);
	}
	
	public static void main(String [] args)
	{
		try 
		{
			
			Encryption encryption = new Encryption();
            SecretKey key = encryption.init();
            byte[] clef = key.getEncoded();
    		String secret = Base64.getEncoder().encodeToString(clef);

			
			String text1="Hello World";

			String encrypted1=encryption.encrypt(text1);
			String message1=encryption.decrypt(encrypted1);
		
			System.out.println("Secret key :"+secret);
			System.out.println(encrypted1);
			System.out.println(message1);			
		}
		
		
		catch(Exception ignored) 
		{
			System.out.println(ignored.getMessage());
		}
		
		
		
		
	}
	
}
