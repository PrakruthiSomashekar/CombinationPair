package src.main.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import src.main.entity.History;
import src.main.repository.CombinationRepository;

import java.util.ArrayList;
import java.util.List;

public class CombinationServiceTest {

    @Mock
    private CombinationRepository repositoryMock;

    @InjectMocks
    private CombinationService serviceMock;

    private CombinationService serviceMockSpy;

    @BeforeMethod
    public void setup(){
        MockitoAnnotations.initMocks(this);
        serviceMockSpy = Mockito.spy(serviceMock);
    }

    @Test
    public void testGeneratePair() throws JsonProcessingException {
        Mockito.when(repositoryMock.save(Mockito.any())).thenReturn(new History());
        String result = serviceMockSpy.generatePair(new int[]{1,2,3,4}, 6);
        Assert.assertEquals(result.replace(" ",""), "[[1,2,3]]");
    }

    @Test(expectedExceptions = JsonProcessingException.class)
    public void testGeneratePairNegative() throws JsonProcessingException {
        Mockito.doThrow(JsonProcessingException.class).when(serviceMockSpy).saveRequest(Mockito.any(), Mockito.anyInt(), Mockito.anyList());
        Mockito.when(repositoryMock.save(Mockito.any())).thenReturn(new History());
        serviceMockSpy.generatePair(new int[]{1,2,3,4}, 6);
    }

    @Test
    public void testGetHistory(){
        History history = new History("[1,2,3,4]",6,"[[1,2,3]]");
        List<History> expected = new ArrayList<>();
        expected.add(history);
        Mockito.when(repositoryMock.findAll()).thenReturn(expected);
        List<History> historyList = serviceMockSpy.getHistory();

        Assert.assertEquals(historyList.size(), expected.size());
        Assert.assertEquals(historyList.get(0).getInput(), expected.get(0).getInput());
        Assert.assertEquals(historyList.get(0).getSum(), expected.get(0).getSum());
        Assert.assertEquals(historyList.get(0).getOutput(), expected.get(0).getOutput());
    }

}
