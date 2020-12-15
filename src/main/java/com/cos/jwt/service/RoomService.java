package com.cos.jwt.service;

import java.io.File;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cos.jwt.domain.room.Room;
import com.cos.jwt.domain.room.RoomReposiroty;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RoomService {
	
	private final RoomReposiroty roomrepositoy;
	
	
	@Transactional
	public void insert(HttpServletRequest request , String roomname , String password, MultipartFile img) {
		try {
			
			String uploadFolder = getFolder();
			String uploadFolderPath = request.getServletContext().getRealPath("/blog-app/public/");
			
//			System.out.println(request.getServletPath());
//			System.out.println(uploadFolder);
//			System.out.println(uploadFolderPath);
			
			File realUploadFolderPath = new File(uploadFolderPath, uploadFolder);
			System.out.println(realUploadFolderPath);
			if(!realUploadFolderPath.exists()) {
				realUploadFolderPath.mkdirs();
			}
			
			String filename = "";
			
			UUID uuid = UUID.randomUUID(); 
			String room_img = img.getOriginalFilename();
			String uploadFilename = uuid.toString() + "_" + room_img; 
			String realPath = realUploadFolderPath + "\\" + uploadFilename;
			System.out.println("asdfasdfasdf : " + realPath);
			filename = "C:\\image\\" + uploadFilename;
			img.transferTo(new File(filename));	
			//filename = "C:\\image" + uploadFolder.replace("\\", "/") + "/" + uploadFilename;
			System.out.println(filename);
			String filename2 = "http://localhost:8000/images/" + uploadFilename;
			Room room = Room.builder().roomname(roomname).password(password).img(filename2).build();
			
			roomrepositoy.save(room);
			
		}catch(Exception e) {
			e.getMessage();
		}
		
		
	}
	
	private String getFolder() {
	    SimpleDateFormat uploadDate = new SimpleDateFormat("yyyy-MM-dd");
	    Date date = new Date();
	    String str = uploadDate.format(date);
	    
	    return str.replace("-", File.separator);
	}

	@Transactional
	public Page<Room> list(Pageable pageable){
		
		return roomrepositoy.findAll(pageable);
		
	}
}
