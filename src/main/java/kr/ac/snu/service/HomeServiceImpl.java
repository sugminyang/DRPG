package kr.ac.snu.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.http.client.utils.CloneUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import kr.ac.snu.dao.ResultDAO;
import kr.ac.snu.vo.DiseaseGeneVO;
import kr.ac.snu.vo.DrugRepoVO;
import kr.ac.snu.vo.RepositioningDrugVO;
import kr.ac.snu.vo.ResultVO;

@Service
public class HomeServiceImpl implements HomeService{
	
	@Inject
    private ResultDAO dao;
    
    @Override
    public List<ResultVO> selectResult() throws Exception {
        return dao.selectResult();
    }

	@Override
	//SELECT * FROM test.`disease-gene_pairs` where disease like '%glioma%';
	public List<ResultVO> getResultByDisease(String disease) {
		List<ResultVO> temp = new ArrayList<ResultVO>();
		for(ResultVO vo : dao.getResultByDisease(disease)) {
			if(vo.getGene().length() > 1)	{
				if(vo.getDisease().equalsIgnoreCase("tumor") || vo.getDisease().equalsIgnoreCase("tumors") 
						|| vo.getDisease().equalsIgnoreCase("carcinoma") || vo.getDisease().equalsIgnoreCase("carcinomas")
						|| vo.getDisease().equalsIgnoreCase("tumour") || vo.getDisease().equalsIgnoreCase("cancer")
						|| vo.getDisease().equalsIgnoreCase("cancers") || vo.getDisease().equalsIgnoreCase("metastasis")
						|| vo.getDisease().equalsIgnoreCase("adenocarcinoma") || vo.getDisease().equalsIgnoreCase("tumours")
						|| vo.getDisease().equalsIgnoreCase("adenocarcinomas") || vo.getDisease().equalsIgnoreCase("metastases")
						|| vo.getDisease().equalsIgnoreCase("overall survival") || vo.getDisease().equalsIgnoreCase("os")
						|| vo.getDisease().equalsIgnoreCase("death") || vo.getDisease().equalsIgnoreCase("malignancies"))	{
				}
				else	{
					temp.add(vo);
				}
				
			}
		}
		
		return temp;
	}

	@Override
	public List<ResultVO> getResultByGene(String gene) {
		List<ResultVO> temp = new ArrayList<ResultVO>();
		for(ResultVO vo : dao.getResultByGene(gene)) {
			if(vo.getDisease().length() > 1)	{
				if(vo.getDisease().equalsIgnoreCase("tumor") || vo.getDisease().equalsIgnoreCase("tumors") 
						|| vo.getDisease().equalsIgnoreCase("carcinoma") || vo.getDisease().equalsIgnoreCase("carcinomas")
						|| vo.getDisease().equalsIgnoreCase("tumour") || vo.getDisease().equalsIgnoreCase("cancer")
						|| vo.getDisease().equalsIgnoreCase("cancers") || vo.getDisease().equalsIgnoreCase("metastasis")
						|| vo.getDisease().equalsIgnoreCase("adenocarcinoma") || vo.getDisease().equalsIgnoreCase("tumours")
						|| vo.getDisease().equalsIgnoreCase("adenocarcinomas") || vo.getDisease().equalsIgnoreCase("metastases")
						|| vo.getDisease().equalsIgnoreCase("overall survival") || vo.getDisease().equalsIgnoreCase("os")
						|| vo.getDisease().equalsIgnoreCase("death") || vo.getDisease().equalsIgnoreCase("malignancies"))	{
						
				}
				else	{
					temp.add(vo);
				}
			}
		}
		
		return temp;
	}

