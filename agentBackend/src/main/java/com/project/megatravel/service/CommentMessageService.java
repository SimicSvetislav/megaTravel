package com.project.megatravel.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.megatravel.model.chat.Poruka;
import com.project.megatravel.model.reservations.Komentar;

@Service
public class CommentMessageService {
	
	public Poruka viewMessage(Long id) {
		
		return new Poruka();
	}
	
	public Poruka answerMessage(Poruka poruka) {
		
		return new Poruka();
	}
	
	public List<Poruka> getMessageAsSender(){
		
		return new ArrayList<Poruka>();
	}
	
	public List<Poruka> getMessageAsReceiver() {
		
		return new ArrayList<Poruka>();
	}
	
	public List<Komentar> getCommentsByUnit(){
		
		return new ArrayList<Komentar>();
	}
	
	public List<Komentar> getCommentsByObject() {
		
		return new ArrayList<Komentar>();
	}

}
