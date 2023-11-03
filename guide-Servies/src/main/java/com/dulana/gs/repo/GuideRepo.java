package com.dulana.gs.repo;

import com.dulana.gs.entity.Guide;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuideRepo extends JpaRepository<Guide,String> {
}
