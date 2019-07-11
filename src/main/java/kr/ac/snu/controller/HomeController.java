package kr.ac.snu.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
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
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		return "home";
	}

	@RequestMapping(value = "/wordcloud", method = RequestMethod.GET)
	public String wordcloud() {
		logger.info("wordcloud");

		return "wordcloud";
	}

	@RequestMapping(value = "/litsearch", method = RequestMethod.GET)
	public String litsearch() {
		logger.info("litsearch");

		return "litSearch";
	}

	@RequestMapping(value = "/paperviz", method = RequestMethod.GET)
	public String paperviz(@RequestParam("pmid") String pmid, Model model) throws ParseException {
		logger.info("paperviz");
		logger.info("pmid: " + pmid);

		//TODO: 2. text에 color정보 입혀 client에 보내면 완료(굳이 client에서 처리하지 않고 해당하는 keyword를 찾아서 tag를 달아서 보내자!).
		
		String request_url = "https://www.ebi.ac.uk/europepmc/webservices/rest/search?query=" + pmid + "&resultType=core&pageSize=1&format=json";
		String[] bioconcept = {"Gene","Disease","Mutation"}; 
		String format = "JSON";
		Map<String, List<String>> colorEntity = new HashMap<String, List<String>>();
		
		for(String concept: bioconcept)	{
			String url_Submit = "https://www.ncbi.nlm.nih.gov/CBBresearch/Lu/Demo/RESTful/tmTool.cgi/" + concept + "/" + pmid + "/" + format + "/";
			logger.info("request url: "+url_Submit);
			List<String> temp = service.getPubTator(url_Submit);
			System.out.println(concept + "\t" + temp);
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
				System.out.println( key+"\t"+ keyword);
				
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
		
//		int begin = 86;
//		int end = 89;
//		String gene = temp_title.substring(begin,end);
//		temp_title = temp_title.replace(gene, "<font style=\"background-color: rgb(255,153,0)\">"  + gene + "</font>");
		
		logger.info("paper title: "+temp_title);
		logger.info("paper abs: "+temp_abs);
		logger.info("paper pub: "+temp_pub);
		
//		String coloredAbs = "BACKGROUND: <font title=\"MEDIC:D009369\" style=\"background-color: rgb(255,153,0)\">Cervical cancer</font> is driven by <font title=\"NCBI Taxonomy:10566\" style=\"background-color: rgb(0,208,255)\">human papillomavirus</font> virus-specific oncoprotein E6. E6 interacts with E3 ubiquitin-protein ligase, resulting in the proteolysis of <font title=\"NCBI Gene:7157\" style=\"background-color: rgb(200,64,240)\">p53</font> protein. The aim of this study was to analyze one <font title=\"NCBI Gene:7157\" style=\"background-color: rgb(200,64,240)\">TP53</font> mutation in <font title=\"NCBI Taxonomy:9606\" style=\"background-color: rgb(0,208,255)\">patients</font> with <font title=\"MEDIC:D009369\" style=\"background-color: rgb(255,153,0)\">cervical cancer</font> and to correlate it to prognosis. MATERIALS AND METHODS: A total of 248 paraffin-embedded <font title=\"MEDIC:D009369\" style=\"background-color: rgb(255,153,0)\">tumor</font> samples were stained for mutated <font title=\"NCBI Gene:7157\" style=\"background-color: rgb(200,64,240)\">p53</font> protein. The distribution and intensity of staining both in the nucleus and cytoplasm were evaluated with a semi-quantitative immunohistochemical score. RESULTS: A total of 66% of studied cervical <font title=\"MEDIC:D002277\" style=\"background-color: rgb(255,153,0)\">carcinomas</font> expressed the mutated <font title=\"NCBI Gene:7157\" style=\"background-color: rgb(200,64,240)\">p53</font> protein. The overall survival was better for <font title=\"NCBI Taxonomy:9606\" style=\"background-color: rgb(0,208,255)\">patients</font> expressing the mutated <font title=\"NCBI Gene:7157\" style=\"background-color: rgb(200,64,240)\">p53</font> protein in the nucleus. CONCLUSION: Interestingly, we found a very high mutation rate of <font title=\"NCBI Gene:7157\" style=\"background-color: rgb(200,64,240)\">TP53</font> in a <font title=\"MEDIC:D009369\" style=\"background-color: rgb(255,153,0)\">cancer</font> type where <font title=\"NCBI Gene:7157\" style=\"background-color: rgb(200,64,240)\">p53</font> is initially inactivated via E6 during the development of <font title=\"MEDIC:D009369\" style=\"background-color: rgb(255,153,0)\">cervical cancer</font>. An unexpected finding is the correlation of this mutation with better survival, possibly due to better response to therapy";
//		String coloredTitle = "Immunohistochemical Evaluation of the Role of <font title=\"NCBI Gene:7157\" style=\"background-color: rgb(200,64,240)\">p53</font> Mutation in Cervical Cancer: Ser-20 <font title=\"NCBI Gene:7157\" style=\"background-color: rgb(200,64,240)\">p53</font>-Mutant Correlates with Better Prognosis.";
//		String publication = "Anticancer research; 2016 Jun ; 36(6) 3131-7";
		
		model.addAttribute("pmid",pmid);
		model.addAttribute("title",temp_title);
//		model.addAttribute("coloredAbs",coloredAbs);
//		model.addAttribute("coloredTitle",coloredTitle);
//		model.addAttribute("publication",publication);
		model.addAttribute("coloredAbs",temp_abs);
		model.addAttribute("coloredTitle",temp_title);
		model.addAttribute("publication",temp_pub);
		
		return "paperViz";
	}

	@RequestMapping(value = "/drugrepositor", method = RequestMethod.GET)
	public String drugrepositor() {
		logger.info("drugrepositor");

		return "drugrepositor";
	}
	
	@RequestMapping(value = "/searchdrugrepositor", method = RequestMethod.GET)
	public void searchDrugrepositor(@RequestParam("query") String query, HttpServletResponse response) {
		logger.info("drugrepositor " + query);
		String[] items = query.split("_@");

		List<RepositioningDrugVO> resultList = null;
		JSONArray jsonArray = null;
		
		if(items[0].contentEquals("disease"))	{
			resultList = service.getDrugsWithDiseaseName(items[1]);
			jsonArray = JSONArray.fromObject(resultList);
			logger.info("[disease]mybeanList - " + jsonArray);
		}
		else if(items[0].contentEquals("gene"))	{ 
			resultList = service.getDrugsWithGeneName(items[1]);
			jsonArray = JSONArray.fromObject(resultList);
			logger.info("[gene]mybeanList - " + jsonArray);
		}
		else if(items[0].contentEquals("drug"))	{
			resultList = service.getDrugUsage(items[1]);
			jsonArray = JSONArray.fromObject(resultList);
			logger.info("[gene]mybeanList - " + jsonArray);
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

	@RequestMapping(value = "/dataset", method = RequestMethod.GET)
	public String dataset() {
		logger.info("dataset");

		return "dataset";
	}

	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String contact() {
		logger.info("contact");

		return "contact";
	}


	@RequestMapping(value = "/dbsearch", method = RequestMethod.GET)
	public void dbsearch(@RequestParam("query") String query, HttpServletResponse response) {
		logger.info("dbsearch");
		String[] items = query.split("_@");

		List<ResultVO> resultList = null;
		JSONArray jsonArray = null;
		if(items[0].contentEquals("disease"))	{
			resultList = service.getResultByDisease(items[1]);
			jsonArray = JSONArray.fromObject(resultList);
			logger.info("[disease]mybeanList - " + jsonArray);
		}
		else if(items[0].contentEquals("gene"))	{
			resultList = service.getResultByGene(items[1]);
			jsonArray = JSONArray.fromObject(resultList);
			logger.info("[gene]mybeanList - " + jsonArray);
		}
		else if(items[0].contentEquals("pmid"))	{
			resultList = service.getResultByPmid(items[1]);
			jsonArray = JSONArray.fromObject(resultList);
			logger.info("[pmid]mybeanList - " + jsonArray);
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
	
	
	@RequestMapping(value = "/instruction", method = RequestMethod.GET)
	public String instruction(Locale locale, Model model) {
		logger.info("instruction page");

		return "instruction";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * search with disease name.
	 * */
	@RequestMapping(value = "/drugprogdisease", method = RequestMethod.GET)
	public void drugprogdisease(@RequestParam("drug_type") String drug_type, @RequestParam("query") String query, HttpServletResponse response) {
		logger.info("drugprogdisease >> " + query + ", " + drug_type);
		String[] items = query.split("_@");

		List<RepositioningDrugVO> resultList = null;
		JSONArray jsonArray = null;
		
		if(drug_type.contentEquals("Aprroved Reference"))	{
			logger.info("[Aprroved Reference] - " + items[1] + ", " + drug_type);

			resultList = service.getApprovedReferenceWithDisease(items[1]);
			jsonArray = JSONArray.fromObject(resultList);
			logger.info("data: " + jsonArray);
		}
		else if(drug_type.contentEquals("Approved Candidate"))	{ 
//			logger.info("[Aprroved Candidate] - " + items[1] + ", " + drug_type);
			
			resultList = service.getApprovedCandidateWithDisease(items[1]);
			jsonArray = JSONArray.fromObject(resultList);
			logger.info("data: " + jsonArray);
		}
		else if(drug_type.contentEquals("Interrupted Candidate"))	{
			logger.info("[Interrupted Candidate] - " + items[1] + ", " + drug_type);
			
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
	
	@RequestMapping(value = "/drugproggene", method = RequestMethod.GET)
	public void drugproggene(@RequestParam("drug_type") String drug_type, @RequestParam("query") String query, HttpServletResponse response) {
		logger.info("drugproggene >> " + query + ", " + drug_type);
		
	}
	
	
	@RequestMapping(value = "/drugprogchemical", method = RequestMethod.GET)
	public void drugprogchemical(@RequestParam("drug_type") String drug_type, @RequestParam("query") String query, HttpServletResponse response) {
		logger.info("drugprogchemical >> " + query + ", " + drug_type);
	}
	
	
	
}
