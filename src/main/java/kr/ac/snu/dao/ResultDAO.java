package kr.ac.snu.dao;

import java.util.List;

import kr.ac.snu.vo.DiseaseGeneVO;
import kr.ac.snu.vo.RepositioningDrugVO;
import kr.ac.snu.vo.ResultVO;

public interface ResultDAO {
    public List<ResultVO> selectResult() throws Exception;
    public List<ResultVO> getResultByDisease(String disease);
    public List<ResultVO> getResultByGene(String gene);
    public List<ResultVO> getResultByPmid(String pmid);
	public List<RepositioningDrugVO> getDrugsWithDiseaseName(String disease);
	public List<RepositioningDrugVO> getDrugsWithGeneName(String gene);
	public List<String> getDiseaseNameByGene(String gene);
	public List<RepositioningDrugVO> getDrugUsage(String drug);
	public List<DiseaseGeneVO> getDiseaseNameByDrug(String drug);
	public List<RepositioningDrugVO> getApprovedReferenceWithDisease(String disease);
	public List<RepositioningDrugVO> getApprovedCandidateWithDisease(String disease);
	public List<RepositioningDrugVO> getInterruptedCandidateWithDisease(String disease);
	public List<RepositioningDrugVO> getApprovedReferenceWithDrug(String drug);
	public List<RepositioningDrugVO> getApprovedCandidateWithDrug(String drug);
	public List<RepositioningDrugVO> getInterruptedCandidateWithDrug(String drug);
}

