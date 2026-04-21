package hashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {

	public static String generate_SHA256(String input) throws NoSuchAlgorithmException
	{
		MessageDigest md=MessageDigest.getInstance("SHA-256");
		byte[] MessageDigest=md.digest(input.getBytes());
		StringBuilder hexString=new StringBuilder();
		for (byte b:MessageDigest)
		{
			
			String hex=Integer.toHexString(0xFF & b);
			if (hex.length() == 1) 
			{
				hexString.append('0');	
			}
			hexString.append(hex);
			
		}
		return hexString.toString();
	}
	public static void main(String args[])
	{
		String word1="Hello World";
		String word2="Hello World";
		try {
		System.out.println(SHA256.generate_SHA256(word1));
		System.out.println(SHA256.generate_SHA256(word2));
		}
		catch (Exception e)
		{
			e.getMessage();
		}
	}
}
