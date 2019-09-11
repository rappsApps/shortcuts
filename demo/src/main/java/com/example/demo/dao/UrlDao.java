package com.example.demo.dao;

import com.example.demo.Url;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface UrlDao extends CrudRepository<Url, Integer> {
    public List<Url> findAll();
    public Url findByUid(Integer uid);
}
