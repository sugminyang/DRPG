package kr.ac.snu.service;

import java.util.List;
import java.util.Map;

import kr.ac.snu.vo.RepositioningDrugVO;
import kr.ac.snu.vo.ResultVO;

public interface HomeService {

					/* literature search with keyword and it's categories */
	public List<ResultVO> getResultByDisease(String disease);
	public List<ResultVO> getResultByGene(String gene);
	public List<ResultVO> getResultByPmid(String pmid);
	
					/* paper vizualization method */
	public Map<String,String> getPubmedData(String request_url);
	public List<String> getPubTator(String request_url);

					/* drug repositioning search with Disease */
	public List<RepositioningDrugVO> getAllItemsWithDisease(String disease);
	public List<RepositioningDrugVO> getApprovedReferenceWithDisease(String disease);
	public List<RepositioningDrugVO> getApprovedCandidateWithDisease(String disease);
	public List<RepositioningDrugVO> getInterruptedCandidateWithDisease(String disease);
	
					/* drug repositioning search with Drug */
	public List<RepositioningDrugVO> getAllItemsWithDrug(String drug);
	public List<RepositioningDrugVO> getApprovedReferenceWithDrug(String drug);
	public List<RepositioningDrugVO> getApprovedCandidateWithDrug(String drug);
	public List<RepositioningDrugVO> getInterruptedCandidateWithDrug(String drug);
				
					/* drug repositioning search with Gene */
	public List<RepositioningDrugVO> getAllItemsWithGene(String gene);
	public List<RepositioningDrugVO> getApprovedReferenceWithGene(String gene);
	public List<RepositioningDrugVO> getApprovedCandidateWithGene(String gene);
	public List<RepositioningDrugVO> getInterruptedCandidateWithGene(String gene);
	
	
	
	public List<String> getAutoSearchByDisease(String disease);
	public List<String> getAutoSearchByGene(String gene);
	public List<String> getDRAutoSearchByDisease(String disease);
	public List<String> getDRAutoSearchByGene(String gene);
	public List<String> getDRAutoSearchByDrug(String drug);
	
	
	
	
}
