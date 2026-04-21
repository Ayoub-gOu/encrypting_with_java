package com.raina.appmain;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import cryptage.Encryption;
@Controller

public class TemplateController {
	@GetMapping("/template")
	public String template()
	{
		return "input";
	}
	
	@GetMapping("/variables")
	public String variables(ModelMap model)
	{
		model.put("variable", "hi darling !!!!!!");
		return "variable-template";
	}
	
//	@PostMapping(value="/chiffrer")
//	public String chiffrer(@RequestParam String text1,
//							@RequestParam String text2,
//							ModelMap model)
//	{
//		try 
//		{
//			
//			Encryption encryption = new Encryption();
//			encryption.init();
//
//			String encrypted1=encryption.encrypt(text1);
//			String message1=encryption.decrypt(encrypted1);
//			
//			System.out.println(encrypted1);
//			System.out.println(message1);
//			
//			String encrypted2=encryption.encrypt(text2);
//			String message2=encryption.decrypt(encrypted2);
//			
//			System.out.println(encrypted2);
//			System.out.println(message2);
//			
//			model.put("variable1",text1+":"+encrypted1);
//			model.put("variable2", text2+":"+encrypted2);
//			return "variable-template";
//		}catch(Exception ignored)
//		{
//			return "input";
//		}
//		
//		
//	}
}
