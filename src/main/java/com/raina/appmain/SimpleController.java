package com.raina.appmain;

import org.springframework.web.bind.annotation.RestController;

import cryptage.Encryption;
import hashing.SHA256;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController

public class SimpleController {
	
	@GetMapping("/")
	public String home()
	{
		return "hello";
	}
	
	@PostMapping(value="/encrypt")
	public String encrypt(@RequestParam String text1,
							@RequestParam String text2,
							ModelMap model)
	{
		try 
		{
			return Encryption.encrypt(text1, text2);
		}catch(Exception ignored)
		{
			return "input";
		}
	}
	@PostMapping(value="/decrypt")
	public String decrypt(@RequestParam String text1,
							@RequestParam String text2,
							ModelMap model)
	{
		try 
		{
			
						return Encryption.decrypt(text1, text2);
		}catch(Exception ignored)
		{
			return "input";
		}
	}
	
		@PostMapping(value="/SHA256")
		public String SHA256(@RequestParam String text1,
								@RequestParam String text2,
								ModelMap model)
		{
			try 
			{
				
				return SHA256.generate_SHA256(text1)+"\n"+SHA256.generate_SHA256(text2);
				
			}catch(Exception ignored)
			{
				return "input";
			}
}}

