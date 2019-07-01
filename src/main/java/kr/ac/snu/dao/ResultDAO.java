package kr.ac.snu.dao;

import java.util.List;

import kr.ac.snu.vo.DrugRepoVO;
import kr.ac.snu.vo.RepositioningDrugVO;
import kr.ac.snu.vo.ResultVO;

public interface ResultDAO {
    public List<ResultVO> selectResult() throws Exception;
    public List<ResultVO> getResultByDisease(String disease);
    public List<ResultVO> getResultByGene(String gene);
    public List<ResultVO> getResultByPmid(String pmid);
	public List<DrugRepoVO> getResultByDiseaseForDR(String disease);
	public List<DrugRepoVO> getResultByGeneForDR(String gene);
	public List<DrugRepoVO> getResultByDrugForDR(String drug);
	public List<RepositioningDrugVO> getDrugsWithDiseaseName(String disease);
	public List<RepositioningDrugVO> getDrugsWithGeneName(String gene);
	public List<String> getDiseaseNameByGene(String gene);
	public List<RepositioningDrugVO> getDrugUsage(String drug);
	public List<String> getDiseaseNameByDrug(String drug);
}

