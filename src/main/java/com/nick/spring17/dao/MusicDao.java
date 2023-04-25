package com.nick.spring17.dao;

import com.nick.spring17.entity.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicDao extends JpaRepository<Music,String> {


}
