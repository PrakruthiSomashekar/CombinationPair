package src.main.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import src.main.entity.History;
import src.main.entity.Request;
import src.main.service.CombinationService;

import java.util.ArrayList;
import java.util.List;

public class CombinationControllerTest {

    @Mock
    private CombinationService serviceMock;

    @InjectMocks
    private CombinationController controllerMock;

    @BeforeMethod
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetHistory(){
        History history = new History("[1,2,3,4]",6,"[[1,2,3]]");
        List<History> expected = new ArrayList<>();
        expected.add(history);
        Mockito.when(serviceMock.getHistory()).thenReturn(expected);
        List<History> historyList = controllerMock.getHistory();

        Assert.assertEquals(historyList.size(), expected.size());
        Assert.assertEquals(historyList.get(0).getInput(), expected.get(0).getInput());
        Assert.assertEquals(historyList.get(0).getSum(), expected.get(0).getSum());
        Assert.assertEquals(historyList.get(0).getOutput(), expected.get(0).getOutput());
    }

    @Test
    public void testGetCombinationPairPositive() throws JsonProcessingException {
        Request request = new Request(new int[]{1,2,3,4}, 6);
        Mockito.when(serviceMock.generatePair(Mockito.any(), Mockito.anyInt())).thenReturn("[[1,2,3]]");
        String result = controllerMock.getCombinationPair(request);

        Assert.assertEquals(result, "[[1,2,3]]");
    }

    @Test(expectedExceptions = ResponseStatusException.class)
    public void testGetCombinationPairWithNullInputArray() {
        Request request = new Request(null, 6);
        controllerMock.getCombinationPair(request);
    }

    @Test(expectedExceptions = ResponseStatusException.class)
    public void testGetCombinationPairWithNullSum() {
        Request request = new Request(new int[]{1,2,3,4}, null);
        controllerMock.getCombinationPair(request);
    }

    @Test(expectedExceptions = ResponseStatusException.class)
    public void testGetCombinationPairNegative() throws JsonProcessingException {
        Request request = new Request(new int[]{1,2,3,4}, 6);
        Mockito.when(serviceMock.generatePair(Mockito.any(), Mockito.anyInt())).thenThrow(JsonProcessingException.class);
        controllerMock.getCombinationPair(request);

    }
}