	@Override
	public List<ResultVO> getResultByPmid(String pmid) {
		List<ResultVO> temp = new ArrayList<ResultVO>();
		for(ResultVO vo : dao.getResultByPmid(pmid)) {
			System.out.println(vo);
			
			if(vo.getDisease().length() > 1 && vo.getGene().length() > 1)	{
				if(vo.getDisease().equalsIgnoreCase("tumor") || vo.getDisease().equalsIgnoreCase("tumors") 
						|| vo.getDisease().equalsIgnoreCase("carcinoma") || vo.getDisease().equalsIgnoreCase("carcinomas")
						|| vo.getDisease().equalsIgnoreCase("tumour") || vo.getDisease().equalsIgnoreCase("cancer")
						|| vo.getDisease().equalsIgnoreCase("cancers") || vo.getDisease().equalsIgnoreCase("metastasis")
						|| vo.getDisease().equalsIgnoreCase("adenocarcinoma") || vo.getDisease().equalsIgnoreCase("tumours")
						|| vo.getDisease().equalsIgnoreCase("adenocarcinomas") || vo.getDisease().equalsIgnoreCase("metastases")
						|| vo.getDisease().equalsIgnoreCase("overall survival") || vo.getDisease().equalsIgnoreCase("os")
						|| vo.getDisease().equalsIgnoreCase("death") || vo.getDisease().equalsIgnoreCase("malignancies"))	{
						
				}
				else	{
					temp.add(vo);
				}
			}
		}
		
		return temp;
	}

