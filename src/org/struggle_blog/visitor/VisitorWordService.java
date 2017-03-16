package org.struggle_blog.visitor;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.struggle_blog.entity.Comment;
import org.struggle_blog.entity.LeaveWord;

@Transactional
public class VisitorWordService {
	private VisitorWordDao dao;

	public void setDao(VisitorWordDao dao) {
		this.dao = dao;
	}
	/**
	 * 留言
	 * @param lw
	 * @return
	 */
	public int doWord(LeaveWord lw){
		return dao.doWord(lw);
	}
	/**
	 * 获取所有的留言
	 * @param firstResult
	 * @param maxResult
	 * @return
	 */
	public List<LeaveWord> getAllWord(int firstResult,int maxResult) {
		return dao.getAllWord(firstResult, maxResult);
	}
	
	public int getAllRowCount() {
		return dao.getAllRowCount();
	}
	
	/**
	 * 评论提交
	 * @param com
	 * @return
	 */
	public int doComments(Comment com) {
		return dao.doComments(com);
	}
}
