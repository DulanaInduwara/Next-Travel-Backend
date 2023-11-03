package com.dulana.pds.repo;

import com.dulana.gs.entity.Guide;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageDetailRepo extends JpaRepository<Guide,String> {
}
