package kr.ac.snu.dao;

import java.util.List;

import kr.ac.snu.vo.DiseaseGeneVO;
import kr.ac.snu.vo.PaperSummaryVO;
import kr.ac.snu.vo.RepositioningDrugVO;
import kr.ac.snu.vo.ResultVO;
import kr.ac.snu.vo.SideEffectVO;

public interface ResultDAO {
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
	public List<RepositioningDrugVO> getApprovedReferenceWithGene(String gene);
	public List<RepositioningDrugVO> getApprovedCandidateWithGene(String gene);
	public List<RepositioningDrugVO> getInterruptedCandidateWithGene(String gene);
	
	public List<String> getAutoSearchByDisease(String disease);
	public List<String> getAutoSearchByGene(String gene);
	public List<String> getDRAutoSearchByDisease(String disease);
	public List<String> getDRAutoSearchByGene(String gene);
	public List<String> getDRAutoSearchByDrug(String drug);
	public List<SideEffectVO> getDrugSideEffect(String drugname);
	public List<String> getPMIDCount(String diseaseName, String targetGene);
	public List<String> getPMIDList(String genesymbol, String diseasename);
	public List<PaperSummaryVO> getPaperSummary(String pmid);
}

