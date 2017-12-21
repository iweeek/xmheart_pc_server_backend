package com.xmheart.service;

import java.util.List;

import com.xmheart.model.XPWColumn;
import com.xmheart.model.XPWColumnEnglish;
import com.xmheart.model.XPWNav;

// TODO: Auto-generated Javadoc
/**
 * The Interface ColumnService.
 */
public interface ColumnEnglishService {

    XPWColumnEnglish getParentColumnById(long id);

    /**
	 * Gets the first columns.
	 *
	 * @return the first columns
	 */
	List<XPWColumnEnglish> getTopFirstColumns();

	/**
	 * Gets the child columns by id.
	 *
	 * @param id the id
	 * @return the child columns by id
	 */
	List<XPWColumnEnglish> getChildColumnsById(long id);

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
    List<XPWColumnEnglish> getColumns();

    /**
     * Gets the columns by parent id.
     *
     * @param parentColumnId the parent column id
     * @return the columns by parent id
     */
    List<XPWColumnEnglish> getColumnsByParentId(Long parentColumnId);

    /**
     * Gets the column by id.
     *
     * @param columnId the column id
     * @return the column by id
     */
    XPWColumnEnglish getColumnById(Long columnId);

    List<XPWColumnEnglish> readSubColumn(Long id);
    
	List<XPWNav> getNavsByChildColumnName(String childColumnName);
	
	List<XPWNav> getNavsByChildColumnId(Long id);

    XPWNav getNavById(Long id);

    List<XPWNav> getNavList();

    List<XPWNav> getNavsByChildColumnIdOrderByPublishTime(long id);

    int updateNavByExample(XPWNav nav);

    int updateColumnEnglish(XPWColumnEnglish column);

}
