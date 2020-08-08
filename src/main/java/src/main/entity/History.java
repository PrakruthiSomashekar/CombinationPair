package src.main.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

@Entity
@Table(name = "history")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "input_array")
    private String input;
    private int sum;
    private String output;

    public History() {
    }

    public History(String input, int sum, String output) {
        this.input = input;
        this.sum = sum;
        this.output = output;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", input='" + input + '\'' +
                ", sum=" + sum +
                ", output='" + output + '\'' +
                '}';
    }
}
