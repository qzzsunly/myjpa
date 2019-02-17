/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later.
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package cn.sexycode.sql.sql;


import cn.sexycode.sql.dialect.Dialect;

import java.util.HashSet;
import java.util.Iterator;

/**
 * A translated HQL query
 *
 * @author Gavin King
 */
public class QuerySelect {
    private Dialect dialect;
    private JoinFragment joins;
    private StringBuilder select = new StringBuilder();
    private StringBuilder where = new StringBuilder();
    private StringBuilder groupBy = new StringBuilder();
    private StringBuilder orderBy = new StringBuilder();
    private StringBuilder having = new StringBuilder();
    private String comment;
    private boolean distinct;

    private static final HashSet<String> DONT_SPACE_TOKENS = new HashSet<String>();

    static {
        //dontSpace.add("'");
        DONT_SPACE_TOKENS.add(".");
        DONT_SPACE_TOKENS.add("+");
        DONT_SPACE_TOKENS.add("-");
        DONT_SPACE_TOKENS.add("/");
        DONT_SPACE_TOKENS.add("*");
        DONT_SPACE_TOKENS.add("<");
        DONT_SPACE_TOKENS.add(">");
        DONT_SPACE_TOKENS.add("=");
        DONT_SPACE_TOKENS.add("#");
        DONT_SPACE_TOKENS.add("~");
        DONT_SPACE_TOKENS.add("|");
        DONT_SPACE_TOKENS.add("&");
        DONT_SPACE_TOKENS.add("<=");
        DONT_SPACE_TOKENS.add(">=");
        DONT_SPACE_TOKENS.add("=>");
        DONT_SPACE_TOKENS.add("=<");
        DONT_SPACE_TOKENS.add("!=");
        DONT_SPACE_TOKENS.add("<>");
        DONT_SPACE_TOKENS.add("!#");
        DONT_SPACE_TOKENS.add("!~");
        DONT_SPACE_TOKENS.add("!<");
        DONT_SPACE_TOKENS.add("!>");
        DONT_SPACE_TOKENS.add("("); //for MySQL
        DONT_SPACE_TOKENS.add(")");
    }

    public QuerySelect(Dialect dialect) {
        this.dialect = dialect;
        joins = new QueryJoinFragment(dialect, false);
    }

    public JoinFragment getJoinFragment() {
        return joins;
    }

    public void addSelectFragmentString(String fragment) {
        if (fragment.length() > 0 && fragment.charAt(0) == ',') {
            fragment = fragment.substring(1);
        }
        fragment = fragment.trim();
        if (fragment.length() > 0) {
            if (select.length() > 0) {
                select.append(", ");
            }
            select.append(fragment);
        }
    }

    public void addSelectColumn(String columnName, String alias) {
        addSelectFragmentString(columnName + ' ' + alias);
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public void setWhereTokens(Iterator tokens) {
        //if ( conjunctiveWhere.length()>0 ) conjunctiveWhere.append(" and ");
        appendTokens(where, tokens);
    }

    public void prependWhereConditions(String conditions) {
        if (where.length() > 0) {
            where.insert(0, conditions + " and ");
        } else {
            where.append(conditions);
        }
    }

    public void setGroupByTokens(Iterator tokens) {
        //if ( groupBy.length()>0 ) groupBy.append(" and ");
        appendTokens(groupBy, tokens);
    }

    public void setOrderByTokens(Iterator tokens) {
        //if ( orderBy.length()>0 ) orderBy.append(" and ");
        appendTokens(orderBy, tokens);
    }

    public void setHavingTokens(Iterator tokens) {
        //if ( having.length()>0 ) having.append(" and ");
        appendTokens(having, tokens);
    }

    public void addOrderBy(String orderByString) {
        if (orderBy.length() > 0) {
            orderBy.append(", ");
        }
        orderBy.append(orderByString);
    }

    public String toQueryString() {
        StringBuilder buf = new StringBuilder(50);
        if (comment != null) {
            buf.append("/* ").append(comment).append(" */ ");
        }
        buf.append("select ");
        if (distinct) {
            buf.append("distinct ");
        }
        String from = joins.toFromFragmentString();
        if (from.startsWith(",")) {
            from = from.substring(1);
        } else if (from.startsWith(" inner join")) {
            from = from.substring(11);
        }

        buf.append(select.toString())
                .append(" from")
                .append(from);

        String outerJoinsAfterWhere = joins.toWhereFragmentString().trim();
        String whereConditions = where.toString().trim();
        boolean hasOuterJoinsAfterWhere = outerJoinsAfterWhere.length() > 0;
        boolean hasWhereConditions = whereConditions.length() > 0;
        if (hasOuterJoinsAfterWhere || hasWhereConditions) {
            buf.append(" where ");
            if (hasOuterJoinsAfterWhere) {
                buf.append(outerJoinsAfterWhere.substring(4));
            }
            if (hasWhereConditions) {
                if (hasOuterJoinsAfterWhere) {
                    buf.append(" and (");
                }
                buf.append(whereConditions);
                if (hasOuterJoinsAfterWhere) {
                    buf.append(")");
                }
            }
        }

        if (groupBy.length() > 0) {
            buf.append(" group by ").append(groupBy.toString());
        }
        if (having.length() > 0) {
            buf.append(" having ").append(having.toString());
        }
        if (orderBy.length() > 0) {
            buf.append(" order by ").append(orderBy.toString());
        }

        return dialect.transformSelectString(buf.toString());
    }

    private static void appendTokens(StringBuilder buf, Iterator iter) {
        boolean lastSpaceable = true;
        boolean lastQuoted = false;
        while (iter.hasNext()) {
            String token = (String) iter.next();
            boolean spaceable = !DONT_SPACE_TOKENS.contains(token);
            boolean quoted = token.startsWith("'");
            if (spaceable && lastSpaceable) {
                if (!quoted || !lastQuoted) {
                    buf.append(' ');
                }
            }
            lastSpaceable = spaceable;
            buf.append(token);
            lastQuoted = token.endsWith("'");
        }
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public QuerySelect copy() {
        QuerySelect copy = new QuerySelect(dialect);
        copy.joins = this.joins.copy();
        copy.select.append(this.select.toString());
        copy.where.append(this.where.toString());
        copy.groupBy.append(this.groupBy.toString());
        copy.orderBy.append(this.orderBy.toString());
        copy.having.append(this.having.toString());
        copy.comment = this.comment;
        copy.distinct = this.distinct;
        return copy;
    }

}
