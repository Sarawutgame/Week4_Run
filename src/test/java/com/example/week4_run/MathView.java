package com.example.week4_run;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.web.reactive.function.client.WebClient;


@Route(value = "/index")
public class MathView extends VerticalLayout {
    private TextField txt1, txt2, ans;
    private Button btnPlus, btnMinus, btnMulti, btnDivide, btnMod, btnMax;
    private Span operator;

    public MathView(){
        txt1 = new TextField("Number 1");
        txt2 = new TextField("Number 2");
        ans = new TextField("Answer");

        btnPlus = new Button("+");
        btnMinus = new Button("-");
        btnMulti = new Button("x");
        btnDivide = new Button("/");
        btnMod = new Button("Mod");
        btnMax = new Button("Max");

        operator = new Span("Operator");

        HorizontalLayout h1 = new HorizontalLayout();
        VerticalLayout v1 = new VerticalLayout();

        h1.add(btnPlus, btnMinus, btnMulti, btnDivide, btnMod, btnMax);
        v1.add(txt1, txt2, operator, h1, ans);
        this.add(v1);

        btnPlus.addClickListener(event -> {
            double num1 = Double.parseDouble(txt1.getValue());
            double num2 = Double.parseDouble(txt2.getValue());

            String out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/plus/"+num1+"/"+num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            ans.setValue(out);
        });

        btnMinus.addClickListener(event -> {
            double num1 = Double.parseDouble(txt1.getValue());
            double num2 = Double.parseDouble(txt2.getValue());

            String out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/minus/"+num1+"/"+num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            ans.setValue(out);
        });

        btnMulti.addClickListener(event -> {
            double num1 = Double.parseDouble(txt1.getValue());
            double num2 = Double.parseDouble(txt2.getValue());

            String out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/multi/"+num1+"/"+num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            ans.setValue(out);
        });

        btnDivide.addClickListener(event -> {
            double num1 = Double.parseDouble(txt1.getValue());
            double num2 = Double.parseDouble(txt2.getValue());

            String out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/divide/"+num1+"/"+num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            ans.setValue(out);
        });

        btnMod.addClickListener(event -> {
            double num1 = Double.parseDouble(txt1.getValue());
            double num2 = Double.parseDouble(txt2.getValue());

            String out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/mod/"+num1+"/"+num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            ans.setValue(out);
        });

        btnMax.addClickListener(event -> {
            double num1 = Double.parseDouble(txt1.getValue());
            double num2 = Double.parseDouble(txt2.getValue());

            String out = WebClient.create()
                    .post()
                    .uri("http://localhost:8080/max?n1="+num1+"&n2="+num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            ans.setValue(out);
        });
    }
}
