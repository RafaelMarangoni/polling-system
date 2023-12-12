package com.br.pollingsystem.service;

import com.br.pollingsystem.Service.AgendaService;
import com.br.pollingsystem.entity.SubjectEntity;
import com.br.pollingsystem.repository.SubjectRepository;
import com.br.pollingsystem.response.AgendaResponse;
import com.br.pollingsystem.utils.Messages;
import com.br.pollingsystem.utils.Validate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AgendaServiceTest {

    @Mock
    private SubjectRepository subjectRepository;

    @Mock
    private Validate validate;

    @InjectMocks
    private AgendaService agendaService;

    List<SubjectEntity> agendaResponseMockReturnEmpty(){
        List<SubjectEntity> newListEmptyMock = new ArrayList<>();
        return newListEmptyMock;
    }

    List<SubjectEntity> subjectEntityMock(){
        var subjectMock = SubjectEntity.builder()
                .id(1212)
                .titleSubject("Test title")
                .build();

        List<SubjectEntity> mockList = new ArrayList<>();
        mockList.add(subjectMock);
         return mockList;
    }

    List<AgendaResponse> agendaResponseReturnList(){

        List<AgendaResponse> newListMock = new ArrayList<>();
        newListMock.add(AgendaResponse.builder()
                        .agendaEnabled(subjectEntityMock())
                .build());
        return newListMock;
    }

    @Test
    public void getAllPollingTestSucess() {
        Mockito.when(subjectRepository.getAgendasEnabled()).thenReturn(subjectEntityMock());
        agendaService.getAllPolling();
        Assert.assertNotNull(subjectEntityMock());
        verify(subjectRepository, times(1)).getAgendasEnabled();

    }

    @Test
    public void getAllPollingTestReturnNull() {
        Mockito.when(subjectRepository.getAgendasEnabled()).thenReturn(null);
        agendaService.getAllPolling();
        Assert.assertEquals("We do not have active agendas",Messages.NO_AGENDA_ENABLE);
        verify(subjectRepository, times(1)).getAgendasEnabled();
    }

}
