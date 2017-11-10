package com.xmheart.service;

import java.util.List;

import com.xmheart.model.XPWColumn;
import com.xmheart.model.XPWNav;

// TODO: Auto-generated Javadoc
/**
 * The Interface ColumnService.
 */
public interface ColumnService {

	XPWColumn getParentColumnById(long id);

    /**
	 * Gets the first columns.
	 *
	 * @return the first columns
	 */
	List<XPWColumn> getTopFirstColumns();

	/**
	 * Gets the child columns by id.
	 *
	 * @param id the id
	 * @return the child columns by id
	 */
	List<XPWColumn> getChildColumnsById(long id);

	/**
	 * Gets the navs by column id.
	 *
	 * @param id the id
	 * @return the navs by column id
	 */
	List<XPWNav> getNavsByColumnId(long id);

	/**
	 * Gets the nav list by second column name.
	 *
	 * @param name the name
	 * @return the nav list by second column name
	 */
	List<XPWNav> getNavListBySecondColumnName(String name);

	/**
	 * Creates the nav.
	 *
	 * @param nav the nav
	 * @return the int
	 */
	int createNav(XPWNav nav);

	/**
	 * Update nav.
	 *
	 * @param nav the nav
	 * @return the int
	 */
	int updateNav(XPWNav nav);
	
    /**
     * Gets the columns.
     *
     * @return the columns
     */
    List<XPWColumn> getColumns();

    /**
     * Gets the columns by parent id.
     *
     * @param parentColumnId the parent column id
     * @return the columns by parent id
     */
    List<XPWColumn> getColumnsByParentId(Long parentColumnId);

    /**
     * Gets the column by id.
     *
     * @param columnId the column id
     * @return the column by id
     */
    XPWColumn getColumnById(Long columnId);

    int updateColumn(XPWColumn column);

    List<XPWColumn> readSubColumn(Long id);
    
	List<XPWNav> getNavsByChildColumnName(String childColumnName);

    XPWNav getNavById(Long id);

    List<XPWNav> getNavList();

    List<XPWNav> getNavsByChildColumnIdOrderByPublishTime(long id);

    int updateNavByExample(XPWNav nav);

}
