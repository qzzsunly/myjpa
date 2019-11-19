/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or http://www.gnu.org/licenses/lgpl-2.1.html
 */
package cn.sexycode.myjpa.metamodel.model.domain.spi;

import javax.persistence.metamodel.ListAttribute;
import java.util.List;

/**
 * Hibernate extension to the JPA {@link ListAttribute} descriptor
 *
 * @author Steve Ebersole
 */
public interface ListPersistentAttribute<D, E> extends ListAttribute<D, E>, PluralPersistentAttribute<D, List<E>, E> {
}
