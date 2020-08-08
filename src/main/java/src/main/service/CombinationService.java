package src.main.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import src.main.entity.History;
import src.main.repository.CombinationRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CombinationService {

    @Autowired
    private CombinationRepository repository;

    // Time complexity O(n2), Space Complexity O(1)

    public String generatePair(int[] inputArray, int sum) throws JsonProcessingException {

        List<List<Integer>> result = new ArrayList();

        if(inputArray.length < 3)
            return "";

        Arrays.sort(inputArray);
        for(int  i = 0; i < inputArray.length - 2; i++){
            if(i == 0 || (i > 0 && inputArray[i] != inputArray[i - 1])){
                int left = i + 1;
                int right = inputArray.length - 1;

                while(left < right){
                    if(inputArray[i] + inputArray[left] + inputArray[right] == sum){
                        result.add(Arrays.asList(inputArray[i], inputArray[left], inputArray[right]));
                        while(left < right && inputArray[left] == inputArray[left + 1]) left++;
                        while(left < right && inputArray[right] == inputArray[right - 1]) right--;
                        left++;
                        right--;
                    }
                    else if(inputArray[i] + inputArray[left] + inputArray[right] < sum) left++;
                    else right--;
                }
            }
        }
        return saveRequest(inputArray, sum, result);
    }

    protected String saveRequest(int[] inputArray, int sum, List<List<Integer>> resultList) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultList);
        repository.save(new History(Arrays.toString(inputArray), sum, result));
        return result;
    }

    public List<History> getHistory(){
        return repository.findAll();
    }
}
