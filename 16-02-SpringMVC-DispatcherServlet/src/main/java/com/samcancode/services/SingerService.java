package com.samcancode.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.samcancode.entities.Singer;

public interface SingerService
{
	List<Singer> findAll();
	Singer findById(Long id);
	Singer save(Singer singer);
	Page<Singer> findAllByPage(Pageable pageable);
}
