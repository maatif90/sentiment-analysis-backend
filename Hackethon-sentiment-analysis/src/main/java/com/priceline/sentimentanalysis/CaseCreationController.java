package com.priceline.sentimentanalysis;

import com.priceline.cares.casemanagement.dto.CaseServiceInputDTO;
import com.priceline.cares.casemanagement.dto.CaseServiceOutputDTO;
import com.priceline.cares.casemanagement.service.CaseServiceImplWrapper;
import com.priceline.cares.slkservice.dto.EventLog;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class CaseCreationController {
    @PostMapping("caseCreation")
    public String createCase(@RequestBody String offerString){




        CaseServiceImplWrapper caseServiceWrapper = CaseServiceImplWrapper.getInstance();
        Pattern pattern = Pattern.compile("\\b\\d{11}\\b");
        Matcher matcher = pattern.matcher(offerString);
        Long number=null;
        if (matcher.find()) {
             number = Long.valueOf(matcher.group());
        }

        CaseServiceInputDTO caseDTO = new CaseServiceInputDTO();
        caseDTO.setOfferNumber(number);
        caseDTO.setUserName("pclnoperations");
        caseDTO.setWorkflowId(19000l); //TODO change which issue ID is required. 
        caseDTO.setIssueId(251490l);
        caseDTO.setTaskId(28002l);


        ArrayList<EventLog> events = new ArrayList<EventLog>();
        EventLog eventLog = new EventLog();
        eventLog.setEventId("NotesAdded");
        eventLog.setEventDesc("This case is created in response to social media complaint");
        eventLog.setEventComments("Testing for customer complaint");

        events.add(eventLog);
        caseDTO.setEvents(events);

        CaseServiceOutputDTO caseServiceOutputDTO = null;

        try {
            caseServiceOutputDTO = caseServiceWrapper.addCase(caseDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return "success";
    }

}
