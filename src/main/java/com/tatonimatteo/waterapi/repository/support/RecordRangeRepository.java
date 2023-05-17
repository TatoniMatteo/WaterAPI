package com.tatonimatteo.waterapi.repository.support;

import com.tatonimatteo.waterapi.entity.support.RecordRange;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional("supportTransactionManager")
public interface RecordRangeRepository extends JpaRepository<RecordRange, Long> {
    @Override
    @NotNull
    List<RecordRange> findAll();
}
