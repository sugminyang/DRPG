package kr.ac.snu.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.ac.snu.vo.DiseaseGeneVO;
import kr.ac.snu.vo.RepositioningDrugVO;
import kr.ac.snu.vo.ResultVO;
import kr.ac.snu.vo.SideEffectVO;

@Repository
public class ResultDAOImpl implements ResultDAO{


	@Inject
	private SqlSession sqlSession;

	private static final String Namespace = "kr.ac.mapper.resultMapper";

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

	
	/* dr search with disease.  */
	
	@Override
	public List<RepositioningDrugVO> getApprovedReferenceWithDisease(String disease) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("disease",disease);
		
		return sqlSession.selectList(Namespace+".getApprovedReferenceWithDisease",map);
	}

	@Override
	public List<RepositioningDrugVO> getApprovedCandidateWithDisease(String disease) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("disease",disease);
		
		return sqlSession.selectList(Namespace+".getApprovedCandidateWithDisease",map);
	}

	@Override
	public List<RepositioningDrugVO> getInterruptedCandidateWithDisease(String disease) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("disease",disease);
		
		return sqlSession.selectList(Namespace+".getInterruptedCandidateWithDisease",map);
	}

	
	/* dr search with drug.  */
	
	@Override
	public List<RepositioningDrugVO> getApprovedReferenceWithDrug(String drug) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("drug",drug);
		
		return sqlSession.selectList(Namespace+".getApprovedReferenceWithDrug",map);
	}

	@Override
	public List<RepositioningDrugVO> getApprovedCandidateWithDrug(String drug) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("drug",drug);
		
		return sqlSession.selectList(Namespace+".getApprovedCandidateWithDrug",map);
	}

	@Override
	public List<RepositioningDrugVO> getInterruptedCandidateWithDrug(String drug) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("drug",drug);
		
		return sqlSession.selectList(Namespace+".getInterruptedCandidateWithDrug",map);
	}

	@Override
	public List<RepositioningDrugVO> getApprovedReferenceWithGene(String gene) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("gene",gene);
		
		return sqlSession.selectList(Namespace+".getApprovedReferenceWithGene",map);
	}

	@Override
	public List<RepositioningDrugVO> getApprovedCandidateWithGene(String gene) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("gene",gene);
		
		return sqlSession.selectList(Namespace+".getApprovedCandidateWithGene",map);
	}

	@Override
	public List<RepositioningDrugVO> getInterruptedCandidateWithGene(String gene) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("gene",gene);
		
		return sqlSession.selectList(Namespace+".getInterruptedCandidateWithGene",map);
	}

	@Override
	public List<String> getAutoSearchByDisease(String disease) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("disease",disease);
		
		return sqlSession.selectList(Namespace+".getAutoSearchByDisease",map);
	}

	@Override
	public List<String> getAutoSearchByGene(String gene) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("gene",gene);
		
		return sqlSession.selectList(Namespace+".getAutoSearchByGene",map);
	}

	@Override
	public List<String> getDRAutoSearchByDisease(String disease) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("disease",disease);
		
		return sqlSession.selectList(Namespace+".getDRAutoSearchByDisease",map);
	}

	@Override
	public List<String> getDRAutoSearchByGene(String gene) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("gene",gene);
		
		return sqlSession.selectList(Namespace+".getDRAutoSearchByGene",map);
	}

	@Override
	public List<String> getDRAutoSearchByDrug(String drug) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("drug",drug);
		
		return sqlSession.selectList(Namespace+".getDRAutoSearchByDrug",map);
	}

	@Override
	public List<SideEffectVO> getDrugSideEffect(String drugname) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("drugname",drugname);

		return sqlSession.selectList(Namespace+".getDrugSideEffect",map);
	}

	@Override
	public List<String> getPMIDCount(String diseaseName, String targetGene) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("gene",targetGene);
		map.put("disease",diseaseName);

		return sqlSession.selectList(Namespace+".getPMIDCount",map);
	}
	
	
	
}