	public Map<String,String>  getPubmedData(String request_url) {
		Map<String,String> paper = new HashMap<String,String>();
		URL url = null;
		BufferedReader br = null;
		String jsonStr = "";
		
		try {
			url = new URL(request_url);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
	        con.setRequestMethod("GET");
	        
	        int responseCode = con.getResponseCode();
	        
	        if(responseCode==200) { // 정상 호출
	            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	        } else {  // 에러 발생
	            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	        }
	        
	        String inputLine;
	        StringBuffer response = new StringBuffer();
	        while ((inputLine = br.readLine()) != null) {
	            response.append(inputLine);
	        }
	        br.close();
	        
	        jsonStr = response.toString();
	        paper = getSummary(jsonStr);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return paper;
	}
	
	public List<String>  getPubTator(String request_url) {
		List<String> paper = new ArrayList<String>();
		URL url = null;
		BufferedReader br = null;
		String jsonStr = "";
		
		try {
			url = new URL(request_url);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
	        con.setRequestMethod("GET");
	        
	        int responseCode = con.getResponseCode();
	        
	        if(responseCode==200) { // 정상 호출
	            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	        } else {  // 에러 발생
	            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	        }
	        
	        String inputLine;
	        StringBuffer response = new StringBuffer();
	        while ((inputLine = br.readLine()) != null) {
	            response.append(inputLine);
	        }
	        br.close();
	        
	        jsonStr = response.toString();
	        paper = getPubTatorJsonParser(jsonStr);
	        
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return paper;
	}
	
	public List<String> getPubTatorJsonParser(String paperJson) throws ParseException	{
		List<String> colored = new ArrayList<String>();
		JSONParser parser = new JSONParser();
		Object obj = parser.parse( paperJson );
		JSONArray jsonArray = (JSONArray) obj;
	
		
		for (int i = 0; i < jsonArray.size();i++) {
		    JSONObject data = (JSONObject)jsonArray.get(i);
		    String text = (String)data.get("text"); 
		    JSONArray pubtator = (JSONArray)data.get("denotations");
		    
		    for (int j = 0; j < pubtator.size();j++) {
		    	JSONObject entity = (JSONObject)pubtator.get(j);
		    	
		    	JSONObject span = (JSONObject)entity.get("span");
		    	Long begin = (Long)span.get("begin");
		    	Long end = (Long)span.get("end");
		    	String keyword = text.substring(Integer.parseInt(begin+""), Integer.parseInt(end+""));

//		    	String mesh_obj = (String)entity.get("obj");
		    	
		    	if(!colored.contains(keyword))	{
		    		colored.add(keyword);
		    	}
		    }
		}
		
		return colored;
	}
	
	public Map<String,String> getSummary(String paperJson) throws ParseException	{
		Map<String,String> paper = new HashMap<String,String>();
		JSONParser parser = new JSONParser();
		Object obj = parser.parse( paperJson );
		JSONObject jsonObj = (JSONObject) obj;
		
		JSONObject resultList = (JSONObject)jsonObj.get("resultList");
		JSONArray result = (JSONArray)resultList.get("result");
		
		for (int i = 0; i < result.size();i++) {
		    JSONObject data = (JSONObject)result.get(i);
		    String title = (String)data.get("title");
		    String abs = (String)data.get("abstractText");
		    //journalInfo.journal.title; journalInfo.dateOfPublication ; journalInfo.volume(journalInfo.issue) ; pageInfo
		    JSONObject journalInfo = (JSONObject)data.get("journalInfo");
		    JSONObject jornal = (JSONObject)journalInfo.get("journal");
		    String publication = (String)jornal.get("title") + "; "
		    					+ (String)journalInfo.get("dateOfPublication") + "; "
		    					+ (String)journalInfo.get("volume") + "(" + (String)journalInfo.get("issue") + "); "
		    					+ (String)journalInfo.get("pageInfo");
		    publication = publication.replace("null", "");
		    publication = publication.replace("();", "");
		    
		    paper.put("title",title);
		    paper.put("abstract",abs);
		    paper.put("publication",publication);
		}
		
		return paper;
	}

	@Override
	public List<DrugRepoVO> getResultByDiseaseForDR(String disease) {
		List<DrugRepoVO> temp = new ArrayList<DrugRepoVO>();
		for(DrugRepoVO vo : dao.getResultByDiseaseForDR(disease)) {
			if(vo.getGene().length() > 1)	{
				if(vo.getDisease().equalsIgnoreCase("tumor") || vo.getDisease().equalsIgnoreCase("tumors") 
						|| vo.getDisease().equalsIgnoreCase("carcinoma") || vo.getDisease().equalsIgnoreCase("carcinomas")
						|| vo.getDisease().equalsIgnoreCase("tumour") || vo.getDisease().equalsIgnoreCase("cancer")
						|| vo.getDisease().equalsIgnoreCase("cancers") || vo.getDisease().equalsIgnoreCase("metastasis")
						|| vo.getDisease().equalsIgnoreCase("adenocarcinoma") || vo.getDisease().equalsIgnoreCase("tumours")
						|| vo.getDisease().equalsIgnoreCase("adenocarcinomas") || vo.getDisease().equalsIgnoreCase("metastases")
						|| vo.getDisease().equalsIgnoreCase("overall survival") || vo.getDisease().equalsIgnoreCase("os")
						|| vo.getDisease().equalsIgnoreCase("death") || vo.getDisease().equalsIgnoreCase("malignancies"))	{
				}
				else	{
					temp.add(vo);
				}
				
			}
		}
		
		return temp;		
	}

	@Override
	public List<DrugRepoVO> getResultByGeneForDR(String gene) {
		List<DrugRepoVO> temp = new ArrayList<DrugRepoVO>();
		for(DrugRepoVO vo : dao.getResultByGeneForDR(gene)) {
			if(vo.getGene().length() > 1)	{
				if(vo.getDisease().equalsIgnoreCase("tumor") || vo.getDisease().equalsIgnoreCase("tumors") 
						|| vo.getDisease().equalsIgnoreCase("carcinoma") || vo.getDisease().equalsIgnoreCase("carcinomas")
						|| vo.getDisease().equalsIgnoreCase("tumour") || vo.getDisease().equalsIgnoreCase("cancer")
						|| vo.getDisease().equalsIgnoreCase("cancers") || vo.getDisease().equalsIgnoreCase("metastasis")
						|| vo.getDisease().equalsIgnoreCase("adenocarcinoma") || vo.getDisease().equalsIgnoreCase("tumours")
						|| vo.getDisease().equalsIgnoreCase("adenocarcinomas") || vo.getDisease().equalsIgnoreCase("metastases")
						|| vo.getDisease().equalsIgnoreCase("overall survival") || vo.getDisease().equalsIgnoreCase("os")
						|| vo.getDisease().equalsIgnoreCase("death") || vo.getDisease().equalsIgnoreCase("malignancies"))	{
				}
				else	{
					temp.add(vo);
				}
				
			}
		}
		
		return temp;
	}

	@Override
	public List<DrugRepoVO> getResultByDrugForDR(String drug) {
		List<DrugRepoVO> temp = new ArrayList<DrugRepoVO>();
		for(DrugRepoVO vo : dao.getResultByDrugForDR(drug)) {
			if(vo.getGene().length() > 1)	{
				if(vo.getDisease().equalsIgnoreCase("tumor") || vo.getDisease().equalsIgnoreCase("tumors") 
						|| vo.getDisease().equalsIgnoreCase("carcinoma") || vo.getDisease().equalsIgnoreCase("carcinomas")
						|| vo.getDisease().equalsIgnoreCase("tumour") || vo.getDisease().equalsIgnoreCase("cancer")
						|| vo.getDisease().equalsIgnoreCase("cancers") || vo.getDisease().equalsIgnoreCase("metastasis")
						|| vo.getDisease().equalsIgnoreCase("adenocarcinoma") || vo.getDisease().equalsIgnoreCase("tumours")
						|| vo.getDisease().equalsIgnoreCase("adenocarcinomas") || vo.getDisease().equalsIgnoreCase("metastases")
						|| vo.getDisease().equalsIgnoreCase("overall survival") || vo.getDisease().equalsIgnoreCase("os")
						|| vo.getDisease().equalsIgnoreCase("death") || vo.getDisease().equalsIgnoreCase("malignancies"))	{
				}
				else	{
					temp.add(vo);
				}
				
			}
		}
		
		return temp;
	}

	@Override
	public List<RepositioningDrugVO> getDrugsWithDiseaseName(String disease) {
		Map<String, RepositioningDrugVO> temp = new HashMap<String, RepositioningDrugVO>();
		List<RepositioningDrugVO> voList = new ArrayList<RepositioningDrugVO>();
		
		if(disease.equalsIgnoreCase("tumor") || disease.equalsIgnoreCase("tumors") 
				|| disease.equalsIgnoreCase("carcinoma") || disease.equalsIgnoreCase("carcinomas")
				|| disease.equalsIgnoreCase("tumour") || disease.equalsIgnoreCase("cancer")
				|| disease.equalsIgnoreCase("cancers") || disease.equalsIgnoreCase("metastasis")
				|| disease.equalsIgnoreCase("adenocarcinoma") || disease.equalsIgnoreCase("tumours")
				|| disease.equalsIgnoreCase("Adenoma") || disease.equalsIgnoreCase("Adenomas")
				|| disease.equalsIgnoreCase("adenocarcinomas") || disease.equalsIgnoreCase("metastases")
				|| disease.equalsIgnoreCase("overall survival") || disease.equalsIgnoreCase("os")
				|| disease.equalsIgnoreCase("death") || disease.equalsIgnoreCase("malignancies")
				|| disease.contains("[OBSOLETE]") || disease.equalsIgnoreCase("Sarcoma")
				|| disease.equalsIgnoreCase("Neoplasms") || disease.equalsIgnoreCase("Neoplasm")
				|| disease.equalsIgnoreCase("Neoplasm Metastasis"))	{
			return voList;
		}
		
		//mapping disease-gene-drugs. and remove duplicate sources and interactionType
		List<RepositioningDrugVO> vos = new ArrayList<RepositioningDrugVO>();

		for(RepositioningDrugVO vo : dao.getDrugsWithDiseaseName(disease)) {
			vos.add(vo);
		}

		for(RepositioningDrugVO vo : vos)	{
			String key =  vo.getTargetGene() + "@" +vo.getPhaseNum() + "@" + vo.getChemblID();

			removeDuplicateSourcesNinteractionType(temp,vo,key);
		}

		//				System.out.println("#size(temp.size()): " + temp.size());
		//				System.out.println("#size(diseaseList.size()): " + diseaseList.size());

		
		for(String key : temp.keySet())	{
//			System.out.println(disease);
			RepositioningDrugVO vo = temp.get(key);

			RepositioningDrugVO deepCopy = null;
			try {
				deepCopy = (RepositioningDrugVO)CloneUtils.clone(vo);
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			deepCopy.setDiseaseName(disease);

			voList.add(deepCopy);
		}
		
		return voList;	
	}


	@Override
	public List<RepositioningDrugVO> getDrugsWithGeneName(String gene) {
		Map<String, RepositioningDrugVO> temp = new HashMap<String, RepositioningDrugVO>();
		List<String> diseaseList = new ArrayList<String>();
		
		//get diseaseName
		for(String disease: dao.getDiseaseNameByGene(gene)) {
			if(disease.equalsIgnoreCase("tumor") || disease.equalsIgnoreCase("tumors") 
					|| disease.equalsIgnoreCase("carcinoma") || disease.equalsIgnoreCase("carcinomas")
					|| disease.equalsIgnoreCase("tumour") || disease.equalsIgnoreCase("cancer")
					|| disease.equalsIgnoreCase("cancers") || disease.equalsIgnoreCase("metastasis")
					|| disease.equalsIgnoreCase("adenocarcinoma") || disease.equalsIgnoreCase("tumours")
					|| disease.equalsIgnoreCase("Adenoma") || disease.equalsIgnoreCase("Adenomas")
					|| disease.equalsIgnoreCase("adenocarcinomas") || disease.equalsIgnoreCase("metastases")
					|| disease.equalsIgnoreCase("overall survival") || disease.equalsIgnoreCase("os")
					|| disease.equalsIgnoreCase("death") || disease.equalsIgnoreCase("malignancies")
					|| disease.contains("[OBSOLETE]") || disease.equalsIgnoreCase("Sarcoma")
					|| disease.equalsIgnoreCase("Neoplasms") || disease.equalsIgnoreCase("Neoplasm")
					|| disease.equalsIgnoreCase("Neoplasm Metastasis"))	{
			}
			else	{
//				System.out.println(disease);
				diseaseList.add(disease);
			}
		}
		
		//IF doesnot exist revealed disease related to target gene. write undiscovered.
		if(diseaseList.size() == 0) {
			diseaseList.add("Undiscovered");
		}
			
		
		
		//mapping disease-gene-drugs. and remove duplicate sources and interactionType
		List<RepositioningDrugVO> vos = new ArrayList<RepositioningDrugVO>();
		
		for(RepositioningDrugVO vo : dao.getDrugsWithGeneName(gene)) {
			vos.add(vo);
		}
		
		for(RepositioningDrugVO vo : vos)	{
			String key =  vo.getTargetGene() + "@" +vo.getPhaseNum() + "@" + vo.getChemblID();

			removeDuplicateSourcesNinteractionType(temp,vo,key);
		}
		
//		System.out.println("#size(temp.size()): " + temp.size());
//		System.out.println("#size(diseaseList.size()): " + diseaseList.size());
		
		List<RepositioningDrugVO> voList = new ArrayList<RepositioningDrugVO>();
		for(String key : temp.keySet())	{
			for(String disease : diseaseList)	{
//				System.out.println(disease);
				RepositioningDrugVO vo = temp.get(key);
				
				RepositioningDrugVO deepCopy = null;
				try {
					deepCopy = (RepositioningDrugVO)CloneUtils.clone(vo);
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
				deepCopy.setDiseaseName(disease);

				voList.add(deepCopy);
			}
		}
		
		return voList;
	}
	
	@Override
	public List<RepositioningDrugVO> getDrugUsage(String drug) {
		Map<String, RepositioningDrugVO> temp = new HashMap<String, RepositioningDrugVO>();
		List<DiseaseGeneVO> diseaseGeneList = new ArrayList<DiseaseGeneVO>();
		
		//get diseaseName
		for(DiseaseGeneVO vo: dao.getDiseaseNameByDrug(drug)) {
			if(vo.getDisease().equalsIgnoreCase("tumor") || vo.getDisease().equalsIgnoreCase("tumors") 
					|| vo.getDisease().equalsIgnoreCase("carcinoma") || vo.getDisease().equalsIgnoreCase("carcinomas")
					|| vo.getDisease().equalsIgnoreCase("tumour") || vo.getDisease().equalsIgnoreCase("cancer")
					|| vo.getDisease().equalsIgnoreCase("cancers") || vo.getDisease().equalsIgnoreCase("metastasis")
					|| vo.getDisease().equalsIgnoreCase("adenocarcinoma") || vo.getDisease().equalsIgnoreCase("tumours")
					|| vo.getDisease().equalsIgnoreCase("Adenoma") || vo.getDisease().equalsIgnoreCase("Adenomas")
					|| vo.getDisease().equalsIgnoreCase("adenocarcinomas") || vo.getDisease().equalsIgnoreCase("metastases")
					|| vo.getDisease().equalsIgnoreCase("overall survival") || vo.getDisease().equalsIgnoreCase("os")
					|| vo.getDisease().equalsIgnoreCase("death") || vo.getDisease().equalsIgnoreCase("malignancies")
					|| vo.getDisease().contains("[OBSOLETE]") || vo.getDisease().equalsIgnoreCase("Sarcoma")
					|| vo.getDisease().equalsIgnoreCase("Neoplasms") || vo.getDisease().equalsIgnoreCase("Neoplasm")
					|| vo.getDisease().equalsIgnoreCase("Neoplasm Metastasis"))	{

			}
			else	{
//				System.out.println(disease);
				diseaseGeneList.add(vo);
			}
			
		}

		//mapping disease-gene-drugs. and remove duplicate sources and interactionType
		List<RepositioningDrugVO> vos = new ArrayList<RepositioningDrugVO>();
		
		for(RepositioningDrugVO vo : dao.getDrugUsage(drug)) {
			vos.add(vo);
		}
		
		for(RepositioningDrugVO vo : vos)	{
			String key = vo.getTargetGene() ;

			removeDuplicateSourcesNinteractionType(temp,vo,key);
		}
		
 
		
		List<RepositioningDrugVO> voList = new ArrayList<RepositioningDrugVO>();
		for(String key : temp.keySet())	{
			for(DiseaseGeneVO geneForBetterPrognisis : diseaseGeneList)	{
//				System.out.println(disease);
				RepositioningDrugVO vo = temp.get(key);

				//using triplet existed gene only because it will be good for prognosis.				
				if(!geneForBetterPrognisis.getGene().equalsIgnoreCase(vo.getTargetGene()))	{
					continue;
				}
				
				
				RepositioningDrugVO deepCopy = null;
				try {
					deepCopy = (RepositioningDrugVO)CloneUtils.clone(vo);
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
				deepCopy.setDiseaseName(geneForBetterPrognisis.getDisease());

				voList.add(deepCopy);
			}
		}

		return voList;
	}	
	
	private void removeDuplicateSourcesNinteractionType(Map<String, RepositioningDrugVO> temp, RepositioningDrugVO vo, String key) {
		
			if(temp.containsKey(key))	{
				RepositioningDrugVO ori = temp.get(key);
				
				//merge. sources.
				if(vo.getSources().length() != 0)	{
					if(ori.getSources().length() == 0)	{
						ori.setSources(vo.getSources());
					}
					else	{
						if(!ori.getSources().contains(vo.getSources()))	{
							String newSources = ori.getSources() + ", " + vo.getSources();
							ori.setSources(newSources);							
						}
					}
				}
				
				//merge. interactionType.
				if(vo.getInteractionType().length() != 0)	{
					if(ori.getInteractionType().length() == 0)	{
						ori.setInteractionType(vo.getInteractionType());
					}
					else	{
						if(!ori.getInteractionType().contains(vo.getInteractionType()))	{		//no containing interaction type.
							String mergedInteractionType = ori.getInteractionType() + ", " + vo.getInteractionType();
							ori.setInteractionType(mergedInteractionType);
						}
					}
				}
				
				temp.put(key,ori);
			} 
			else	{
				temp.put(key,vo);
			}
	}

	
}
