package com.example.week4_run;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route(value = "/index5")
public class TestView extends VerticalLayout {
    public TestView(){
        TextField num1 = new TextField("Number");
        this.add(num1);
    }
}
