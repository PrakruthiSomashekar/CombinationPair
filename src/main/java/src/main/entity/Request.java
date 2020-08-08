package src.main.entity;


import java.util.List;

public class Request {

    private int[] input;
    private Integer sum;


    public Request(int[] input, Integer sum) {
        this.input = input;
        this.sum = sum;
    }

    public int[] getInput() {
        return input;
    }

    public void setInput(int[] input) {
        this.input = input;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }
}
