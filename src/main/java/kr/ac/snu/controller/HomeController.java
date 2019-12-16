package kr.ac.snu.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.snu.service.HomeService;
import kr.ac.snu.vo.RepositioningDrugVO;
import kr.ac.snu.vo.ResultVO;
import kr.ac.snu.vo.SideEffectVO;
import net.sf.json.JSONArray;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Inject
	private HomeService service;


	/**
	 * main url / about page.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "mainpage";//"home"
	}

	@RequestMapping(value = "/drugprog1", method = RequestMethod.POST)
	public String drugprog1(HttpServletRequest request, Model model){ 
		String drugName = request.getParameter("drugname[]");
		logger.info("DrugName: " +drugName);
		
		JSONArray jsonArray = null;
		List<RepositioningDrugVO> resultList = null;
		
		resultList = service.getAllItemsWithDrug(drugName);
		
		jsonArray = JSONArray.fromObject(resultList);
		logger.info("data: " + jsonArray);
		
		model.addAttribute("data", jsonArray);
		model.addAttribute("drugname", drugName);
		model.addAttribute("mode", "drugprog1");
		return "drugprogresult";
	}
	
	@RequestMapping(value = "/drugprog2", method = RequestMethod.POST)
	public String drugprog2(HttpServletRequest request, Model model){ 
		String drugName = request.getParameter("drugname[]");
		String diseaseName = request.getParameter("diseasename[]");
		
		logger.info("DrugName: " +drugName);
		logger.info("DiseaseName: " +diseaseName);

		//둘 중에 하나만 입력되거나 둘다 입력되지 않을 경우 처리.
		
		JSONArray jsonArray = null;
		List<RepositioningDrugVO> resultList = null;
		
		resultList = service.getAllItemsWithDrugNDisease(drugName,diseaseName);
		
		jsonArray = JSONArray.fromObject(resultList);
		logger.info("data: " + jsonArray);
		
		model.addAttribute("data", jsonArray);
		model.addAttribute("drugname", drugName);
		model.addAttribute("diseasename", diseaseName);
		model.addAttribute("mode", "drugprog2");
		
		return "drugprogresult";
	}
	
	
	@RequestMapping(value = "/rowinfo", method = RequestMethod.GET)
	public void rowinfo(@RequestParam("genesymbol") String genesymbol, @RequestParam("diseasename") String diseasename, HttpServletResponse response) {
//		List<RepositioningDrugVO> resultList = null;
		JSONArray jsonArray = null;
		System.out.println("geneSymbol: " + genesymbol + "\tdiseasename: " + diseasename);
		List<String> resultList = null;
		resultList = service.getPMIDList(genesymbol,diseasename);
		jsonArray = JSONArray.fromObject(resultList);
		logger.info("[litsearch] search by disease : result - " + jsonArray);
		
		try {
			if(jsonArray == null)	return ;
			
			response.getWriter().print(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * wordcloud figure section(hide 1st milstone)
	 * */
	@RequestMapping(value = "/wordcloud", method = RequestMethod.GET)
	public String wordcloud() {
		logger.info("wordcloud");

		return "wordcloud";
	}


	
	
	
											/* searching literatures */
	/**
	 * 
	 * literature search url
	 * */
	@RequestMapping(value = "/litsearch", method = RequestMethod.GET)
	public String litsearch() {
		return "litSearch";
	}
	
	/**
	 * search with keyword and it's type on database(triplet table).
	 * */
	@RequestMapping(value = "/dbsearch", method = RequestMethod.GET)
	public void dbsearch(@RequestParam("query") String query, HttpServletResponse response) {
		String[] items = query.split("_@");

		List<ResultVO> resultList = null;
		JSONArray jsonArray = null;
		if(items[0].contentEquals("disease"))	{
			resultList = service.getResultByDisease(items[1]);
			jsonArray = JSONArray.fromObject(resultList);
			logger.info("[litsearch] search by disease : result - " + jsonArray);
		}
		else if(items[0].contentEquals("gene"))	{
			resultList = service.getResultByGene(items[1]);
			jsonArray = JSONArray.fromObject(resultList);
			logger.info("[litsearch] search by gene : result - " + jsonArray);
		}
		else if(items[0].contentEquals("pmid"))	{
			resultList = service.getResultByPmid(items[1]);
			jsonArray = JSONArray.fromObject(resultList);
			logger.info("[litsearch] search by pmid : result - " + jsonArray);
		}
		else	{
			logger.error("[Error in literature] - invalid format query  !!!");
		}

		try {
			if(jsonArray == null)	return ;
			
			response.getWriter().print(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * paper visualization url.
	 * quite slow it will be revised to get faster.
	 * */
	@RequestMapping(value = "/drugprog1/paperviz", method = RequestMethod.GET)
	public String paperviz1(@RequestParam("pmid") String pmid, Model model) throws ParseException {
		logger.info("[paperviz]-pmid: " + pmid);

		String request_url = "https://www.ebi.ac.uk/europepmc/webservices/rest/search?query=" + pmid + "&resultType=core&pageSize=1&format=json";
		String[] bioconcept = {"Gene","Disease","Mutation"}; 
		String format = "JSON";
		Map<String, List<String>> colorEntity = new HashMap<String, List<String>>();
		
		for(String concept: bioconcept)	{
			String url_Submit = "https://www.ncbi.nlm.nih.gov/CBBresearch/Lu/Demo/RESTful/tmTool.cgi/" + concept + "/" + pmid + "/" + format + "/";
			logger.debug("request url: "+url_Submit);
			
			List<String> temp = service.getPubTator(url_Submit);
			logger.debug(concept + "\t" + temp);
			colorEntity.put(concept, temp);
		}
		
		Map<String,String> paperSummary = service.getPubmedData(request_url);
		String temp_title = paperSummary.get("title");
		String temp_abs = paperSummary.get("abstract");
		String temp_pub = paperSummary.get("publication");
		
		String geneColor = "rgb(200,64,240)";
		String diseaseColor = "rgb(255,153,0)";
		String mutColor = "rgb(250,220,180)";
		
		for(String key: colorEntity.keySet())	{
			for(String keyword: colorEntity.get(key))	{
				logger.debug( key+"\t"+ keyword);
				
				if(key.equals("Gene"))	{
					temp_title = temp_title.replace(keyword, "<font style=\"background-color: " + geneColor + "\">"  + keyword + "</font>");
					temp_abs = temp_abs.replace(keyword, "<font style=\"background-color: " + geneColor + "\">"  + keyword + "</font>");
				}
				else if(key.equals("Disease"))	{
					temp_title = temp_title.replace(keyword, "<font style=\"background-color: " + diseaseColor + "\">"  + keyword + "</font>");
					temp_abs = temp_abs.replace(keyword, "<font style=\"background-color: " + diseaseColor + "\">"  + keyword + "</font>");
				}
				else if(key.equals("Mutation"))	{
					temp_title = temp_title.replace(keyword, "<font style=\"background-color: " + mutColor + "\">"  + keyword + "</font>");
					temp_abs = temp_abs.replace(keyword, "<font style=\"background-color: " + mutColor + "\">"  + keyword + "</font>");
				}
			}
		}
		
		logger.debug("paper title: "+temp_title);
		logger.debug("paper abs: "+temp_abs);
		logger.debug("paper pub: "+temp_pub);
				
		model.addAttribute("pmid",pmid);
		model.addAttribute("title",temp_title);
		model.addAttribute("coloredAbs",temp_abs);
		model.addAttribute("coloredTitle",temp_title);
		model.addAttribute("publication",temp_pub);
		
		return "paperViz";
	}

	/**
	 * paper visualization url.
	 * quite slow it will be revised to get faster.
	 * */
	@RequestMapping(value = "/drugprog2/paperviz", method = RequestMethod.GET)
	public String paperviz2(@RequestParam("pmid") String pmid, Model model) throws ParseException {
		logger.info("[paperviz]-pmid: " + pmid);

		String request_url = "https://www.ebi.ac.uk/europepmc/webservices/rest/search?query=" + pmid + "&resultType=core&pageSize=1&format=json";
		String[] bioconcept = {"Gene","Disease","Mutation"}; 
		String format = "JSON";
		Map<String, List<String>> colorEntity = new HashMap<String, List<String>>();
		
		for(String concept: bioconcept)	{
			String url_Submit = "https://www.ncbi.nlm.nih.gov/CBBresearch/Lu/Demo/RESTful/tmTool.cgi/" + concept + "/" + pmid + "/" + format + "/";
			logger.debug("request url: "+url_Submit);
			
			List<String> temp = service.getPubTator(url_Submit);
			logger.debug(concept + "\t" + temp);
			colorEntity.put(concept, temp);
		}
		
		Map<String,String> paperSummary = service.getPubmedData(request_url);
		String temp_title = paperSummary.get("title");
		String temp_abs = paperSummary.get("abstract");
		String temp_pub = paperSummary.get("publication");
		
		String geneColor = "rgb(200,64,240)";
		String diseaseColor = "rgb(255,153,0)";
		String mutColor = "rgb(250,220,180)";
		
		for(String key: colorEntity.keySet())	{
			for(String keyword: colorEntity.get(key))	{
				logger.debug( key+"\t"+ keyword);
				
				if(key.equals("Gene"))	{
					temp_title = temp_title.replace(keyword, "<font style=\"background-color: " + geneColor + "\">"  + keyword + "</font>");
					temp_abs = temp_abs.replace(keyword, "<font style=\"background-color: " + geneColor + "\">"  + keyword + "</font>");
				}
				else if(key.equals("Disease"))	{
					temp_title = temp_title.replace(keyword, "<font style=\"background-color: " + diseaseColor + "\">"  + keyword + "</font>");
					temp_abs = temp_abs.replace(keyword, "<font style=\"background-color: " + diseaseColor + "\">"  + keyword + "</font>");
				}
				else if(key.equals("Mutation"))	{
					temp_title = temp_title.replace(keyword, "<font style=\"background-color: " + mutColor + "\">"  + keyword + "</font>");
					temp_abs = temp_abs.replace(keyword, "<font style=\"background-color: " + mutColor + "\">"  + keyword + "</font>");
				}
			}
		}
		
		logger.debug("paper title: "+temp_title);
		logger.debug("paper abs: "+temp_abs);
		logger.debug("paper pub: "+temp_pub);
				
		model.addAttribute("pmid",pmid);
		model.addAttribute("title",temp_title);
		model.addAttribute("coloredAbs",temp_abs);
		model.addAttribute("coloredTitle",temp_title);
		model.addAttribute("publication",temp_pub);
		
		return "paperViz";
	}	
	
	
	
	
											/* drug repositioning */
	/**
	 * drug repositioning section
	 * */
	@RequestMapping(value = "/drugrepositor", method = RequestMethod.GET)
	public String drugrepositor() {
		return "drugrepositor";
	}
	
	/**
	 * drug prognosis search - disease
	 * */
	@RequestMapping(value = "/drugprogdisease", method = RequestMethod.GET)
	public void drugprogdisease(@RequestParam("drug_type") String drug_type, @RequestParam("query") String query, HttpServletResponse response) {
		logger.info("drugprogdisease >> " + query + ", " + drug_type);
		String[] items = query.split("_@");

		List<RepositioningDrugVO> resultList = null;
		JSONArray jsonArray = null;
		
		if(drug_type.contentEquals("All"))	{
			logger.info("[All] - " + items[1] + ", " + drug_type);

			resultList = service.getAllItemsWithDisease(items[1]);
			jsonArray = JSONArray.fromObject(resultList);
			logger.info("data: " + jsonArray);
		}
		else if(drug_type.contentEquals("FDA-approved control"))	{
			logger.info("[FDA-approved control] - " + items[1] + ", " + drug_type);

			resultList = service.getApprovedReferenceWithDisease(items[1]);
			jsonArray = JSONArray.fromObject(resultList);
			logger.info("data: " + jsonArray);
		}
		else if(drug_type.contentEquals("FDA-approved candidate"))	{ 
			logger.info("[FDA-approved candidate] - " + items[1] + ", " + drug_type);
			
			resultList = service.getApprovedCandidateWithDisease(items[1]);
			jsonArray = JSONArray.fromObject(resultList);
			logger.info("data: " + jsonArray);
		}
		else if(drug_type.contentEquals("Unapproved candidate"))	{
			logger.info("[Unapproved candidate] - " + items[1] + ", " + drug_type);
			
			resultList = service.getInterruptedCandidateWithDisease(items[1]);
			jsonArray = JSONArray.fromObject(resultList);
			logger.info("data: " + jsonArray);
		}
		else	{
			logger.error("[Error]invalid format query  !!!");
		}

		try {
			if(jsonArray == null)	return ;
			
			response.getWriter().print(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * drug prognosis search - gene
	 * */
	@RequestMapping(value = "/drugproggene", method = RequestMethod.GET)
	public void drugproggene(@RequestParam("drug_type") String drug_type, @RequestParam("query") String query, HttpServletResponse response) {
		logger.info("drugproggene >> " + query + ", " + drug_type);
		
		String[] items = query.split("_@");

		List<RepositioningDrugVO> resultList = null;
		JSONArray jsonArray = null;
		
		if(drug_type.contentEquals("All"))	{
			logger.info("[All] - " + items[1] + ", " + drug_type);

			resultList = service.getAllItemsWithGene(items[1]);
			jsonArray = JSONArray.fromObject(resultList);
			logger.info("data: " + jsonArray);
		}
		else if(drug_type.contentEquals("FDA-approved control"))	{
			logger.info("[FDA-approved control] - " + items[1] + ", " + drug_type);
			
			resultList = service.getApprovedReferenceWithGene(items[1]);
			jsonArray = JSONArray.fromObject(resultList);
			logger.info("data: " + jsonArray);
		}
		else if(drug_type.contentEquals("FDA-approved candidate"))	{ 
			logger.info("[FDA-approved candidate] - " + items[1] + ", " + drug_type);
			
			resultList = service.getApprovedCandidateWithGene(items[1]);
			jsonArray = JSONArray.fromObject(resultList);
			logger.info("data: " + jsonArray);
		}
		else if(drug_type.contentEquals("Unapproved candidate"))	{
			logger.info("[Unapproved candidate] - " + items[1] + ", " + drug_type);

			resultList = service.getInterruptedCandidateWithGene(items[1]);
			jsonArray = JSONArray.fromObject(resultList);
			logger.info("data: " + jsonArray);
		}
		else	{
			logger.error("[Error]invalid format query  !!!");
		}

		try {
			if(jsonArray == null)	return ;
			
			response.getWriter().print(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * drug prognosis search - chemical/drug
	 * @throws IOException 
	 * */
	@RequestMapping(value = "/drugprogchemical", method = RequestMethod.GET)
	public void drugprogchemical(@RequestParam("drug_type") String drug_type, @RequestParam("query") String query, HttpServletResponse response) throws IOException {
		logger.info("drugprogchemical >> " + query + ", " + drug_type);
		
		String[] items = query.split("_@");

		List<RepositioningDrugVO> resultList = null;
		JSONArray jsonArray = null;
		
		if(drug_type.contentEquals("All"))	{
			logger.info("[All] - " + items[1] + ", " + drug_type);

			resultList = service.getAllItemsWithDrug(items[1]);
//			BufferedWriter out = new BufferedWriter(new FileWriter("/Users/dean/Documents/2019/TextMining/repurposing_drug/formula1_Triplet/"+items[1]+".txt"));
//			out.append(items[1]+"\n");
//			out.append("DiseaseName\ttargetGene\tstatus\tevidenceScore"+"\n");
//			for(RepositioningDrugVO vo : resultList)	{
//				out.append(vo.writeCSV()+"\n");	
//			}
//			out.close();
			
			jsonArray = JSONArray.fromObject(resultList);
			logger.info("data: " + jsonArray);
		}
		else if(drug_type.contentEquals("FDA-approved control"))	{
			logger.info("[FDA-approved control] - " + items[1] + ", " + drug_type);
			
			resultList = service.getApprovedReferenceWithDrug(items[1]);
			jsonArray = JSONArray.fromObject(resultList);
			logger.info("data: " + jsonArray);
		}
		else if(drug_type.contentEquals("FDA-approved candidate"))	{ 
			logger.info("[FDA-approved candidate] - " + items[1] + ", " + drug_type);
			
			resultList = service.getApprovedCandidateWithDrug(items[1]);
			jsonArray = JSONArray.fromObject(resultList);
			logger.info("data: " + jsonArray);
		}
		else if(drug_type.contentEquals("Unapproved candidate"))	{
			logger.info("[Unapproved candidate] - " + items[1] + ", " + drug_type);

			resultList = service.getInterruptedCandidateWithDrug(items[1]);
			jsonArray = JSONArray.fromObject(resultList);
			logger.info("data: " + jsonArray);
		}
		else	{
			logger.error("[Error]invalid format query  !!!");
		}

		try {
			if(jsonArray == null)	return ;
			
			response.getWriter().print(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * provide dataset.
	 * */
	@RequestMapping(value = "/dataset", method = RequestMethod.GET)
	public String dataset() {
		logger.info("dataset");

		return "dataset";
	}
	
	@RequestMapping(value = "/instruction", method = RequestMethod.GET)
	public String instruction(Locale locale, Model model) {
		logger.info("instruction page");

		return "instruction";
	}

	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String contact() {
		logger.info("contact");

		return "contact";
	}
	
	
	@RequestMapping(value = "/autosearch", method = RequestMethod.GET)
	public void autosearch(@RequestParam("query") String query, HttpServletResponse response) {
		logger.info("autosearch");

		String[] items = query.split("_@");

		List<String> resultList = null;
		JSONArray jsonArray = null;
		if(items[0].contentEquals("disease"))	{
			resultList = service.getAutoSearchByDisease(items[1]);
			jsonArray = JSONArray.fromObject(resultList);
			System.out.println(jsonArray);
		}
		else if(items[0].contentEquals("gene"))	{
			resultList = service.getAutoSearchByGene(items[1]);
			jsonArray = JSONArray.fromObject(resultList);
		}

		try {
			if(jsonArray == null)	return ;
			
			response.getWriter().print(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value = "/autosearch_dr", method = RequestMethod.GET)
	public void autosearch_dr(@RequestParam("query") String query, HttpServletResponse response) {
		logger.info("autosearch_dr");

		String[] items = query.split("_@");

		List<String> resultList = null;
		JSONArray jsonArray = null;
		if(items[0].contentEquals("disease"))	{
			resultList = service.getDRAutoSearchByDisease(items[1]);
			jsonArray = JSONArray.fromObject(resultList);
			System.out.println(jsonArray);
		}
		else if(items[0].contentEquals("gene"))	{
			resultList = service.getDRAutoSearchByGene(items[1]);
			jsonArray = JSONArray.fromObject(resultList);
			System.out.println(jsonArray);
		}
		else if(items[0].contentEquals("chemical"))	{
			resultList = service.getDRAutoSearchByDrug(items[1]);
			jsonArray = JSONArray.fromObject(resultList);
			System.out.println(jsonArray);
		}

		try {
			if(jsonArray == null)	return ;
			
			response.getWriter().print(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/sideeffect", method = RequestMethod.GET)
	public void sideeffect(@RequestParam("drugname") String drugname, HttpServletResponse response) {
		logger.info("sideeffect: "+drugname);
		drugname = drugname.toLowerCase().trim();
		
		List<SideEffectVO> resultList = null;
		JSONArray jsonArray = null;
		resultList = service.getDrugSideEffect(drugname);
		jsonArray = JSONArray.fromObject(resultList);
		System.out.println("sideeffect: " + jsonArray);
		
		try {
			if(jsonArray == null)	return ;
			
			response.getWriter().print(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
