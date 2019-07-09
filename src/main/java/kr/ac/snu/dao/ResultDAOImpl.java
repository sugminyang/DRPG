package kr.ac.snu.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.ac.snu.vo.DiseaseGeneVO;
import kr.ac.snu.vo.DrugRepoVO;
import kr.ac.snu.vo.RepositioningDrugVO;
import kr.ac.snu.vo.ResultVO;

@Repository
public class ResultDAOImpl implements ResultDAO{


	@Inject
	private SqlSession sqlSession;

	private static final String Namespace = "kr.ac.mapper.resultMapper";

	@Override
	public List<ResultVO> selectResult()throws Exception {
		return sqlSession.selectList(Namespace+".selectResult");
	}

	@Override
	public List<ResultVO> getResultByDisease(String disease) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("disease",disease);
		
		return sqlSession.selectList(Namespace+".getResultByDisease",map);
	}

	@Override
	public List<ResultVO> getResultByGene(String gene) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("gene",gene);
		
		return sqlSession.selectList(Namespace+".getResultByGene",map);
	}

	@Override
	public List<ResultVO> getResultByPmid(String pmid) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("pmid",pmid);
		
		return sqlSession.selectList(Namespace+".getResultByPmid",map);
	}

	@Override
	public List<DrugRepoVO> getResultByDiseaseForDR(String disease) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("disease",disease);
		
		return sqlSession.selectList(Namespace+".getResultByDiseaseForDR",map);
	}

	@Override
	public List<DrugRepoVO> getResultByGeneForDR(String gene) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("gene",gene);
		
		return sqlSession.selectList(Namespace+".getResultByGeneForDR",map);
	}

	@Override
	public List<DrugRepoVO> getResultByDrugForDR(String drug) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("drug",drug);
		
		return sqlSession.selectList(Namespace+".getResultByDrugForDR",map);
	}

	@Override
	public List<RepositioningDrugVO> getDrugsWithDiseaseName(String disease) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("disease",disease);
		
		return sqlSession.selectList(Namespace+".getDrugsWithDiseaseName",map);
	}

	@Override
	public List<RepositioningDrugVO> getDrugsWithGeneName(String gene) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("gene",gene);
		
		return sqlSession.selectList(Namespace+".getDrugsWithGeneName",map);
	}

	@Override
	public List<String> getDiseaseNameByGene(String gene) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("gene",gene);
		
		return sqlSession.selectList(Namespace+".getDiseaseNameByGene",map);
	}

	@Override
	public List<RepositioningDrugVO> getDrugUsage(String drug) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("drug",drug);
		
		return sqlSession.selectList(Namespace+".getDrugUsage",map);
	}

	@Override
	public List<DiseaseGeneVO> getDiseaseNameByDrug(String drug) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("drug",drug);
		
		return sqlSession.selectList(Namespace+".getDiseaseNameByDrug",map);
	}
	
}


