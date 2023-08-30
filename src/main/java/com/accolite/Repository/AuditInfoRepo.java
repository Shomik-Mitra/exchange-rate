package com.accolite.Repository;

import com.accolite.Entity.AuditInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditInfoRepo extends JpaRepository<AuditInfo, Long>
{
}
