/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later.
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package cn.sexycode.sql.type.descriptor.sql;


import cn.sexycode.sql.type.descriptor.JdbcTypeNameMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Types;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Steve Ebersole
 */
public class LobTypeMappings {
    private static final Logger log = LoggerFactory.getLogger(LobTypeMappings.class);

    /**
     * Singleton access
     */
    public static final LobTypeMappings INSTANCE = new LobTypeMappings();

    private final Map<Integer, Integer> lobCodeByNonLobCode;

    private LobTypeMappings() {
        this.lobCodeByNonLobCode = new ConcurrentHashMap<>();

        // BLOB mappings
        this.lobCodeByNonLobCode.put(Types.BLOB, Types.BLOB);
        this.lobCodeByNonLobCode.put(Types.BINARY, Types.BLOB);
        this.lobCodeByNonLobCode.put(Types.VARBINARY, Types.BLOB);
        this.lobCodeByNonLobCode.put(Types.LONGVARBINARY, Types.BLOB);

        // CLOB mappings
        this.lobCodeByNonLobCode.put(Types.CLOB, Types.CLOB);
        this.lobCodeByNonLobCode.put(Types.CHAR, Types.CLOB);
        this.lobCodeByNonLobCode.put(Types.VARCHAR, Types.CLOB);
        this.lobCodeByNonLobCode.put(Types.LONGVARCHAR, Types.CLOB);

        // NCLOB mappings
        this.lobCodeByNonLobCode.put(Types.NCLOB, Types.NCLOB);
        this.lobCodeByNonLobCode.put(Types.NCHAR, Types.NCLOB);
        this.lobCodeByNonLobCode.put(Types.NVARCHAR, Types.NCLOB);
        this.lobCodeByNonLobCode.put(Types.LONGNVARCHAR, Types.NCLOB);
    }

    public boolean hasCorrespondingLobCode(int jdbcTypeCode) {
        return lobCodeByNonLobCode.containsKey(jdbcTypeCode);
    }

    public int getCorrespondingLobCode(int jdbcTypeCode) {
        Integer lobTypeCode = lobCodeByNonLobCode.get(jdbcTypeCode);
        if (lobTypeCode == null) {
            throw new IllegalArgumentException(
                    String.format(
                            Locale.ROOT,
                            "JDBC type-code [%s (%s)] not known to have a corresponding LOB equivalent",
                            jdbcTypeCode,
                            JdbcTypeNameMapper.getTypeName(jdbcTypeCode)
                    )
            );
        }
        return lobTypeCode;
    }
}
