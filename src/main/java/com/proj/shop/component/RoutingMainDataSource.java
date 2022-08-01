package com.proj.shop.component;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class RoutingMainDataSource extends AbstractRoutingDataSource {
    private final MasterSlaveDataSourceKeySelector masterSlaveDataSourceKeySelector;
    public RoutingMainDataSource(Map<Object, Object> dataSourceMap, MasterSlaveDataSourceKeySelector masterSlaveDataSourceKeySelector) {
        super.setTargetDataSources(dataSourceMap);
        this.masterSlaveDataSourceKeySelector = masterSlaveDataSourceKeySelector;
        this.afterPropertiesSet();
    }
    @Override
    protected Object determineCurrentLookupKey() {
        Object key;
        if(TransactionSynchronizationManager.isCurrentTransactionReadOnly()) {
            log.info("read-only transaction.");
            key = masterSlaveDataSourceKeySelector.getSlaveKey();
        } else {
            log.info("read-write transaction.");
            key = masterSlaveDataSourceKeySelector.getMasterKey();
        }
        log.info("selected datasource key={}", key);
        return key;
    }
}
