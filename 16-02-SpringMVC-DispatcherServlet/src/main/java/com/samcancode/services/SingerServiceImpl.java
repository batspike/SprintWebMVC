package com.samcancode.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.samcancode.entities.Singer;
import com.samcancode.repos.SingerRepository;

@Transactional
@Service("singerService")
public class SingerServiceImpl implements SingerService
{
	private SingerRepository singerRepository;
	
	@Autowired
	public void setSingerRepository(SingerRepository singerRepository) {
		this.singerRepository = singerRepository;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Singer> findAll() {
		return Lists.newArrayList(singerRepository.findAll());
	}

	@Override
	@Transactional(readOnly=true)
	public Singer findById(Long id) {
		return singerRepository.findById(id).get();
	}

	@Override
	public Singer save(Singer singer) {
		return singerRepository.save(singer);
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Singer> findAllByPage(Pageable pageable) {
		return singerRepository.findAll(pageable);
	}

}
