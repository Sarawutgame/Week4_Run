package com.example.week4_run;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.web.reactive.function.client.WebClient;

@Route(value = "/index1")
public class CashierView extends VerticalLayout {
    private TextField money, b_1000, b_500, b_100, b_20, b_10, b_5, b_1;
    private Button go_change;
    public CashierView(){
        money = new TextField("เงินทอน");
        b_1000 = new TextField();
        b_500 = new TextField();
        b_100 = new TextField();
        b_20 = new TextField();
        b_10 = new TextField();
        b_5 = new TextField();
        b_1 = new TextField();
        go_change = new Button("คำนวนเงินทอน");

        money.setPrefixComponent(new Span("$"));
        b_1000.setPrefixComponent(new Span("$1000"));
        b_500.setPrefixComponent(new Span("$500"));
        b_100.setPrefixComponent(new Span("$100"));
        b_20.setPrefixComponent(new Span("$20"));
        b_10.setPrefixComponent(new Span("$10"));
        b_5.setPrefixComponent(new Span("$5"));
        b_1.setPrefixComponent(new Span("$1"));

        this.add(money, go_change, b_1000, b_500, b_100, b_20, b_10, b_5, b_1);

        go_change.addClickListener(buttonClickEvent -> {
            int money_in = Integer.parseInt(money.getValue());
            Change out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/getChange/" + money_in)
                    .retrieve()
                    .bodyToMono(Change.class)
                    .block();
            b_1000.setValue(out.getB1000() + "");
            b_500.setValue(out.getB500()+ "");
            b_100.setValue(out.getB100()+ "");
            b_20.setValue(out.getB20()+ "");
            b_10.setValue(out.getB10()+ "");
            b_5.setValue(out.getB5()+ "");
            b_1.setValue(out.getB1()+ "");
        });
    }
}
