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
    private TextField num1, num2, ans;
    private Button btn_plus, btn_minus, btn_milt, btn_div, btn_mod, btn_max;
    private Span oper;
    public MathView(){
        num1 = new TextField("Number 1");
        num2 = new TextField("Number 2");
        ans = new TextField("Answer");

        btn_plus = new Button("+");
        btn_minus = new Button("-");
        btn_milt = new Button("x");
        btn_div = new Button("/");
        btn_mod = new Button("Mod");
        btn_max = new Button("Max");

        oper = new Span("Operator");

        VerticalLayout v1 = new VerticalLayout();
        HorizontalLayout h1 = new HorizontalLayout();
        h1.add(btn_plus, btn_minus, btn_milt, btn_div, btn_mod, btn_max);
        v1.add(num1, num2, oper, h1, ans);
        this.add(v1);

        btn_plus.addClickListener(event ->{
            double number1 = Double.parseDouble(num1.getValue());
            double number2 = Double.parseDouble(num2.getValue());
            String out = WebClient.create().get().uri("http://localhost:8080/plus/"+ number1 +"/"+ number2).retrieve().bodyToMono(String.class).block();
            ans.setValue(out);
        });

        btn_minus.addClickListener(event ->{
            double number1 = Double.parseDouble(num1.getValue());
            double number2 = Double.parseDouble(num2.getValue());
            String out = WebClient.create().get().uri("http://localhost:8080/minus/"+ number1 +"/"+ number2).retrieve().bodyToMono(String.class).block();
            ans.setValue(out);
        });

        btn_milt.addClickListener(event ->{
            double number1 = Double.parseDouble(num1.getValue());
            double number2 = Double.parseDouble(num2.getValue());
            String out = WebClient.create().get().uri("http://localhost:8080/multi/"+ number1 +"/"+ number2).retrieve().bodyToMono(String.class).block();
            ans.setValue(out);
        });

        btn_div.addClickListener(event ->{
            double number1 = Double.parseDouble(num1.getValue());
            double number2 = Double.parseDouble(num2.getValue());
            String out = WebClient.create().get().uri("http://localhost:8080/divide/"+ number1 +"/"+ number2).retrieve().bodyToMono(String.class).block();
            ans.setValue(out);
        });

        btn_mod.addClickListener(event ->{
            double number1 = Double.parseDouble(num1.getValue());
            double number2 = Double.parseDouble(num2.getValue());
            String out = WebClient.create().get().uri("http://localhost:8080/mod/"+ number1 +"/"+ number2).retrieve().bodyToMono(String.class).block();
            ans.setValue(out);
        });

        btn_max.addClickListener(event -> {
            double number1 = Double.parseDouble(num1.getValue());
            double number2 = Double.parseDouble(num2.getValue());

            String out = WebClient.create().post().uri("http://localhost:8080/max?number1="+number1+"&number2="+number2).retrieve().bodyToMono(String.class).block();
            ans.setValue(out);
        });

    }
}
