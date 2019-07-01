package kr.ac.snu.service;

import java.util.List;
import java.util.Map;

import kr.ac.snu.vo.DrugRepoVO;
import kr.ac.snu.vo.RepositioningDrugVO;
import kr.ac.snu.vo.ResultVO;

public interface HomeService {
	public List<ResultVO> selectResult() throws Exception;
	public List<ResultVO> getResultByDisease(String disease);
	public List<ResultVO> getResultByGene(String gene);
	public List<ResultVO> getResultByPmid(String pmid);
	public Map<String,String> getPubmedData(String request_url);
	public List<String> getPubTator(String request_url);
	public List<DrugRepoVO> getResultByDiseaseForDR(String disease);
	public List<DrugRepoVO> getResultByGeneForDR(String gene);
	public List<DrugRepoVO> getResultByDrugForDR(String drug);
	public List<RepositioningDrugVO> getDrugsWithDiseaseName(String disease);
	public List<RepositioningDrugVO> getDrugsWithGeneName(String gene);
	public List<RepositioningDrugVO> getDrugUsage(String drug);
	

}
