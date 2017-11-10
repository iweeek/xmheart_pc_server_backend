package com.xmheart.service;

import java.util.List;

import com.xmheart.model.XPWArticle;
import com.xmheart.model.XPWElecNewspaper;


/**
 * The Interface NewsService.
 * 用于处理新闻相关
 */
public interface NewsService {

	/**
	 * Gets the pinned media news.
	 *
	 * @return the pinned media news
	 */
	List<XPWArticle> getPinnedMediaNews();

	/**
	 * Gets the no pinned media news.
	 *
	 * @return the no pinned media news
	 */
	List<XPWArticle> getNoPinnedMediaNews();

	/**
	 * Gets the pinned hospital news.
	 *
	 * @return the pinned hospital news
	 */
	List<XPWArticle> getPinnedHospitalNews();

	/**
	 * Gets the no pinned hospital news.
	 *
	 * @return the no pinned hospital news
	 */
	List<XPWArticle> getNoPinnedHospitalNews();

	/**
	 * Gets the news by title.
	 *
	 * @param title the title
	 * @return the news by title
	 */
	XPWArticle getNewsByTitle(String title);

	/**
	 * Gets the news by id.
	 *
	 * @param id the id
	 * @return the news by id
	 */
	XPWArticle getNewsById(Long id);

	/**
	 * Gets the elec news paper.
	 *
	 * @param year the year
	 * @param time the time
	 * @return the elec news paper
	 */
	List<XPWElecNewspaper> getElecNewsPaper(String year, String times);

	/**
	 * Gets the new paper years.
	 *
	 * @return the new paper years
	 */
	List<String> getNewsPaperYears();

	/**
	 * Gets the news paper times.
	 *
	 * @param year the year
	 * @return the news paper times
	 */
	List<String> getNewsPaperTimes(String year);

	/**
	 * Gets the news.
	 *
	 * @return the news
	 */
	List<XPWArticle> getNews();

	/**
	 * Update news.
	 *
	 * @param news the news
	 * @return the int
	 */
	int updateNews(XPWArticle news);

    XPWArticle getColPrevNewsById(Long colId, Long articleId);

    XPWArticle getColNextNewsById(Long colId, Long id);

}
