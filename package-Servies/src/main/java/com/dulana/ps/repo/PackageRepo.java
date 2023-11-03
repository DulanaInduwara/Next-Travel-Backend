package com.dulana.ps.repo;

import com.dulana.ps.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepo extends JpaRepository<Package,String> {
}
