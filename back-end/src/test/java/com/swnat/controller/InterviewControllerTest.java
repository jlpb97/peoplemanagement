package com.swnat.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.swnat.TestUtils;
import com.swnat.model.Interview;
import com.swnat.service.InterviewService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class) // provide the spring context for the test
@SpringBootTest
@AutoConfigureMockMvc//provide configuration for the MockMvc
public class InterviewControllerTest {

    @Autowired
    private MockMvc mockMvc;//provide Spring MVC infrastructure without starting server.

    @MockBean
    private InterviewService interviewService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        //lets the Spring know that we want a WebApplicationContext loaded for the project
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void saveInterviewTest() throws Exception {
        // prepare data and mock's behaviour
        Interview interview = TestUtils.buildInterviewTest();
        when(interviewService.add(any(Interview.class))).
        thenReturn(interview);

        String requestJson= TestUtils.jsonStringFromObject(interview);

        // execute
         MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(TestUtils.INTERVIEW_URL).
         contentType(MediaType.APPLICATION_JSON_UTF8).
         accept(MediaType.APPLICATION_JSON_UTF8).
         content(requestJson)).andReturn();


        // verify status post call
        int status = result.getResponse().getStatus();
        assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);
                
        // verify that service method was called once
        verify(interviewService).add(any(Interview.class));
    }

    @Test
    public void updateInterviewTest() throws Exception {
        // prepare data and mock's behaviour
        Interview interview = TestUtils.buildInterviewTest();
        when(interviewService.add(any(Interview.class))).
        thenReturn(interview);

        String requestJson= TestUtils.jsonStringFromObject(interview);

        // execute
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(TestUtils.INTERVIEW_URL+"{id}", interview.getId()).
        contentType(MediaType.APPLICATION_JSON_UTF8).
        accept(MediaType.APPLICATION_JSON_UTF8).
        content(requestJson)).andReturn();

        // verify status post call
        int status = result.getResponse().getStatus();
        assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);
                
        // verify that service method was called once
        verify(interviewService).add(any(Interview.class));        
    }
}