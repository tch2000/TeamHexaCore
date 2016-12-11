package edu.hexa.leejaehoon.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.hexa.leejaehoon.domain.ProjectInfoVO;

import edu.hexa.leejaehoon.service.ProjectInfoService;
import edu.hexa.teamsns.domain.ProjectVO;
import edu.hexa.teamsns.domain.RecruitProjectVO;
import edu.hexa.teamsns.domain.RequiredSkillVO;
import edu.penta.hyunsun.domain.RecruitDetailDTO;

@Controller
@RequestMapping(value="project")
public class ProjectInfoController {
	private static final Logger logger = LoggerFactory.getLogger(ProjectInfoController.class);
	
	@Autowired
	private ProjectInfoService projectInfoService;
	
	@RequestMapping(value="projectInfo")
	public void projectinfo(String uid,Model model, HttpServletRequest request){
		
		HttpSession session = request.getSession();
		 uid = (String)session.getAttribute("login_id");
		
		List<ProjectInfoVO> list = projectInfoService.select(uid);
		
		logger.info("프로젝트 리스트 :" + list);
		
		model.addAttribute("projectList",list);
	}
	
	@RequestMapping(value="/recruitProjectUpdate/{rbno}", method=RequestMethod.POST)
	public ResponseEntity<Integer> recruitProjectUpdate(@PathVariable("rbno") Integer rbno,@RequestBody RecruitProjectVO vo){
		
		ResponseEntity<Integer> entity = null;
		vo.setRbno(rbno);
		int result =projectInfoService.update(vo);
		logger.info("dvo 결과 ==" + vo);
		logger.info("result 값: " + result);
		if(result == 1){
			entity = new ResponseEntity<Integer>( 1, HttpStatus.OK);
		}
		
		return entity;
	}
	
	@RequestMapping(value="/ProjectUpdate/{rbno}",method=RequestMethod.POST)
	public ResponseEntity<Integer> projectUpdate(@PathVariable("rbno") Integer rbno,@RequestBody ProjectVO vo){
		logger.info("vo title: 결과 ==" + vo.getPname());
		logger.info("vo title: 결과 ==" + vo.getDistrict());
		logger.info("vo title: 결과 ==" + vo.getCategory());
		logger.info("vo title: 결과 ==" + vo.getStart());
		ResponseEntity<Integer> entity = null;
		RecruitProjectVO rvo = new RecruitProjectVO();
		rvo.setRbno(rbno);
		vo.setRbno(rbno);
		int result = projectInfoService.update2(vo);
		
		logger.info("result 값: " + result);
		if(result == 1){
			entity = new ResponseEntity<Integer>( 1, HttpStatus.OK);
		}
		return entity;
	}
	
	@RequestMapping(value="/skillUpdate/{rbno}",method=RequestMethod.POST)
	public ResponseEntity<Integer> skillUpdate(@PathVariable("rbno") Integer rbno,@RequestBody RequiredSkillVO vo){
		logger.info("vo title: 결과 ==" + vo.getSkill_1());
		logger.info("vo title: 결과 ==" + vo.getSkill_2());
		logger.info("vo title: 결과 ==" + vo.getSkill_3());
		logger.info("vo title: 결과 ==" + vo.getSkill_4());
		
		ResponseEntity<Integer> entity = null;
		
		RecruitProjectVO rvo = new RecruitProjectVO();
		rvo.setRbno(rbno);
		vo.setRbno(rbno);
		int result = projectInfoService.update3(vo);
		
		logger.info("result 값: " + result);
		if(result == 1){
			entity = new ResponseEntity<Integer>( 1, HttpStatus.OK);
		}
		return entity;
	}
	
	
	
	
} // end class
