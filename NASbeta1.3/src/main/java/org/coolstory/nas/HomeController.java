package org.coolstory.nas;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.coolstory.nas.bean.MessageBean;
import org.coolstory.nas.bean.UserBean;
import org.coolstory.nas.service.StudentService;
import org.coolstory.nas.service.UserService;
import org.coolstory.nas.testenv.TestingSpringHibernateBonecp;
import org.coolstory.nas.utils.CSVParser;
import org.coolstory.nas.utils.CSVReader;
import org.coolstory.nas.utils.CSVWriter;
import org.coolstory.nas.utils.DataFormVO;
import org.coolstory.nas.utils.TypeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	//Below three fields are required by Twilio
	public static final String ACCOUNT_SID = "AC53fc877e3310d97381a42289b5d16a2e";
    public static final String AUTH_TOKEN = "461844256289d70efbb2976d62d401b7";
    public static final String TWILIO_NUMBER = "+16464614380";
    
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String AboutPage() {
		System.out.println("Coming here for about ..");
		return "about";
	}
	
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String contactPage() {
		System.out.println("Coming here for about ..");
		return "contact";
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String uploadPage() {
		System.out.println("Coming here for about ..");
		return "upload";
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
		public String formPage(Map<String, Object> model) {
	        UserBean userForm = new UserBean();    
	        model.put("userForm", userForm);
	         
	        return "form";
	}
	
	//This will be called to render the form initially.
	@RequestMapping(value = "/sendsms", method = RequestMethod.GET)
	public String sendSmsPage(Map<String, Object> model) {
		UserBean userForm = new UserBean();    
        model.put("userBean", userForm);
		return "sendsms";
	}
	
	//This will be called once form is submitted.
	@RequestMapping(value = "/addNewEntry", method = RequestMethod.POST)
    public ModelAndView submitForm(@ModelAttribute("userForm") UserBean userBean,
                            BindingResult result) 
    {	
		MessageBean messageBean = userService.setUserData(userBean);
		
        return new ModelAndView("form", new ModelMap().addAttribute(
				"messageBean", messageBean));
    }
	
	//This will be fetch data on pressing getData button.
	@RequestMapping(value = "/getData", method = RequestMethod.GET)
    public ModelAndView getPersonsData() 
    {
		System.out.println("coming in getData method....");
		UserBean userBean = new UserBean();
		ArrayList<DataFormVO> personsDataList = null;
		
		try{
			
		String jsonString = "{}" ;
		personsDataList = userService.getPersonsData();
		
		System.out.print("not flag::"+personsDataList.get(0));
		
		ObjectMapper mapper = new ObjectMapper();
		Writer strWriter = new StringWriter();
		mapper.writeValue(strWriter, personsDataList);
		
		jsonString= strWriter.toString();
		
		userBean.setPersonDataString(jsonString);
		
		}catch(Exception e){
			
			e.printStackTrace();
		}
		return new ModelAndView("home", new ModelMap().addAttribute(
				"userBean", userBean));
    }
	
	/*
	 * This method will check if the list is not empty.If not then export data
	 * to file in csv format
	 */
	@RequestMapping(value = "/export", method = RequestMethod.GET)
	public String exportFileAction(
			@ModelAttribute("userBean") UserBean userBean,
			HttpServletResponse response, HttpSession session) {

		List<DataFormVO> exportDataList = null;
		CSVWriter csvWriter = null;

		try {
			exportDataList = userService.getPersonsData();
			
			String[] line = { "UserId","First Name", "Middle Name", "Last Name",
					"Email", "Mobile Number", "Address", "SMS Opt" };

			if (exportDataList != null && exportDataList.size() > 0) {
				try {
					response.setHeader("Content-type", "application/xls");

					String lv = TypeConverter.getCurrentDateInString();
					String outputFileName = lv + "personData.csv";

					response.setHeader("Content-disposition",
							"inline; filename=" + outputFileName);
					csvWriter = new CSVWriter(response.getWriter());
					csvWriter.writeNext(line);
					for (DataFormVO dataFormVO : exportDataList) {
						line[0] = dataFormVO.getUserId();
						line[1] = dataFormVO.getFirstName();
						line[2] = dataFormVO.getMiddleName();
						line[3] = dataFormVO.getLastName();
						line[4] = dataFormVO.getEmail();
						line[5] = dataFormVO.getMobileNumber();
						line[6] = dataFormVO.getAddress();
						line[7] = dataFormVO.getNotificationflag();

						csvWriter.writeNext(line);
					}

				} catch (IOException e) {
					logger.error("********** Unexpected Error occurred in : exportFileAction *********"
							+ e.getMessage());
				}
			} else {

				logger.error("********** Unexpected Error occurred in : exportFileAction *********");
			}
		} catch (Exception e) {
			logger.error("********** Unexpected Error occurred in : exportFileAction *********"
					+ e.getMessage());
		}

		finally {
			logger.info("executing finally block :exportFileAction ::");
			try {
				csvWriter.flush();
				csvWriter.close();
			} catch (IOException e) {

				logger.error("Error while flushing or closing csvWriter"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		logger.info("********** Exit : exportFileAction :::*********");
		return null;
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView homePage(@ModelAttribute("userBean") UserBean userBean) {
		
		return new ModelAndView("home", new ModelMap().addAttribute(
				"userBean", userBean));
	}
	
	@RequestMapping(value = "/updateSmsNotification", method = RequestMethod.POST)
    public ModelAndView updateSmsNotification(@ModelAttribute("userBean") UserBean userBeans) 
    {	
		System.out.println("inside update notification..");
		
		System.out.println("BEAN-->>"+userBeans.getFirstName()+ "mobileNumber::"+userBeans.getMobileNumber()+"::notf flag::"+userBeans.getNotificationflag());
		userService.updateSmsNotification(userBeans);

		UserBean userBean = new UserBean();
		ArrayList<DataFormVO> personsDataList = null;
		
		try{
			
		String jsonString = "{}" ;
		personsDataList = userService.getPersonsData();
		
		System.out.print("not flag::"+personsDataList.get(0));
		
		ObjectMapper mapper = new ObjectMapper();
		Writer strWriter = new StringWriter();
		mapper.writeValue(strWriter, personsDataList);
		
		jsonString= strWriter.toString();
		
		userBean.setPersonDataString(jsonString);
		
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
		return new ModelAndView("home", new ModelMap().addAttribute(
				"userBean", userBean));
    }
	
	@RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
	public ModelAndView uploadFile(ModelMap model, @RequestParam("file") MultipartFile file,HttpServletRequest request) {
			
		ArrayList<UserBean> userBeanList = new ArrayList<UserBean>();
		MessageBean messageBean = new MessageBean();
		UserBean userBean = null;
		boolean validationSuccessFlag = true;
	
		if (file.isEmpty()) {
			messageBean.setFlag(false);
			messageBean.setMessageDescription("Failed to upload file because its empty.");
			
			return new ModelAndView("upload", new ModelMap().addAttribute(
					"messageBean", messageBean));
	    }
	
		try {			
			
			byte[] bytes = file.getBytes();
            String completeData = new String(bytes);
            String[] rows = completeData.split("\\n");
            
            for(int i=1; i<rows.length; i++){
            
                String[] columns = rows[i].split(",");
          
                userBean = new UserBean();
                
                if(matchNumber(columns[0].replaceAll("^\"|\"$", ""))){;
                
                	userBean.setUserId(columns[0].replaceAll("^\"|\"$", ""));
                }else{
                	messageBean.setMessageDescription("Data type for 'User Id' is incorrect.");
                	validationSuccessFlag = false;
                	break;
                }
                
                if(matchString(columns[1].replaceAll("^\"|\"$", ""))){;
                
                	userBean.setFirstName(columns[1].replaceAll("^\"|\"$", ""));
	            }else{
	            	messageBean.setMessageDescription("Data type for 'First Name' is incorrect.");
	            	validationSuccessFlag = false;
	            	break;
	            }
	             
	            if(matchString(columns[2].replaceAll("^\"|\"$", ""))){;
	                
	            	userBean.setMiddleName(columns[2].replaceAll("^\"|\"$", ""));
	            }else{
	            	messageBean.setMessageDescription("Data type for 'Middle Name' is incorrect.");
	            	validationSuccessFlag = false;
	            	break;
	            }
                
	            if(matchString(columns[3].replaceAll("^\"|\"$", ""))){;
                
	            	userBean.setLastName(columns[3].replaceAll("^\"|\"$", ""));
                }else{
                	messageBean.setMessageDescription("Data type for 'Last Name' is incorrect.");
                	validationSuccessFlag = false;
                	break;
                }
	            
	            if(matchEmailAddress(columns[4].replaceAll("^\"|\"$", ""))){;
                
	            	userBean.setEmail(columns[4].replaceAll("^\"|\"$", ""));
                }else{
                	messageBean.setMessageDescription("Format for 'Email' is incorrect.");
                	validationSuccessFlag = false;
                	break;
                }
	            
	            if(matchPhoneNumber(columns[5].replaceAll("^\"|\"$", ""))){;
                
	            	userBean.setMobileNumber(columns[5].replaceAll("^\"|\"$", ""));
                }else{
                	messageBean.setMessageDescription("Data/format for 'Mobile Number' is incorrect.");
                	validationSuccessFlag = false;
                	break;
                }
	            
	            	userBean.setAddress(columns[6].replaceAll("^\"|\"$", ""));
	            
	            if(matchNotificationFlag(columns[7].replaceAll("^\"|\"$", ""))){;
                
	            userBean.setNotificationflag(columns[7].replaceAll("^\"|\"$", ""));
                }else{
                	messageBean.setMessageDescription("Data for 'Notification Flag' is incorrect.");
                	validationSuccessFlag = false;
                	break;
                }
            	userBeanList.add(userBean);          	
            }
            if(validationSuccessFlag){
              messageBean = userService.setImportedData(userBeanList);
            }
            
	    } catch(IOException e) {
	        //System.out.println("error while reading csv and put to db : " + e.getMessage());
	    	e.getStackTrace();
	    } 
		
		System.out.println("coming here..message"+messageBean.getMessageDescription());
		return new ModelAndView("upload", new ModelMap().addAttribute(
				"messageBean", messageBean));
	}
	
	@RequestMapping(value = "/sendSMS", method = RequestMethod.POST)
    public ModelAndView sendSMS(@ModelAttribute("userBean") UserBean userBean) 
    {	
		System.out.println("Send sms called..");
		MessageBean messageBean = new MessageBean();
		
		String mobileNumbers = userBean.getMobileNumberString();
		String messageBody = userBean.getMessageBody();
		
	    try {
	        TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
	 
	        // Build a filter for the MessageList
	        
	        String[] mobileNumbersArr = mobileNumbers.split(",");
	        
	        for(String number : mobileNumbersArr){
	        	
	        	System.out.println("Mobile number::"+number);
	        	
		        List<NameValuePair> params = new ArrayList<NameValuePair>();
		        params.add(new BasicNameValuePair("Body", messageBody));
		        params.add(new BasicNameValuePair("To", number)); //Add real number here
		        params.add(new BasicNameValuePair("From", TWILIO_NUMBER));
		 
		        MessageFactory messageFactory = client.getAccount().getMessageFactory();
		        Message message = messageFactory.create(params);
		        logger.info("Message SID::"+ message.getSid());
		        messageBean.setFlag(true);
		    	messageBean.setMessageDescription("Message sent successfully.");
	        }
	       
	    } 
	    catch (TwilioRestException e) {
	    	messageBean.setFlag(false);
	    	messageBean.setMessageDescription(e.getErrorMessage());
	        logger.error(e.getErrorMessage());
	    }
	    
	    return new ModelAndView("sendsms", new ModelMap().addAttribute(
				"messageBean", messageBean));
	}
	
	
	private boolean matchString(String str){
		
		System.out.println("str::"+str);
		
		String pattern = "^[a-zA-Z]+$";
		
		// Create a Pattern object
	    Pattern r = Pattern.compile(pattern);
	    
	   // Now create matcher object.
	    Matcher m = r.matcher(str);
	    System.out.println("Result::"+m.matches());
		return m.matches();
	}
	
	private boolean matchNumber(String str){
		
		String pattern = "^[0-9]*$";
	
		// Create a Pattern object
	    Pattern r = Pattern.compile(pattern);
	    
	    // Now create matcher object.
	    Matcher m = r.matcher(str);
		return m.matches();
	}
	
    private boolean matchPhoneNumber(String str){
		
		String pattern = "^((\\+|00)(\\d{1,3})[\\s-]?)?(\\d{10})$";
	
		// Create a Pattern object
	    Pattern r = Pattern.compile(pattern);
	    
	    // Now create matcher object.
	    Matcher m = r.matcher(str);
		return m.matches();
	}
    
    private boolean matchEmailAddress(String str){
		
		String pattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
	
		// Create a Pattern object
	    Pattern r = Pattern.compile(pattern);
	    
	    // Now create matcher object.
	    Matcher m = r.matcher(str);
		return m.matches();
	}
    
    private boolean matchNotificationFlag(String str){
		
		String pattern = "^[a-zA-Z]+$";
	
		// Create a Pattern object
	    Pattern r = Pattern.compile(pattern);
	    
	    // Now create matcher object.
	    Matcher m = r.matcher(str);
	    
	    if(m.matches() && str.length()<=3){
	    	return true;
	    }else{
	    	return false;
	    }
	}
}
