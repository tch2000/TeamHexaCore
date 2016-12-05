package edu.hexa.leejaehoon.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.hexa.leejaehoon.domain.BoardVO;

@Repository
public class BoardDAOImple implements BoardDAO {
	
	private static final String NAMESPCAE = "edu.hexa.leejaehoon.board";
	private static final Logger logger = LoggerFactory.getLogger(BoardDAOImple.class);
	@Autowired
	SqlSession sqlsession;
	
	@Override
	public List<BoardVO> select() {
		
		List<BoardVO> list = sqlsession.selectList(NAMESPCAE + ".selectAllTip");
		
		logger.info("dao list = " + list.size());
		return list;
	}

}