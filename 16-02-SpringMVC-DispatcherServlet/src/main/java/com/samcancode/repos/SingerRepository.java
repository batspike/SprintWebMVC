package com.samcancode.repos;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.samcancode.entities.Singer;

public interface SingerRepository extends PagingAndSortingRepository<Singer, Long>
{

}
