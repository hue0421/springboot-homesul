package com.cos.jwt.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cos.jwt.domain.room.Room;
import com.cos.jwt.domain.room.RoomReposiroty;
import com.cos.jwt.service.RoomService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class RoomController {
	
	private final HttpSession session;
	private final RoomReposiroty roomPository;
	private final RoomService roomservice;
	
	
	@PostMapping("/room/insert")
	public ResponseEntity<?> insert(HttpServletRequest request ,@RequestParam(value="roomname" ,required=false) String roomname, @RequestParam(value="password" ,required=false) String password, @RequestParam(value="img" ,required=false)MultipartFile img){
		System.out.println(roomname +":" + password + ":" + img.getOriginalFilename());
		roomservice.insert(request ,roomname,password, img);
		
		return new ResponseEntity<String>("ok", HttpStatus.CREATED);
		
	}
	
	@GetMapping("/roomlist")
	public Page<Room> roomList(@PageableDefault(size=20, sort ="roomNo", direction=Direction.DESC) Pageable pageable){
		
		return roomservice.list(pageable);
	}
	

}
