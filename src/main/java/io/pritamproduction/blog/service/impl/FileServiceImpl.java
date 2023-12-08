package io.pritamproduction.blog.service.impl;

import io.pritamproduction.blog.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {


		String randomId= UUID.randomUUID().toString();

		String fileName=randomId.concat(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.')));

		String fullPath=path+File.separator+fileName;

		File file1=new File(path);
		if (!file1.exists()){
			file1.mkdir();
		}
		Files.copy(file.getInputStream(), Paths.get(fullPath));

		return fileName;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		String fullPath=path+File.separator+fileName;
		InputStream is=new FileInputStream(fullPath);
		return is;
	}
}
