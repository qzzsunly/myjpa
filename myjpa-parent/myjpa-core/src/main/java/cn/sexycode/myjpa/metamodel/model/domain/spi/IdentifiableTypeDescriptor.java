package cn.sexycode.myjpa.metamodel.model.domain.spi;

import cn.sexycode.myjpa.metamodel.model.domain.IdentifiableDomainType;

import javax.persistence.metamodel.IdentifiableType;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Hibernate extension to the JPA {@link IdentifiableType} descriptor
 *
 * @author Steve Ebersole
 */
public interface IdentifiableTypeDescriptor<J> extends IdentifiableDomainType<J>, ManagedTypeDescriptor<J> {
    boolean hasIdClass();

    SingularPersistentAttribute<? super J, ?> locateIdAttribute();

    void collectIdClassAttributes(Set<SingularPersistentAttribute<? super J, ?>> attributes);

    void visitIdClassAttributes(Consumer<SingularPersistentAttribute<? super J, ?>> attributeConsumer);

    interface InFlightAccess<X> extends ManagedTypeDescriptor.InFlightAccess<X> {
        void applyIdAttribute(SingularPersistentAttribute<X, ?> idAttribute);

        void applyIdClassAttributes(Set<SingularPersistentAttribute<? super X, ?>> idClassAttributes);

        void applyVersionAttribute(SingularPersistentAttribute<X, ?> versionAttribute);
    }

    @Override
    InFlightAccess<J> getInFlightAccess();

    @Override
    SimpleTypeDescriptor<?> getIdType();

    @Override
    <Y> SingularPersistentAttribute<J, Y> getDeclaredId(Class<Y> type);

    @Override
    <Y> SingularPersistentAttribute<? super J, Y> getId(Class<Y> type);

    SingularPersistentAttribute<? super J, ?> locateVersionAttribute();

    @Override
    <Y> SingularPersistentAttribute<? super J, Y> getVersion(Class<Y> type);

    @Override
    <Y> SingularPersistentAttribute<J, Y> getDeclaredVersion(Class<Y> type);

    @Override
    IdentifiableTypeDescriptor<? super J> getSuperType();

    @Override
    default IdentifiableTypeDescriptor<? super J> getSupertype() {
        return getSuperType();
    }
}