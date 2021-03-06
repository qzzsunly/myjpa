package cn.sexycode.myjpa.boot;

import javax.persistence.SharedCacheMode;
import javax.persistence.ValidationMode;
import javax.persistence.spi.PersistenceUnitInfo;
import javax.persistence.spi.PersistenceUnitTransactionType;
import java.net.URL;
import java.util.List;
import java.util.Properties;

/**
 * @author Steve Ebersole
 */
public class PersistenceUnitInfoDescriptor implements PersistenceUnitDescriptor {
    private final PersistenceUnitInfo persistenceUnitInfo;

    public PersistenceUnitInfoDescriptor(PersistenceUnitInfo persistenceUnitInfo) {
        this.persistenceUnitInfo = persistenceUnitInfo;
    }

    @Override
    public URL getPersistenceUnitRootUrl() {
        return persistenceUnitInfo.getPersistenceUnitRootUrl();
    }

    @Override
    public String getName() {
        return persistenceUnitInfo.getPersistenceUnitName();
    }

    @Override
    public Object getNonJtaDataSource() {
        return persistenceUnitInfo.getNonJtaDataSource();
    }

    @Override
    public Object getJtaDataSource() {
        return persistenceUnitInfo.getJtaDataSource();
    }

    @Override
    public String getProviderClassName() {
        return persistenceUnitInfo.getPersistenceProviderClassName();
    }

    @Override
    public PersistenceUnitTransactionType getTransactionType() {
        return persistenceUnitInfo.getTransactionType();
    }

    @Override
    public boolean isUseQuotedIdentifiers() {
        return false;
    }

    @Override
    public Properties getProperties() {
        return persistenceUnitInfo.getProperties();
    }

    @Override
    public ClassLoader getClassLoader() {
        return persistenceUnitInfo.getClassLoader();
    }

    @Override
    public ClassLoader getTempClassLoader() {
        return persistenceUnitInfo.getNewTempClassLoader();
    }

    @Override
    public boolean isExcludeUnlistedClasses() {
        return persistenceUnitInfo.excludeUnlistedClasses();
    }

    @Override
    public ValidationMode getValidationMode() {
        return persistenceUnitInfo.getValidationMode();
    }

    @Override
    public SharedCacheMode getSharedCacheMode() {
        return persistenceUnitInfo.getSharedCacheMode();
    }

    @Override
    public List<String> getManagedClassNames() {
        return persistenceUnitInfo.getManagedClassNames();
    }

    @Override
    public List<String> getMappingFileNames() {
        return persistenceUnitInfo.getMappingFileNames();
    }

    @Override
    public List<URL> getJarFileUrls() {
        return persistenceUnitInfo.getJarFileUrls();
    }

}
